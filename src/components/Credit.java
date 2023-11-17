//1.3.3 Creation of the Transfert, Credit, Debit classes
package components;

public class Credit extends FlowClass{

	public Credit(String comment, double amount, int accountNumber, boolean effect) {
		super(comment, amount, accountNumber, effect);
	}

}
