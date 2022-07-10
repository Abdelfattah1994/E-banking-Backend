package ebanking_backend.com.rabou.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ebanking_backend.com.rabou.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
