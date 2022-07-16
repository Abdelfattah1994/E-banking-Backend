package ebanking_backend.com.rabou.dtos;

import java.util.List;

public class BankAccountHistory {

	private String id;
	private double balance;
	private int page;
	private int totalePages;
	private int size;
	private List<AccountOperationDTO> accountOperations;
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
	public List<AccountOperationDTO> getAccountOperations() {
		return accountOperations;
	}
	public void setAccountOperations(List<AccountOperationDTO> accountOperations) {
		this.accountOperations = accountOperations;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotalePages() {
		return totalePages;
	}
	public void setTotalePages(int totalePages) {
		this.totalePages = totalePages;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
}
