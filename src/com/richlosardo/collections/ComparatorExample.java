package com.richlosardo.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.richlosardo.Account;

public class ComparatorExample {

	public ComparatorExample() {
	}
	
	public void createAndSort() {
		Account account1 = new Account(1, "Rich", "Losardo", "Brooklyn", "NY");
		Account account2 = new Account(2, "John", "Doe", "Staten Island", "NY");
		Account account3 = new Account(3, "Mary", "Smith", "NY", "NY");
		Account account4 = new Account(4, "Paul", "Jones", "Brooklyn", "NY");
		Account account5 = new Account(5, "James", "White", "Bronx", "NY");
		Account account6 = new Account(6, "Barbara", "White", "Bronx", "NY");
		Account account7 = new Account(7, "Jennifer", "Black", "Staten Island", "NY");
		Account account8 = new Account(8, "Robert", "Johnson", "NY", "NY");
		Account account9 = new Account(9, "Jesse", "Green", "Edison", "NJ");
		Account account10 = new Account(10, "Daniel", "Jackson", "Trenton", "NJ");
		List<Account> accountList = new ArrayList<Account>();
		accountList.add(account1);
		accountList.add(account2);
		accountList.add(account3);
		accountList.add(account4);
		accountList.add(account5);
		accountList.add(account6);
		accountList.add(account7);
		accountList.add(account8);
		accountList.add(account9);
		accountList.add(account10);
		//sort by State, City, Last Name, First Name with a custom comparator:
		Collections.sort(accountList, new Comparator<Account>() {
			@Override
			public int compare(Account o1, Account o2) {
				int compare = o1.getState().compareTo(o2.getState());
				if (compare == 0) {
					compare = o1.getCity().compareTo(o2.getCity());
				}
				if (compare == 0) {
					compare = o1.getLastName().compareTo(o2.getLastName());
				}
				if (compare == 0) {
					compare = o1.getFirstName().compareTo(o2.getFirstName());
				}
				return compare;
			}
		});
		for (Account account : accountList) {
			System.out.println(account);
		}
	}
	
	public static void main(String[] args) {
		ComparatorExample comparatorExample = new ComparatorExample();
		comparatorExample.createAndSort();
	}
}
