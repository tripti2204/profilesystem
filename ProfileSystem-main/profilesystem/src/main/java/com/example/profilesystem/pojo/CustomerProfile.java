package com.example.profilesystem.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.example.profilesystem.constants.Constants;

@Entity(name = Constants.BUSINESS_PROFILE_TABLE_NAME)
public class CustomerProfile {

	@Id
	@Column(nullable = false)
	private Integer customerId;

	@Column(nullable = false)
	private String customerName;
	@Column(nullable = false)
	private String phoneNumber;
	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String companyName;

	@Column(nullable = false)
	private String legalName;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id", referencedColumnName = "id")
	private Address businessAddress;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "legal_address_id", referencedColumnName = "id")
	private Address legalAddress;

	@Column(nullable = false)
	private String panNumber;

	@Column(nullable = false)
	private String email;

	@Column
	private String website;

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getLegalName() {
		return legalName;
	}

	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}

	public Address getBusinessAddress() {
		return businessAddress;
	}

	public void setBusinessAddress(Address businessAddress) {
		this.businessAddress = businessAddress;
		this.businessAddress.setLine1(businessAddress.getLine1());
		this.businessAddress.setLine2(businessAddress.getLine2());
		this.businessAddress.setCity(businessAddress.getCity());
		this.businessAddress.setState(businessAddress.getState());
		this.businessAddress.setZipcode(businessAddress.getZipcode());
	}

	public Address getLegalAddress() {
		return legalAddress;
	}

	public void setLegalAddress(Address legalAddress) {
		this.legalAddress = legalAddress;
		this.legalAddress.setLine1(legalAddress.getLine1());
		this.legalAddress.setLine2(legalAddress.getLine2());
		this.legalAddress.setCity(legalAddress.getCity());
		this.legalAddress.setState(legalAddress.getState());
		this.legalAddress.setZipcode(legalAddress.getZipcode());
	}

	public String getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

}
