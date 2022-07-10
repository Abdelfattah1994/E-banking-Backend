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
@DiscriminatorValue(value="SA")
@Data @AllArgsConstructor @NoArgsConstructor
public class SavingAccount extends BankAccount {

	private double interestRate;

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public SavingAccount(String id, double balance, Date createdAT, AccountStatus status, Customer customer,
			List<AccountOperation> accountOperations, double interestRate) {
		super(id, balance, createdAT, status, customer, accountOperations);
		this.interestRate = interestRate;
	}

	public SavingAccount() {
	}
	
}
