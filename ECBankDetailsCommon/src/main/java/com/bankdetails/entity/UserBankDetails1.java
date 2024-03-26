//package com.bankdetails.entity;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.OneToOne;
//import jakarta.persistence.Table;
//import jakarta.persistence.Transient;
//
//@Entity
//@Table(name = "user_bank_details")
//public class UserBankDetails1 {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Integer id;
//	
//	@Column(name = "first_name", length = 45, nullable = false)
//	private String firstName;
//	
//	@Column(name = "last_name", length = 45, nullable = false)
//	private String lastName;
//	
//	@Column(unique = true, nullable = false)
//	private String accountNumber;
//	
//	@Column(nullable = false)
//	private String bankName;
//	
//	@Column(nullable = false)
//	private String branch;
//	
//	@OneToOne(mappedBy = "userBankDetails")
//    private User1 user;
//
//    @ManyToOne
//    @JoinColumn(name = "district_id")
//    private District1 district;
//
//	public UserBankDetails1() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//
//	public UserBankDetails1(String firstName, String lastName, String accountNumber, String bankName, String branch) {
//		super();
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.accountNumber = accountNumber;
//		this.bankName = bankName;
//		this.branch = branch;
//	}
//
//	public Integer getId() {
//		return id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}
//
//	public String getFirstName() {
//		return firstName;
//	}
//
//	public void setFirstName(String firstName) {
//		this.firstName = firstName;
//	}
//
//	public String getLastName() {
//		return lastName;
//	}
//
//	public void setLastName(String lastName) {
//		this.lastName = lastName;
//	}
//
//	public String getAccountNumber() {
//		return accountNumber;
//	}
//
//	public void setAccountNumber(String accountNumber) {
//		this.accountNumber = accountNumber;
//	}
//
//	public String getBranch() {
//		return branch;
//	}
//
//	public void setBranch(String branch) {
//		this.branch = branch;
//	}
//
//	public String getBankName() {
//		return bankName;
//	}
//
//	public void setBankName(String bankName) {
//		this.bankName = bankName;
//	}
//
//	public User1 getUser() {
//		return user;
//	}
//
//	public void setUser(User1 user) {
//		this.user = user;
//	}
//
//	public District1 getDistrict() {
//		return district;
//	}
//
//	public void setDistrict(District1 district) {
//		this.district = district;
//	}
//
//	@Override
//	public String toString() {
//		return "UserBankDetails [firstName=" + firstName + ", lastName=" + lastName + ", accountNumber=" + accountNumber
//				+ ", bankName=" + bankName + ", branch=" + branch + "]";
//	}
//
//	@Transient
//	public String getFullName() {
//		return  firstName + " " + lastName;
//	}
//	
//}
