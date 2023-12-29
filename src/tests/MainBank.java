//1.1.2 Creation of main class for tests
package tests;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;


import javax.xml.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;

//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import components.Account;
import components.Client;
import components.Credit;
import components.CurrentAccount;
import components.Debit;
import components.FlowClass;
import components.SavingsAccount;
import components.Transfer;

public class MainBank {

	public static int clientNumber = 0;
	public static int accountNumber = 0;
	public static int flowNumber = 0;

	// I use ArrayList because is easy to keep data
	static ArrayList<Client> clientsList = new ArrayList<Client>();
	static ArrayList<Account> accountsList = new ArrayList<Account>();
	//1.3.4 Creation of the flow array
	static ArrayList<FlowClass> flowsList = new ArrayList<FlowClass>();
	//1.3.1 Adaptation of the table of accounts
	public static Hashtable<Integer, Account> hashAccount = new Hashtable<>();
	

	public static void main(String[] args) {

		// I'm creating 10 clients for example
		clientsList = loadClientsList(4);
		displayClients(clientsList);
		//1.2.3 Creation of the table account
		accountsList = loadAccountsList(clientsList);
		displayAccounts(accountsList);
		//1.3.1 Adaptation of the table of accounts
		hashAccount = createHashTable(accountsList);
		showHashSorted();
		
		//1.3.4 Creation of the flow array
		flowsList = loadFlowsList();
		//1.3.5 Updating accounts
		updateBalances(flowsList, hashAccount);
		displayAccounts(accountsList);
		showHashSorted();
		
		//I used this code to probe that the flows are updated correctly
//		for (FlowClass flow : flowsList) {
//			System.out.println(flow.toString());
//		}
		
		//2.1 JSON file of flows
		String jsonPath = "";
		//loadFlowsFromJsonFile(jsonPath);
		
		
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
		System.out.println("");
	}
	
	//1.3.4 Creation of the flow array
	public static ArrayList<FlowClass> loadFlowsList(){
		ArrayList<FlowClass> flows = new ArrayList<FlowClass>();
		//a debit of 50€ from account n°1
		Debit debit1 = new Debit("Debit", 50, 1, true);
		flows.add(debit1);
		
		for (Account account : accountsList) {
			//A credit of 100.50€ on all current accounts in the array of accounts
			if (account.getLabel().equalsIgnoreCase("Current")) {
				Credit credit = new Credit("Credit", 100.50, account.getAccountNumber(), true);
				flows.add(credit);
			}
			//A credit of 1500€ on all savings accounts in this same array
			else if (account.getLabel().equalsIgnoreCase("Savings")) {
				Credit credit = new Credit("Credit", 1500, account.getAccountNumber(), true);
				flows.add(credit);
			}
		}
		
		//A transfer of 50 € from account n ° 1 to account n ° 2
		Transfer transfer1 = new Transfer("Transfer", 50, 2, true, 1);
		flows.add(transfer1);
		
		return flows;
	}
	
	//1.3.5 Updating accounts
	
	public static void updateBalances(ArrayList<FlowClass> flows, Hashtable<Integer, Account> hashAccount) {
		
		 for (FlowClass flow : flows) {
			//If the flow is a transfer it will make 2 operations 1 add and 1 remove
			if(flow instanceof Transfer) {
				Transfer transfer = (Transfer) flow;
				hashAccount.get(transfer.getIssuingAccountNumber()).setBalance(flow);
			}
			hashAccount.get(flow.getAccountNumber()).setBalance(flow);
		 }
		 
		
		//Using Optional Predicate and Steam
		boolean hasNegativeBalance = flows.stream().peek(flow -> {
	            
	        }).flatMap(flow -> hashAccount.values().stream()).anyMatch(account -> account.getBalance() < 0); 
		
	    Optional.of(hasNegativeBalance).filter(Predicate.isEqual(true))
		    .ifPresent(result -> System.out.println("Account with a negative balance"));
		
	}
	
	public static ArrayList<Account> fillAccountCollectionByXml() {
		ArrayList<Account> accountCollection = new ArrayList<>();

		File file = new File("src/ressources/accountCollection.xml");
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {

			DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = db.parse(file);
			doc.getDocumentElement().normalize();
			NodeList nodeList = doc.getElementsByTagName("account");
			Element eElement;
			Client tmpClient;
			for (int itr = 0; itr < nodeList.getLength(); itr++) {
				Node node = nodeList.item(itr);
				System.out.println("\nNode Name :" + node.getNodeName());
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					eElement = (Element) node;
					tmpClient = new Client(eElement.getElementsByTagName("name").item(0).getTextContent(),
							eElement.getElementsByTagName("firstName").item(0).getTextContent());
		
					accountCollection.add(new Account(eElement.getElementsByTagName("label").item(0).getTextContent(),tmpClient) {
					}) ;

				}
			}

		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return accountCollection;
	}
	
	//2.1 JSON file of flows
//	public static ArrayList<FlowClass> fillFlowCollectionJson() {
//		ArrayList<FlowClass> flows = new ArrayList<>();
//		try {
////			System.out.println("fillFlowJson");
//
//			JSONParser jsonParser = new JSONParser();
//			FileReader fileReader = new FileReader("src/ressources/flowCollection.json");
//			JSONArray jsArr = (JSONArray) jsonParser.parse(fileReader);
//
////			System.out.println("arr");
////			System.out.println(jsArr);
//
//			jsArr.forEach(data -> {
//				try {
//					flows.add(parseFlow((JSONObject) data));
//				} catch (ParseException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			});
//
//		} catch (Exception e) {
//			System.out.println("An error occurred.");
//			e.printStackTrace();
//		}
//		return flows;
//
//	}
	
//	public static FlowClass parseFlow(JSONObject data) throws ParseException {
//		FlowClass flow;
//		switch ((String) data.get("identifier")) {
//		case "transfer": {
//
//			flow = new Transfer((String) data.get("comment"),(Double) data.get("amount"), (int) (long) data.get("tragetAccountNumber"),
//					(boolean) data.get("effect"),
//					(int) (long) data.get("transferingAccountNumber"));
//			break;
//		}
//		case "credit": {
//			flow = new Credit((String) data.get("comment"),(Double) data.get("amount"), (int) (long) data.get("tragetAccountNumber"),
//					(boolean) data.get("effect"))
//					;
//			break;
//		}
//		case "debit": {
//			flow = new Debit((String) data.get("comment"),(Double) data.get("amount"), (int) (long) data.get("tragetAccountNumber"),
//					(boolean) data.get("effect")
//					);
//			break;
//		}
//		default:
//			throw new IllegalArgumentException("Unexpected value: " + data.get("identifier"));
//		}
//		return flow;
//
//	}
	
	
	
}
