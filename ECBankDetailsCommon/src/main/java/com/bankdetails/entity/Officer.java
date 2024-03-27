package com.bankdetails.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.Version;

@Entity
@Table(name = "officers")
public class Officer {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "other_name")
	private String otherName;
	
	@Column(name = "last_name")
	private String lastName;
	
	private String email;
	private String password;
	
	@Column(name = "identity_card")
	private String identityCard;
	
	@Column(name = "phone_number")
	private String phoneNumber;
	
	//user status
	@Column(name="status")
	private boolean enabled;
		
	@Version
	private Integer version;//for optimistic locking
	
	 @ManyToOne
    @JoinColumn(name = "district_id")
    private District district;
	 
	
	
	  @ManyToOne
	  
	  @JoinColumn(name = "officer_role_id") private OfficerRole officerRole;
	 
	public Officer() {}
	
	
	
	public Officer(String otherName, String lastName, String email, String password, String identityCard,
			String phoneNumber) {
		super();
		
		this.otherName = otherName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.identityCard = identityCard;
		this.phoneNumber = phoneNumber;
	}



	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOtherName() {
		return otherName;
	}
	public void setOtherName(String otherName) {
		this.otherName = otherName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIdentityCard() {
		return identityCard;
	}
	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}
	
	public OfficerRole getOfficerRole() {
		return officerRole;
	}

	public void setOfficerRole(OfficerRole officerRole) {
		this.officerRole = officerRole;
	}

	
	//method that get user full name
		@Transient
		public String getFullName() {
			return otherName + " " + lastName;
		}

		@Override
		public String toString() {
			return "Officer [id=" + id + ", otherName=" + otherName + ", lastName=" + lastName + ", email=" + email
					+ ", password=" + password + ", identityCard=" + identityCard + ", phoneNumber=" + phoneNumber
					+ ", enabled=" + enabled + ", version=" + version + ", district=" + district + "]";
		}
		
		
	
}
