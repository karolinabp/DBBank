// 1.1.1 Creation of the client class
package components;

import tests.MainBank;

public class Client {
	
	private String name;
	private String lastName;
	private int number;
	
	
	//CONSTRUCTOR
	public Client(String name, String firstName) {
		MainBank.clientNumber++;
		this.number = MainBank.clientNumber;
		this.name = name;
		this.lastName = firstName;
	}
	
	//GETTES AND SETTERS
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
		return number + "\t" + name + "\t" + lastName;
	}
	
	
}
