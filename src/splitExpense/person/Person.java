package splitExpense.person;

import splitExpense.group.Transaction;
import splitExpense.group.GroupList;
import java.util.ArrayList;
import java.util.HashMap;

public class Person {
	private String name;
	private double amountToRecieve;
	private ArrayList<Transaction> transactions;
	// HashMap to store the mapping of amount the person owes to other group members.
	private HashMap<Person, Double> amountOwed; 
	// HashMap to store the mapping of final amount the person owes to other group members
	// after adjusting all transactions.
	private HashMap<Person, Double> amountOwedAfterAdjustments;
	private GroupList groups;
	
	
	public Person(String name) {
		this.name = name;
		this.amountToRecieve = 0;
		this.transactions = new ArrayList<>();
		this.amountOwed = new HashMap<Person, Double>();
		this.amountOwedAfterAdjustments = new HashMap<Person, Double>();
		this.groups = new GroupList();
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double getAmountToRecieve() {
		return this.amountToRecieve;
	}

	public void setAmountToRecieve(double amountToRecieve) {
		this.amountToRecieve = amountToRecieve;
	}

	public GroupList getGroups() {
		return this.groups;
	}


	public ArrayList<Transaction> getTransactions() {
		return new ArrayList<Transaction> (this.transactions);
	}
	
	public HashMap<Person, Double> getAmountOwed() {
		return new HashMap<Person, Double> (this.amountOwed);
	}

	public HashMap<Person, Double> getAmountOwedAfterAdjustments() {
		return new HashMap<Person, Double> (this.amountOwedAfterAdjustments);
	}

	public void addTransaction(Transaction transaction) {
		this.transactions.add(transaction);
	}
	
	public void addAmountOwed(Person p, double amount) {

		if (this.amountOwed.containsKey(p)) {
			this.amountOwed.put(p, this.amountOwed.get(p) + amount);
			return;
		}
		this.amountOwed.putIfAbsent(p, amount);
	}

	public void subtractAmountOwed(Person p, double amount) {

		if (this.amountOwed.containsKey(p)) {
			this.amountOwed.put(p, this.amountOwed.get(p) - amount);
		}

	}

	public void editAmountOwedAfterAdjustments(Person p, double amount) {

		if (this.amountOwedAfterAdjustments.containsKey(p)) {
			this.amountOwedAfterAdjustments.put(p, amount);
			return;
		}
		this.amountOwedAfterAdjustments.putIfAbsent(p, amount);
	}
}
