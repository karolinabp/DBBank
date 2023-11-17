//1.3.3 Creation of the Transfer, Credit, Debit classes
package components;

public class Transfer extends FlowClass{
	
	private int issuingAccountNumber;
	

	public Transfer(String comment, double amount, int accountNumber, boolean effect, int issuingAccountNumber) {
		super(comment, amount, accountNumber, effect);
		this.issuingAccountNumber = issuingAccountNumber;
	}

	//GETTERS AND SETTERS

	public int getIssuingAccountNumber() {
		return issuingAccountNumber;
	}

	public void setIssuingAccountNumber(int issuingAccountNumber) {
		this.issuingAccountNumber = issuingAccountNumber;
	}
	
	

}
