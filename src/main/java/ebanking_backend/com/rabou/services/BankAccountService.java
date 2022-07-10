package ebanking_backend.com.rabou.services;

import java.util.List;

import ebanking_backend.com.rabou.dtos.BankAccountDTO;
import ebanking_backend.com.rabou.dtos.CurrentBankAccountDTO;
import ebanking_backend.com.rabou.dtos.CustomerDTO;
import ebanking_backend.com.rabou.dtos.SavingBankAccountDTO;
import ebanking_backend.com.rabou.entities.BankAccount;
import ebanking_backend.com.rabou.exceptions.BalanceNotSufficientException;
import ebanking_backend.com.rabou.exceptions.BankAccountNotFoundException;
import ebanking_backend.com.rabou.exceptions.CustomerNotFoundException;

public interface BankAccountService {

	CustomerDTO saveCustomer(CustomerDTO customerDTO);
	List<CustomerDTO> listCustomers();
	BankAccountDTO getBankAccount(String accoutnId) throws BankAccountNotFoundException;
	CurrentBankAccountDTO saveCurrentBankAccount(double initialBalance, double overDraft,Long customerId) throws CustomerNotFoundException;
	SavingBankAccountDTO saveSavingBankAccount(double initialBalance, double interestRate,Long customerId) throws CustomerNotFoundException;
	void debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException;
	void credit(String accountId, double amount, String description) throws BankAccountNotFoundException;
	void transfer(String accountIdSource, String accountIdDestination, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException;
	List<BankAccountDTO> bankAccountList();
	CustomerDTO getCustomer(Long customerId) throws CustomerNotFoundException;
	CustomerDTO updateCustomer(CustomerDTO customerDTO);
	void deleteCustomer(Long CustomerId);
}
