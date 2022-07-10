package ebanking_backend.com.rabou.dtos;

import java.util.Date;

import ebanking_backend.com.rabou.entities.Customer;
import ebanking_backend.com.rabou.enums.AccountStatus;

public class CurrentBankAccountDTO extends BankAccountDTO {

	private String id;
	private double balance;
	private Date createdAT;
	private AccountStatus status;
	private CustomerDTO customerDTO;
	private double overDraft;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public Date getCreatedAT() {
		return createdAT;
	}
	public void setCreatedAT(Date createdAT) {
		this.createdAT = createdAT;
	}
	public AccountStatus getStatus() {
		return status;
	}
	public void setStatus(AccountStatus status) {
		this.status = status;
	}


	public CustomerDTO getCustomerDTO() {
		return customerDTO;
	}
	public void setCustomerDTO(CustomerDTO customerDTO) {
		this.customerDTO = customerDTO;
	}
	public double getOverDraft() {
		return overDraft;
	}
	public void setOverDraft(double overDraft) {
		this.overDraft = overDraft;
	}

}
