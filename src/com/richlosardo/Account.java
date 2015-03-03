package com.richlosardo;

public class Account {
	
	private Integer id;
	private String firstName;
	private String lastName;
	private String city;
	private String state;
	
	public Account(Integer id, String firstName, String lastName, String city, String state) {
		setId(id);
		setFirstName(firstName);
		setLastName(lastName);
		setCity(city);
		setState(state);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(firstName);
		sb.append(" ");
		sb.append(lastName);
		sb.append(" ");
		sb.append(city);
		sb.append(", ");
		sb.append(state);
		return sb.toString();
	}

}
