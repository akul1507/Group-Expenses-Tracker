package splitExpense.group;

import splitExpense.person.Person;
import java.util.ArrayList;

public class Transaction {
	private Person payer;
	private String description;
	private double amount;
	private ArrayList<Person> membersInvolved;
	
	public Transaction(Person payer, String description, double amount, ArrayList<Person> membersInvolved) {
		this.payer = payer;
		this.amount = amount;
		this.description = description; 
		this.membersInvolved = membersInvolved;
	}

	public Person getPayer() {
		return this.payer;
	}

	public ArrayList<Person> getMembersInvolved() {
		return new ArrayList<Person> (this.membersInvolved);
	}

	public String getTransactionString() {
		if (payer != null && description != null)
			return (payer.getName() + " added '" + description + "' which cost $" + amount);

		return "No Transaction Record";
	}

	@Override
	public String toString() {
		return this.getTransactionString();
	}
}
