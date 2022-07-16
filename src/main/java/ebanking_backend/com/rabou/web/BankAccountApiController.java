package ebanking_backend.com.rabou.web;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ebanking_backend.com.rabou.dtos.AccountOperationDTO;
import ebanking_backend.com.rabou.dtos.BankAccountDTO;
import ebanking_backend.com.rabou.dtos.BankAccountHistory;
import ebanking_backend.com.rabou.dtos.CreditDTO;
import ebanking_backend.com.rabou.dtos.DebitDTO;
import ebanking_backend.com.rabou.dtos.TransferDTO;
import ebanking_backend.com.rabou.exceptions.BalanceNotSufficientException;
import ebanking_backend.com.rabou.exceptions.BankAccountNotFoundException;
import ebanking_backend.com.rabou.services.BankAccountServiceImpl;
@CrossOrigin
@RestController
public class BankAccountApiController {

	private BankAccountServiceImpl bankAccountServiceImpl;

	public BankAccountApiController(BankAccountServiceImpl bankAccountServiceImpl) {
		this.bankAccountServiceImpl = bankAccountServiceImpl;
	}
	
	@GetMapping("accounts/{id}")
	public BankAccountDTO getBankAccount(@PathVariable(name="id") String accountId) throws BankAccountNotFoundException {
		return bankAccountServiceImpl.getBankAccount(accountId);
	}
	
	@GetMapping("accounts")
	public List<BankAccountDTO> bankAccountList(){
		return bankAccountServiceImpl.bankAccountList();
	}
	
	@GetMapping("accounts/{accountId}/operations")
	public List<AccountOperationDTO> accountOperations(@PathVariable String accountId){
		return bankAccountServiceImpl.accountHistory(accountId);
	}
	
	@GetMapping("accounts/{accountId}/pageOperations")
	public BankAccountHistory bankAccountHistory(@PathVariable String accountId,
			@RequestParam(name="page", defaultValue="0")int page,
			@RequestParam(name="size", defaultValue="5")int size) throws BankAccountNotFoundException {
		return bankAccountServiceImpl.bankAccountHistory(accountId, page, size);
	}
	@PostMapping("account/debit")
	public DebitDTO debit(@RequestBody DebitDTO debitDTO) throws BankAccountNotFoundException, BalanceNotSufficientException {
		bankAccountServiceImpl.debit(debitDTO.getAccountId(), debitDTO.getAmount(), debitDTO.getDescription());
		return debitDTO;
	}
	@PostMapping("account/credit")
	public CreditDTO credit(@RequestBody CreditDTO creditDTO) throws BankAccountNotFoundException {
		bankAccountServiceImpl.credit(creditDTO.getAccountId(), creditDTO.getAmount(), creditDTO.getDescription());
		return creditDTO;
	}
	@PostMapping("account/transfer")
	public void transfer(@RequestBody TransferDTO transferDTO) throws BankAccountNotFoundException, BalanceNotSufficientException {
		bankAccountServiceImpl.transfer(transferDTO.getSourceAccount(), transferDTO.getDestinationAccount(), transferDTO.getAmount());
		
	}
	
}
