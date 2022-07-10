package ebanking_backend.com.rabou.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ebanking_backend.com.rabou.entities.BankAccount;

public interface BankAccountRepository extends JpaRepository<BankAccount, String> {

}
