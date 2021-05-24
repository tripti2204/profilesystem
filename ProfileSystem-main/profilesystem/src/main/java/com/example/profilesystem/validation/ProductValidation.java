package com.example.profilesystem.validation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.profilesystem.exception.InvalidInputException;
import com.example.profilesystem.exception.MyException;
import com.example.profilesystem.pojo.CustomerProfile;
import com.example.profilesystem.pojo.Product;
import com.example.profilesystem.repository.CustomerRepository;
import com.example.profilesystem.repository.ProductRepository;

@Service
public class ProductValidation {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	ProductRepository productRepository;

	public void checkUserAlreadyExistOrNot(Integer customerId) throws MyException {
		if (customerRepository.findById(customerId).isPresent())
			throw new MyException("UserId already exist please give different userid");
	}

	public void checkProductIdIsAlreadyPresent(Integer productId) throws MyException {
		if (productRepository.findById(productId).isPresent())
			throw new MyException("ProductId already exist please give different ProductId");
	}

	public void checkProductIdINotPresesnt(Integer productId) throws MyException {
		if (!productRepository.findById(productId).isPresent())
			throw new MyException("ProductId you are trying to subscribe does not exist");
	}

	public void checkSubscriberExistOrNot(Integer customerId) throws MyException {
		if (!customerRepository.findById(customerId).isPresent())
			throw new MyException("Subscriber is not present in the system");
	}

	public void validateUser(CustomerProfile customerProfile) throws InvalidInputException {

		if (customerProfile.getPassword() == null || customerProfile.getPassword().length() < 5)
			throw new InvalidInputException("password is manadatory field and its length should be greater than 5");
		if (customerProfile.getCustomerName() == null)
			throw new InvalidInputException("username is manadatory field");
	}

	public void validateProduct(Product product) throws InvalidInputException {
		if (product.getProductId() == null)
			throw new InvalidInputException("productId is manadatory field");

		if (product.getProductName() == null)
			throw new InvalidInputException("Product name is manadatory field");

		else if (product.getProductName().length() > 30)
			throw new InvalidInputException("Product name cannot be greater than 30");

		if (product.getSubscribed() != null) {
			System.out.println("validating the user id of subscriber");
			validateUserId(product.getSubscriberId());
		}

	}

	public void validateUserId(Integer userId) throws InvalidInputException {
		if (userId == null)
			throw new InvalidInputException("subscriber  id is empty  field");

	}

	public void applyProductSpecificValidations(List<Product> productsSuscribed) {
		// TODO Auto-generated method stub
		
	}

}
