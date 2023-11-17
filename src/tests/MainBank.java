//1.1.2 Creation of main class for tests
package tests;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

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
	//1.3.1 Adaptation of the table of accounts
	static Hashtable<Integer, Account> hashAccount = new Hashtable<>();
	

	public static void main(String[] args) {

		// I'm creating 10 clients for example
		clientsList = loadClientsList(10);
		displayClients(clientsList);
		//1.2.3 Creation of the table account
		accountsList = loadAccountsList(clientsList);
		displayAccounts(accountsList);
		//1.3.1 Adaptation of the table of accounts
		hashAccount = createHashTable(accountsList);
		showHashSorted();
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
	//1.2.3 Creation of the table account
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
	
	
	//1.3.1 Adaptation of the table of accounts METHODS
	public static Hashtable<Integer, Account> createHashTable(ArrayList<Account> accounts) {
		Hashtable<Integer, Account> hashTable = new Hashtable<>();
		for (Account account : accounts) {
			hashTable.put(account.getAccountNumber(), account);
		}
		return hashTable;
	}
	
	public static void showHashSorted() {
		System.out.println("LIST OF ACCOUNTS SORTED BY BALANCE");
		System.out.println("------------------------------------------");
		System.out.println("Account\tLabel\tBalance\tClient");
		hashAccount.entrySet().stream()
        .sorted(Map.Entry.comparingByValue((account1, account2) -> Double.compare(account1.getBalance(), account2.getBalance())))
        .forEach(accountHash -> System.out.println(accountHash.getValue().toString()));
	}
	
}
