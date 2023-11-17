// 1.1.1 Creation of the client class
package components;

import tests.MainBank;

public class Client {
	
	private String name;
	private String firstName;
	private int number;
	
	
	//CONSTRUCTOR
	public Client(String name, String firstName) {
		MainBank.clientNumber++;
		this.number = MainBank.clientNumber;
		this.name = name;
		this.firstName = firstName;
	}
	
	//GETTES AND SETTERS
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	
	//TO STRING METHOD
	@Override
	public String toString() {
		return number + "\t" + name + "\t" + firstName;
	}
	
	
}
