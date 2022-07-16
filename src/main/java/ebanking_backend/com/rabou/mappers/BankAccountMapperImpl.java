package ebanking_backend.com.rabou.mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import ebanking_backend.com.rabou.dtos.AccountOperationDTO;
import ebanking_backend.com.rabou.dtos.CurrentBankAccountDTO;
import ebanking_backend.com.rabou.dtos.CustomerDTO;
import ebanking_backend.com.rabou.dtos.SavingBankAccountDTO;
import ebanking_backend.com.rabou.entities.AccountOperation;
import ebanking_backend.com.rabou.entities.CurrentAccount;
import ebanking_backend.com.rabou.entities.Customer;
import ebanking_backend.com.rabou.entities.SavingAccount;

@Service
public class BankAccountMapperImpl {

	public CustomerDTO fromCustomer(Customer customer) {
		CustomerDTO customerDTO = new CustomerDTO();
		BeanUtils.copyProperties(customer, customerDTO);
		return customerDTO;
	}
	
	public Customer fromCustomerDTO(CustomerDTO customerDTO) {
		Customer customer = new Customer();
		BeanUtils.copyProperties(customerDTO, customer);
		return customer;
	}
	
	public SavingBankAccountDTO fromSavingBankAccount(SavingAccount savingBankAccount) {
		SavingBankAccountDTO savingBankAccountDTO = new SavingBankAccountDTO();
		BeanUtils.copyProperties(savingBankAccount, savingBankAccountDTO);
		savingBankAccountDTO.setCustomerDTO(fromCustomer(savingBankAccount.getCustomer()));
		savingBankAccountDTO.setType(savingBankAccount.getClass().getSimpleName());
		return savingBankAccountDTO;
	}
	public SavingAccount fromSavingBankAccountDTO(SavingBankAccountDTO savingBankAccountDTO) {
		SavingAccount savingBankAccount = new SavingAccount();
		BeanUtils.copyProperties(savingBankAccountDTO, savingBankAccount);
		savingBankAccount.setCustomer(fromCustomerDTO(savingBankAccountDTO.getCustomerDTO()));
		return savingBankAccount;
	}
	public CurrentBankAccountDTO fromCurrentBankAccount(CurrentAccount currentBankAccount) {
		CurrentBankAccountDTO currentBankAccountDTO = new CurrentBankAccountDTO();
		BeanUtils.copyProperties(currentBankAccount, currentBankAccountDTO);
		currentBankAccountDTO.setCustomerDTO(fromCustomer(currentBankAccount.getCustomer()));
		currentBankAccountDTO.setType(currentBankAccount.getClass().getSimpleName());
		return currentBankAccountDTO;
	}
	public CurrentAccount fromCurrentBankAccountDTO(CurrentBankAccountDTO currentBankAccountDTO) {
		CurrentAccount currentBankAccount = new CurrentAccount();
		BeanUtils.copyProperties(currentBankAccountDTO, currentBankAccount);
		currentBankAccount.setCustomer(fromCustomerDTO(currentBankAccountDTO.getCustomerDTO()));
		return currentBankAccount;
	}
	
	public AccountOperationDTO fromAccountOperation(AccountOperation accountOperation) {
		AccountOperationDTO accountOperationDTO = new AccountOperationDTO();
		BeanUtils.copyProperties(accountOperation, accountOperationDTO);
		return accountOperationDTO;
	}
}
