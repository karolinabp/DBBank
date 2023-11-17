//1.1.2 Creation of main class for tests
package tests;

import java.util.ArrayList;

import components.Account;
import components.Client;
import components.CurrentAccount;
import components.SavingsAccount;

public class MainBank {

	public static int clientNumber = 0;
	public static int accountNumber = 0;

	// I use ArrayList because is easy to keep data
	static ArrayList<Client> clientsList = new ArrayList<Client>();
	static ArrayList<Account> accountsList = new ArrayList<Account>();
	

	public static void main(String[] args) {

		// I'm creating 10 clients for example
		clientsList = loadClientsList(10);
		displayClients(clientsList);
		//1.2.3 Creation of the tablea account
		accountsList = loadAccountsList(clientsList);
		displayAccounts(accountsList);

	}
	
	
	// CLIENTS METHODS
	// Method to create as much clients as we want
	public static ArrayList<Client> loadClientsList(int clientsAmount) {
		ArrayList<Client> clients = new ArrayList<Client>();
		for (int i = 1; i <= clientsAmount; i++) {
			Client client = new Client("name" + i, "lastName" + i);
			clients.add(client);
		}
		return clients;
	}

	public static void displayClients(ArrayList<Client> clients) {
		System.out.println("LIST OF CLIENTS");
		System.out.println("--------------------------");
		System.out.println("Number\tName\tLast name");
		for (Client client : clients) {
			System.out.println(client.toString());
		}
		System.out.println("");
	}
	
	// ACCOUNTS METHODS
	//1.2.3 Creation of the tablea account
	public static ArrayList<Account> loadAccountsList(ArrayList<Client> clients) {
		ArrayList<Account> accounts = new ArrayList<Account>();
		for (Client client : clients) {
			CurrentAccount currentAc = new CurrentAccount("Current", client);
			accounts.add(currentAc);
			SavingsAccount savingsAc = new SavingsAccount("Savings", client);
			accounts.add(savingsAc);
		}
		return accounts;
	}
	
	public static void displayAccounts(ArrayList<Account> accounts) {
		System.out.println("LIST OF ACCOUNTS");
		System.out.println("------------------------------------------");
		System.out.println("Number\tLabel\tBalance\tClient");
		for (Account account : accounts) {
			System.out.println(account.toString());
		}
		System.out.println("");
	}

}
