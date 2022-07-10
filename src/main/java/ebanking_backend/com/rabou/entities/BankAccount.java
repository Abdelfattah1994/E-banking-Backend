package ebanking_backend.com.rabou.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import ebanking_backend.com.rabou.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE", length=4)
@Data @AllArgsConstructor @NoArgsConstructor
public abstract class BankAccount {
	@Id
	private String id;
	private double balance;
	private Date createdAT;
	@Enumerated(EnumType.STRING)
	private AccountStatus status;
	@ManyToOne
	private Customer customer;
	@OneToMany(mappedBy = "bankAccount", fetch=FetchType.LAZY)
	private List<AccountOperation> accountOperations;
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
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public List<AccountOperation> getAccountOperations() {
		return accountOperations;
	}
	public void setAccountOperations(List<AccountOperation> accountOperations) {
		this.accountOperations = accountOperations;
	}
	public BankAccount(String id, double balance, Date createdAT, AccountStatus status, Customer customer,
			List<AccountOperation> accountOperations) {
		this.id = id;
		this.balance = balance;
		this.createdAT = createdAT;
		this.status = status;
		this.customer = customer;
		this.accountOperations = accountOperations;
	}
	public BankAccount() {
	}
	
}
