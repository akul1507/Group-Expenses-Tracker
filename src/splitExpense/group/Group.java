package splitExpense.group;

import splitExpense.person.Person;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Group {

	private String groupName;
	private HashMap<Integer, Person> groupMembers; //HashMap to map each member to a unique id
	private ArrayList<Transaction> groupTransactions;
	private boolean simplifyCashFlows;

	public Group(String name) {
		this.groupName = name;
		this.groupMembers = new HashMap<>();
		this.groupTransactions = new ArrayList<>();
		this.simplifyCashFlows = false;
	}

	public String getName() {
		return this.groupName;
	}

	public void setName(String newName) {
		this.groupName= newName;
	}

	public boolean isSimplifyCashFlows() {
		return this.simplifyCashFlows;
	}

	public void setSimplifyCashFlows(boolean simplifyCashFlows) {
		this.simplifyCashFlows = simplifyCashFlows;
	}

	public void addGroupMember(Person p) {
		if (this.groupMembers.containsKey(p.getName().hashCode()))
			return;
		this.groupMembers.putIfAbsent(p.getName().hashCode(), p);
	}
	
	public void deleteGroupMember(int key) {
		this.groupMembers.remove(key);
	}

	public ArrayList<Person> getGroupMembers() {
		return new ArrayList<Person>(this.groupMembers.values());
	}

	public HashMap<Integer, Person> getGroupMemmbersMap() {
		return this.groupMembers;
	}
	
	public void addGroupTransactions(Transaction transaction) {
		this.groupTransactions.add(transaction);
		//TODO Make changes in balances
		this.adjustAllTransactions();
		
		if(this.isSimplifyCashFlows() == true)
			this.reduceCashFlows();
	}
	
	public void deleteGroupTransaction(ArrayList<Transaction> transactions) {
		this.groupTransactions.removeAll(transactions);
		//TODO Make changes in balances
		this.adjustAllTransactions();
		
		if(this.isSimplifyCashFlows() == true)
			this.reduceCashFlows();
	}
	
	public ArrayList<Transaction> getTransactions() {
		return new ArrayList<Transaction> (this.groupTransactions);
	}

// Adjust all the transactions
	public void adjustAllTransactions() {

		for (Person debter : this.getGroupMembers()){

			for (Map.Entry<Person, Double> lender : debter.getAmountOwed().entrySet()) {
				Person lenderPerson = lender.getKey();
				double debterOwesToLender = lender.getValue();
				double lenderOwesToDebter = lenderPerson.getAmountOwed().get(debter) != null
						? lenderPerson.getAmountOwed().get(debter)
						: 0;

				if (lenderOwesToDebter > debterOwesToLender) {
					lenderPerson.editAmountOwedAfterAdjustments(debter, lenderOwesToDebter - debterOwesToLender);
					// System.out.println(lenderPerson.getName() + " -> " + debter.getName() + " ->
					// " + (lenderOwesToDebter - debterOwesToLender));
					debter.editAmountOwedAfterAdjustments(lenderPerson, 0);
					// System.out.println(debter.getName() + " -> " + lenderPerson.getName() + " ->
					// " + 0);

				} else if (lenderOwesToDebter < debterOwesToLender) {
					debter.editAmountOwedAfterAdjustments(lenderPerson, debterOwesToLender - lenderOwesToDebter);
					// System.out.println(debter.getName() + " -> " + lenderPerson.getName() + " ->
					// " + (debterOwesToLender - lenderOwesToDebter));
					lenderPerson.editAmountOwedAfterAdjustments(debter, 0);
					// System.out.println(lenderPerson.getName() + " -> " + debter.getName() + " ->
					// " + 0);
				}

				else if (lenderOwesToDebter == debterOwesToLender) {
					// System.out.println("Balancing Out balances");
					debter.editAmountOwedAfterAdjustments(lenderPerson, 0);
					lenderPerson.editAmountOwedAfterAdjustments(debter, 0);
				}

			}
		}
	}
	
	public void reduceCashFlows() {
		//TODO
	}
}