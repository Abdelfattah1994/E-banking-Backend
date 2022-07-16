package ebanking_backend.com.rabou.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ebanking_backend.com.rabou.dtos.AccountOperationDTO;
import ebanking_backend.com.rabou.dtos.BankAccountDTO;
import ebanking_backend.com.rabou.dtos.BankAccountHistory;
import ebanking_backend.com.rabou.dtos.CurrentBankAccountDTO;
import ebanking_backend.com.rabou.dtos.CustomerDTO;
import ebanking_backend.com.rabou.dtos.SavingBankAccountDTO;
import ebanking_backend.com.rabou.entities.AccountOperation;
import ebanking_backend.com.rabou.entities.BankAccount;
import ebanking_backend.com.rabou.entities.CurrentAccount;
import ebanking_backend.com.rabou.entities.Customer;
import ebanking_backend.com.rabou.entities.SavingAccount;
import ebanking_backend.com.rabou.enums.AccountStatus;
import ebanking_backend.com.rabou.enums.OperationType;
import ebanking_backend.com.rabou.exceptions.BalanceNotSufficientException;
import ebanking_backend.com.rabou.exceptions.BankAccountNotFoundException;
import ebanking_backend.com.rabou.exceptions.CustomerNotFoundException;
import ebanking_backend.com.rabou.mappers.BankAccountMapperImpl;
import ebanking_backend.com.rabou.repositories.AccountOperationRepository;
import ebanking_backend.com.rabou.repositories.BankAccountRepository;
import ebanking_backend.com.rabou.repositories.CustomerRepository;

@Service
@Transactional
public class BankAccountServiceImpl implements BankAccountService{

	BankAccountRepository bankAccountRepository;
	AccountOperationRepository accountOperationRepository;
	CustomerRepository customerRepository;
	BankAccountMapperImpl dtoMapper;
	Logger log = LoggerFactory.getLogger(this.getClass().getName());
	
	public BankAccountServiceImpl(BankAccountRepository bankAccountRepository,BankAccountMapperImpl dtoMapper,
			AccountOperationRepository accountOperationRepository, CustomerRepository customerRepository) {
		this.bankAccountRepository = bankAccountRepository;
		this.accountOperationRepository = accountOperationRepository;
		this.customerRepository = customerRepository;
		this.dtoMapper = dtoMapper;
	}

