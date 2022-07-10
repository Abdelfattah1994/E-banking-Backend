package ebanking_backend.com.rabou.web;

import org.springframework.web.bind.annotation.RestController;

import ebanking_backend.com.rabou.services.BankAccountServiceImpl;

@RestController
public class BankAccountApiController {

	private BankAccountServiceImpl bankAccountServiceImpl;

	public BankAccountApiController(BankAccountServiceImpl bankAccountServiceImpl) {
		this.bankAccountServiceImpl = bankAccountServiceImpl;
	}
	
}
