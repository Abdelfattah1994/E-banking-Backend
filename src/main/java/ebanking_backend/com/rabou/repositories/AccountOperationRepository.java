package ebanking_backend.com.rabou.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ebanking_backend.com.rabou.entities.AccountOperation;

public interface AccountOperationRepository extends JpaRepository<AccountOperation, Long> {

}
