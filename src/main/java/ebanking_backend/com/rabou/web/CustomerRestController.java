package ebanking_backend.com.rabou.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ebanking_backend.com.rabou.dtos.CustomerDTO;
import ebanking_backend.com.rabou.exceptions.CustomerNotFoundException;
import ebanking_backend.com.rabou.services.BankAccountServiceImpl;

@RestController
public class CustomerRestController {

	@Autowired
	BankAccountServiceImpl bankAccountServiceImpl;
	
	@GetMapping("/customers")
	public List<CustomerDTO> getCustomers(){
		return bankAccountServiceImpl.listCustomers();
	}
	
	@GetMapping("/customers/{id}")
	public CustomerDTO getCustomer(@PathVariable(name="id") Long customerId) throws CustomerNotFoundException {
		return bankAccountServiceImpl.getCustomer(customerId);
	}
	@PostMapping("/customers")
	public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
		return bankAccountServiceImpl.saveCustomer(customerDTO);
	}
	@PutMapping("/customers/{customerId}")
	public CustomerDTO updateCustomer(@PathVariable Long customerId,@RequestBody CustomerDTO customerDTO) {
		customerDTO.setId(customerId);
		return bankAccountServiceImpl.updateCustomer(customerDTO);
	}
	@DeleteMapping("/customers/{customerId}")
	public void deleteCustomer(@PathVariable Long customerId){
		bankAccountServiceImpl.deleteCustomer(customerId);
	}
}
