package com.example.profilesystem.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;

import com.example.profilesystem.constants.Constants;

@Entity(name = Constants.PRODUCT_TABLE_NAME)
public class Product {
	@Id
	@Column(nullable = false)
	private Integer productId;

	@Column(nullable = false)
	private String productName;

	@Column
	private Boolean subscribed;

	@Column
	private Integer subscriberId;

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Boolean getSubscribed() {
		return subscribed;
	}

	public void setSubscribed(Boolean subscribed) {
		this.subscribed = subscribed;
	}

	public Integer getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(Integer subscriberId) {
		this.subscriberId = subscriberId;
	}

}
