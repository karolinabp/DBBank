//1.3.2 Creation of the Flow class
package components;

import java.time.LocalDate;

import tests.MainBank;

public abstract class FlowClass {
	
	private int id;
	private String comment;
	private double amount;
	private int accountNumber;
	private boolean effect;
	private LocalDate date;

	
	//CONSTRUCTOR
	public FlowClass(String comment, double amount, int accountNumber, boolean effect) {
		MainBank.flowNumber++;
		this.id = MainBank.flowNumber;
		this.comment = comment;
		this.amount = amount;
		this.accountNumber = accountNumber;
		this.effect = effect;
		this.date = LocalDate.now();
	}
	
	//GETTERS AND SETTERS
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public boolean isEffect() {
		return effect;
	}
	public void setEffect(boolean effect) {
		this.effect = effect;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	
	
}
