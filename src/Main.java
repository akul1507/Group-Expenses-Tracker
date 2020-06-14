import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import splitExpense.group.*;
import splitExpense.person.*;


public class Main {

	public static void main(String[] args) {
		// Create few persons
		Person p1 = new Person("Person1");
		Person p2 = new Person("Person2");
		Person p3 = new Person("Person3");
		Person p4 = new Person("Person4");
		Person p5 = new Person("Person5");
		
		//Create few groups
		Group g1 = new Group("Group1");
		Group g2 = new Group("Group2");
		
		//Add members in group1
		g1.addGroupMember(p1);
		g1.addGroupMember(p2);
		g1.addGroupMember(p3);
		g1.addGroupMember(p4);
		
		//Add members in group2
		g2.addGroupMember(p1);
		g2.addGroupMember(p4);
		g2.addGroupMember(p5);
		
		//Print groups of different members
		System.out.print(p1.getGroups().toString());
		System.out.print(p2.getGroups().toString());
		System.out.print(p3.getGroups().toString());
		System.out.print(p4.getGroups().toString());
		System.out.print(p5.getGroups().toString());
		
		Transaction t1= new Transaction(p1,"Item1",50.0, new ArrayList<Person>(List.of(p1,p2,p3)));
		Transaction t2= new Transaction(p3,"Item1",40.0, new ArrayList<Person>(List.of(p2,p3,p4)));
		Transaction t3= new Transaction(p1,"Item1",100.0, new ArrayList<Person>(List.of(p1,p4,p5)));
		Transaction t4= new Transaction(p5,"Item1",23.0, new ArrayList<Person>(List.of(p5,p2,p4)));
		
		//Add transactions
		g1.addGroupTransactions(t1);
		g1.addGroupTransactions(t2);
		
		g2.addGroupTransactions(t3);
		g2.addGroupTransactions(t4);
		
		//Printing Transactions
		for(Transaction transaction:g1.getTransactions()) {
			System.out.println(transaction.toString());
		}
		
		for(Transaction transaction:g2.getTransactions()) {
			System.out.println(transaction.toString());
		}
		
		//Show Balances of a person with other persons
		for(Person p: List.of(p1,p2,p3,p4,p5)) {
			System.out.println(p.getName() + " owes: ");
			HashMap<Person,Double> map= p.getAmountOwedAfterAdjustments();
			for (Map.Entry<Person,Double> entry : map.entrySet())  
	            System.out.println(entry.getKey().getName() + " $" + entry.getValue());
		}
		
		//Delete a transaction and check for balances
		
		g2.deleteGroupTransaction(new ArrayList<Transaction>(List.of(t4)));
		
		for(Person p: List.of(p1,p2,p3,p4,p5)) {
			System.out.println(p.getName() + " owes: ");
			HashMap<Person,Double> map= p.getAmountOwedAfterAdjustments();
			for (Map.Entry<Person,Double> entry : map.entrySet())  
	            System.out.println(entry.getKey().getName() + " $" + entry.getValue());
		}
		
	}

}