	@Override
	public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
		log.info("Saving new customer");
		Customer customer = dtoMapper.fromCustomerDTO(customerDTO);
		Customer savedCustomer = customerRepository.save(customer);
		return dtoMapper.fromCustomer(savedCustomer);
	}
	
	@Override
	public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
		log.info("Saving new customer");
		Customer customer = dtoMapper.fromCustomerDTO(customerDTO);
		Customer savedCustomer = customerRepository.save(customer);
		return dtoMapper.fromCustomer(savedCustomer);
	}
	
	@Override
	public void deleteCustomer(Long CustomerId) {
		customerRepository.deleteById(CustomerId);
	}

	@Override
	public CurrentBankAccountDTO saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException {
		Customer customer = customerRepository.findById(customerId).orElse(null);
		if(customer == null)
			throw new CustomerNotFoundException("Customer not found !");
		CurrentAccount currentAccount = new CurrentAccount();
		currentAccount.setId(UUID.randomUUID().toString());
		currentAccount.setBalance(initialBalance);
		currentAccount.setCreatedAT(new Date());
		currentAccount.setCustomer(customer);
		currentAccount.setOverDraft(overDraft);
		currentAccount.setStatus(AccountStatus.CREATED);
		CurrentAccount savedCurrentAccount = bankAccountRepository.save(currentAccount);
		return dtoMapper.fromCurrentBankAccount(savedCurrentAccount);
	}

	@Override
	public SavingBankAccountDTO saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException{
		Customer customer = customerRepository.findById(customerId).orElse(null);
		if(customer == null)
			throw new CustomerNotFoundException("Customer not found !");
		SavingAccount savingAccount = new SavingAccount();
		savingAccount.setId(UUID.randomUUID().toString());
		savingAccount.setBalance(initialBalance);
		savingAccount.setCustomer(customer);
		savingAccount.setCreatedAT(new Date());
		savingAccount.setInterestRate(interestRate);
		savingAccount.setStatus(AccountStatus.CREATED);
		SavingAccount savedBankAccount = bankAccountRepository.save(savingAccount);
		return dtoMapper.fromSavingBankAccount(savedBankAccount);
	}

	@Override
	public void debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException {
		BankAccount bankAccount = bankAccountRepository.findById(accountId)
				.orElseThrow(()->new BankAccountNotFoundException("Bank account not found !"));
		if(bankAccount.getBalance()<amount )
			throw new BalanceNotSufficientException("Balance not sufficient");
		AccountOperation accountOperation = new AccountOperation();
		accountOperation.setBankAccount(bankAccount);
		accountOperation.setDescription(description);
		accountOperation.setOperationDate(new Date());
		accountOperation.setType(OperationType.DEBIT);
		accountOperation.setAmount(amount);
		accountOperationRepository.save(accountOperation);
		bankAccount.setBalance(bankAccount.getBalance()-amount);
		bankAccountRepository.save(bankAccount);
		}
		
	

	@Override
	public void credit(String accountId, double amount, String description) throws BankAccountNotFoundException {
		BankAccount bankAccount = bankAccountRepository.findById(accountId)
				.orElseThrow(()->new BankAccountNotFoundException("Bank account not found !"));
		AccountOperation accountOperation = new AccountOperation();
		accountOperation.setType(OperationType.CREDIT);
		accountOperation.setAmount(amount);
		accountOperation.setDescription(description);
		accountOperation.setOperationDate(new Date());
		accountOperation.setBankAccount(bankAccount);
		accountOperationRepository.save(accountOperation);
		bankAccount.setBalance(bankAccount.getBalance()+amount);
		bankAccountRepository.save(bankAccount);
	}

	@Override
	public void transfer(String accountIdSource, String accountIdDestination, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException {
		debit(accountIdSource, amount, "transfer to "+accountIdDestination);
		credit(accountIdDestination, amount, "transfer from "+accountIdSource);
	}

	@Override
	public List<CustomerDTO> listCustomers() {
		List<Customer> customersList = customerRepository.findAll();
		List<CustomerDTO> customersDTOList = customersList.stream()
				.map(customer->dtoMapper.fromCustomer(customer))
				.collect(Collectors.toList());
		return customersDTOList;
	}

	@Override
	public BankAccountDTO getBankAccount(String accountId) throws BankAccountNotFoundException{
		BankAccount bankAccount = bankAccountRepository.findById(accountId)
				.orElseThrow(()->new BankAccountNotFoundException("Bank account not found !"));
		if(bankAccount instanceof CurrentAccount) {
			CurrentAccount currentAccount = (CurrentAccount) bankAccount ;
			return dtoMapper.fromCurrentBankAccount(currentAccount);
		}else {
		SavingAccount savingAccount = (SavingAccount) bankAccount ;
		return dtoMapper.fromSavingBankAccount(savingAccount);
		}

	}
	
	@Override
	public List<BankAccountDTO> bankAccountList(){
		List<BankAccount> bankAccountList = bankAccountRepository.findAll();
		List<BankAccountDTO> bankAccountDTOList = bankAccountList.stream().map(bankAccount ->{
			if(bankAccount instanceof CurrentAccount) {
				CurrentAccount currentAccount = (CurrentAccount) bankAccount;
				return dtoMapper.fromCurrentBankAccount(currentAccount);
			}else {
				SavingAccount savingAccount = (SavingAccount) bankAccount;
				return dtoMapper.fromSavingBankAccount(savingAccount);
			}
		}).collect(Collectors.toList());
		return bankAccountDTOList;
	}
	@Override
	public CustomerDTO getCustomer(Long customerId) throws CustomerNotFoundException {
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(()->new CustomerNotFoundException("Customer not Found !"));
		return dtoMapper.fromCustomer(customer);
	}
	
	@Override
	public List<AccountOperationDTO> accountHistory(String accountId){
		List<AccountOperation> accountOperations = accountOperationRepository.findByBankAccountId(accountId);
		return accountOperations.stream().map(operation ->{
			return dtoMapper.fromAccountOperation(operation);
		}).collect(Collectors.toList());
		
	}
	@Override
	public BankAccountHistory bankAccountHistory(String accountId, int page, int size) throws BankAccountNotFoundException {
		BankAccountHistory bankAccountHistory = new BankAccountHistory();
		BankAccount bankAccount = bankAccountRepository.findById(accountId).orElse(null);
		if (bankAccount == null) throw new BankAccountNotFoundException("account not found");
		Page<AccountOperation> pageOperations = accountOperationRepository.findByBankAccountId(accountId, PageRequest.of(page, size));
		List<AccountOperationDTO> pageOperationsDTO = new ArrayList<AccountOperationDTO>();
		for(AccountOperation op : pageOperations) {
			pageOperationsDTO.add(dtoMapper.fromAccountOperation(op));
		}
		bankAccountHistory.setAccountOperations(pageOperationsDTO);
		bankAccountHistory.setBalance(bankAccount.getBalance());
		bankAccountHistory.setId(bankAccount.getId());
		bankAccountHistory.setPage(page);
		bankAccountHistory.setSize(size);
		bankAccountHistory.setTotalePages(pageOperations.getTotalPages());
		return bankAccountHistory;
		}

	@Override
	public List<CustomerDTO> searchCustomers(String keyword) {
		List<Customer> customerList = customerRepository.searchCustomer(keyword);
		List<CustomerDTO> customerDTOList = customerList.stream().map(customer -> dtoMapper.fromCustomer(customer)).collect(Collectors.toList());
		return customerDTOList;
	}
	
}
