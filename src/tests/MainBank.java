package tests;

import java.util.ArrayList;

import components.Client;

public class MainBank {
	
	public static int clientNumber = 0;
	
	//I use ArrayList because is easy to keep data
	static ArrayList<Client> clientsList = new ArrayList<Client>();

	public static void main(String[] args) {
		
		//I'm creating 10 clients for example
		clientsList = loadClientsList(10);
		displayClients(clientsList);
		

	}
	
	//Method to create as much clients as we want
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
	}

}
