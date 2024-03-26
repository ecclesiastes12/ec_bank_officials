//package com.bankdetails.entity;
//
//import java.util.List;
//
//import jakarta.persistence.CascadeType;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.OneToMany;
//import jakarta.persistence.OneToOne;
//import jakarta.persistence.PostPersist;
//
//@Entity(name = "districts")
//public class District1 {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private  Integer id;
//	private String code;
//	private String name;
//	private String region;
//	
//	@OneToOne(mappedBy = "district", cascade = CascadeType.ALL)
//    private User1 user;
//
//    @OneToOne(mappedBy = "district", cascade = CascadeType.ALL)
//    private UserBankDetails1 userBankDetails;
//	
//	public District1() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//
//	public District1(String code, String name, String region) {
//		super();
//		this.code = code;
//		this.name = name;
//		this.region = region;
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
//	public String getCode() {
//		return code;
//	}
//
//	public void setCode(String code) {
//		this.code = code;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getRegion() {
//		return region;
//	}
//
//	public void setRegion(String region) {
//		this.region = region;
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
//	public UserBankDetails1 getUserBankDetails() {
//		return userBankDetails;
//	}
//
//	public void setUserBankDetails(UserBankDetails1 userBankDetails) {
//		this.userBankDetails = userBankDetails;
//	}
//
//	@Override
//	public String toString() {
//		return "District [code=" + code + ", name=" + name + ", region=" + region + "]";
//	}
//	
//	@PostPersist
//    public void initializeAssociations() {
//        // Initialize User
//        User1 newUser = new User1();
//        newUser.setDistrict(this);
//        newUser.setEmail("default_email@example.com");//default user email
//        newUser.setFirstName(null);
//        newUser.setLastName(null);
//        newUser.setPassword(null);
//        newUser.setIdentityCard(null);
//        newUser.setPhoneNumber(null);
//        newUser.setEnabled(false);
//        this.setUser(newUser);
//
//        // Initialize UserBankDetails
//        UserBankDetails1 newUserBankDetails = new UserBankDetails1();
//        newUserBankDetails.setDistrict(this);
//        this.setUserBankDetails(newUserBankDetails);
//    }
//}
