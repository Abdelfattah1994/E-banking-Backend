package ebanking_backend.com.rabou;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import ebanking_backend.com.rabou.entities.BankAccount;
import ebanking_backend.com.rabou.entities.CurrentAccount;
import ebanking_backend.com.rabou.entities.Customer;
import ebanking_backend.com.rabou.entities.SavingAccount;
import ebanking_backend.com.rabou.enums.AccountStatus;
import ebanking_backend.com.rabou.enums.OperationType;
import ebanking_backend.com.rabou.exceptions.BalanceNotSufficientException;
import ebanking_backend.com.rabou.exceptions.BankAccountNotFoundException;
import ebanking_backend.com.rabou.exceptions.CustomerNotFoundException;
import ebanking_backend.com.rabou.repositories.AccountOperationRepository;
import ebanking_backend.com.rabou.repositories.BankAccountRepository;
import ebanking_backend.com.rabou.repositories.CustomerRepository;
import ebanking_backend.com.rabou.services.BankAccountService;

@SpringBootApplication
public class EBankingBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EBankingBackendApplication.class, args);
	}
	/*
	@Bean
	CommandLineRunner start (BankAccountService bankAccountService) {
		
		return args ->{
			Stream.of("Ahmed","Manar","Samira").forEach(name->{
				CustomerDTO customerDTO = new CustomerDTO();
				customer.setName(name);
				customer.setEmail(name+"@gmail.com");
				bankAccountService.saveCustomer(customer);
			});
		
			bankAccountService.listCustomers().forEach(customer->{
				try {
					bankAccountService.saveCurrentBankAccount(10000+Math.random()*90000, 8000, customer.getId());
					bankAccountService.saveSavingBankAccount(10000+Math.random()*70000, 3.5, customer.getId());
		        	List<BankAccount> bankAccountList;
			        bankAccountList = bankAccountService.bankAccountList();
			        for(BankAccount b : bankAccountList) {
			            for(int i=0 ; i < 10 ; i++) {
				             bankAccountService.credit(b.getId(), 9000+Math.random()*2000, "credit");
				             bankAccountService.debit(b.getId(), 2000+Math.random()*1000, "debit");
			               }
			           }
				     } catch (CustomerNotFoundException | BankAccountNotFoundException | BalanceNotSufficientException e) {
					        e.printStackTrace();
				        }
			});
			 
	       
		};
	
	}
*/
}
