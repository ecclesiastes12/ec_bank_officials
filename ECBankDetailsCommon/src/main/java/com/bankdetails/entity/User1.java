//package com.bankdetails.entity;
//
//import java.util.HashSet;
//import java.util.Iterator;
//import java.util.Set;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.FetchType;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.JoinTable;
//import jakarta.persistence.ManyToMany;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.OneToOne;
//import jakarta.persistence.Table;
//import jakarta.persistence.Transient;
//
//@Entity
//@Table(name="users")
//public class User1 {
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
//	@Column(length = 128, unique = true, nullable = false)
//	private String email;
//	
//	@Column(name="identity_card",length = 15, unique = true, nullable = false)
//	private String identityCard;
//
//	@Column(name = "phone_number",length = 15, nullable = false)
//	private String phoneNumber;
//	
//	@Column(length = 64, nullable = false)
//	private String password;
//	
//	//user status
//	@Column(name="status")
//	private boolean enabled;
//	
//	@ManyToMany(fetch = FetchType.EAGER)
//	@JoinTable(
//			name = "user_role",
//			joinColumns = @JoinColumn(name = "user_id"),
//			inverseJoinColumns = @JoinColumn(name = "role_id")
//			)
//	Set<Role> roles = new HashSet<>();
//	
//	@OneToOne
//    @JoinColumn(name = "user_bank_details_id")
//    private UserBankDetails1 userBankDetails;
//
//    @ManyToOne
//    @JoinColumn(name = "district_id")
//    private District1 district;
//	
//	
//	public User1() {
//		super();
//	}	
//
//	
//
//	public User1(String firstName, String lastName, String email, String identityCard, String phoneNumber,
//			String password, District1 district) {
//		super();
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.email = email;
//		this.identityCard = identityCard;
//		this.phoneNumber = phoneNumber;
//		this.password = password;
//		this.district = district;
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
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//	
//	public String getIdentityCard() {
//		return identityCard;
//	}
//
//	public void setIdentityCard(String identityCard) {
//		this.identityCard = identityCard;
//	}
//
//	public String getPhoneNumber() {
//		return phoneNumber;
//	}
//
//	public void setPhoneNumber(String phoneNumber) {
//		this.phoneNumber = phoneNumber;
//	}
//
//	public boolean isEnabled() {
//		return enabled;
//	}
//
//	public void setEnabled(boolean enabled) {
//		this.enabled = enabled;
//	}
//	
//	//method that get user full name
//	@Transient
//	public String getFullName() {
//		return firstName + " " + lastName;
//	}
//	
//	public Set<Role> getRoles() {
//		return roles;
//	}
//
//	public void setRoles(Set<Role> roles) {
//		this.roles = roles;
//	}
//	
//
//	public District1 getDistrict() {
//		return district;
//	}
//
//	public void setDistrict(District1 district) {
//		this.district = district;
//	}
//
//	
//	public UserBankDetails1 getUserBankDetails() {
//		return userBankDetails;
//	}
//
//	public void setUserBankDetails(UserBankDetails1 userBankDetails) {
//		this.userBankDetails = userBankDetails;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//	
//	
//	@Override
//	public String toString() {
//		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
//				+ ", identityCard=" + identityCard + ", phoneNumber=" + phoneNumber + ", password=" + password
//				+ ", enabled=" + enabled + "]";
//	}
//
//	
//	//method that add role to the user
//	public void addRoleToUser(Role role) {
//		this.roles.add(role);
//	}
//
//	//method that check role name of the user
//	public boolean hasRole(String roleName) {
//		//get all roles objects thus set of role by iteration
//		Iterator<Role> iterator = roles.iterator();
//		
//		//iterate over the roles
//		while (iterator.hasNext()) {
//			//gets the role object of the next element in the iterator
//			Role role = iterator.next();
//			
//			//check if the role name is equal to the paramter roleName
//			if(role.getName().equals(roleName)) {
//				return true;
//			}
//		}
//		return false;
//	}
//	
//	
//
//	
//	
//}
