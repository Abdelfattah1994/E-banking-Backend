package ebanking_backend.com.rabou.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import ebanking_backend.com.rabou.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@DiscriminatorValue(value = "CA")
@Data @AllArgsConstructor @NoArgsConstructor
public class CurrentAccount extends BankAccount {

	private double overDraft;

	public double getOverDraft() {
		return overDraft;
	}

	public void setOverDraft(double overDraft) {
		this.overDraft = overDraft;
	}

	public CurrentAccount(double overDraft) {
		this.overDraft = overDraft;
	}

	public CurrentAccount() {
	}

	public CurrentAccount(String id, double balance, Date createdAT, AccountStatus status, Customer customer,
			List<AccountOperation> accountOperations, double overDraft) {
		super(id, balance, createdAT, status, customer, accountOperations);
		this.overDraft = overDraft;
	}
	
}
