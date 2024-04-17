package application;

import java.util.*;

public class Customer implements Comparable<Customer> {
	String name;
	String address;
	String plan;
	String id;
	String mobile;
	int LimitedValue;

	ArrayList<String> interested = new ArrayList();
	ArrayList<String> rented = new ArrayList();

	public Customer(String name, String address, String plan, String id, String mobile) {
		this.name = name;
		this.address = address;
		this.plan = plan;
		this.id = id;
		this.mobile = mobile;
	}

	public int getLimitedValue() {
		return LimitedValue;
	}

	public void setLimitedValue(int limitedValue) {
		this.LimitedValue = limitedValue;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	public String getAddress() {
		return address;
	}

	public String getPlan() {
		return plan;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public ArrayList<String> getInterested() {
		return interested;
	}

	public ArrayList<String> getRented() {
		return rented;
	}

	public void setInterested(ArrayList<String> interested) {
		this.interested = interested;
	}

	public void setRented(ArrayList<String> rented) {
		this.rented = rented;
	}

	@Override
	public int compareTo(Customer o) {
		if (name.compareTo(o.getName()) > 0)
			return 1;
		else if (name.compareTo(o.getName()) < 0)
			return -1;
		else
			return 0;
	}

	@Override
	public String toString() {
		return "ID : " + id + " || " + "Name : " + name + " || " + "Address : " + address + " || " + "\n" + "Plan : "
				+ plan + " || " + "Mobile : " + mobile + "\n"
				+ "_____________________________________________________________________________ \n";
	}

	public String printIntrested() {
		return "ID : " + id + " || Interested Cart : " + interested;
	}

	public String printRented() {
		return "ID : " + id + " || Rented Cart : " + rented;
	}

}
