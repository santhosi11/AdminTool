package com.acc.java.bean;

public class AdminBean {
	//private int id;
	private String firstName;
	private String middleName;
	private String lastName;
	private String dob;
	private String email;
	private String phoneNumber;
	private String address;
	private String occupation;
	/*	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}*/
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	
	@Override
	/*id=" + id*/
	public String toString() {
		return "AdminBean [  firstName=" + firstName + ", middleName=" + middleName + ", lastName="
				+ lastName + ", dob=" + dob + ", email=" + email + ", phoneNumber=" + phoneNumber + ", address="
				+ address + ", occupation=" + occupation + "]";
	}


}
