//1.2.1 Creation of the account class
package components;

import tests.MainBank;

public abstract class Account {
	
	protected int accountNumber;
	protected String label;
	protected double balance;
	protected Client client;
	
	
	//CONSTRUCTOR
	public Account(String label, Client client) {
		MainBank.accountNumber++;
		this.accountNumber = MainBank.accountNumber;
		this.label = label;
		this.client = client;
	}
	
	//GETTERS AND SETTERS
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public String toString() {
		return accountNumber + "\t" 
				+ label + "\t" 
				+ balance + "€\t" 
				+ client.getName() + "\t" 
				+ client.getLastName();
	}
	
	
	
}
