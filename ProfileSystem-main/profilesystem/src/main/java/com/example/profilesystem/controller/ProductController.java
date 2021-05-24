package com.example.profilesystem.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.profilesystem.exception.InvalidInputException;
import com.example.profilesystem.exception.MyException;
import com.example.profilesystem.exception.ResourceNotFoundException;
import com.example.profilesystem.pojo.CustomerProfile;
import com.example.profilesystem.pojo.Product;
import com.example.profilesystem.repository.CustomerRepository;
import com.example.profilesystem.repository.ProductRepository;
import com.example.profilesystem.validation.CustomerProfileValidation;
import com.example.profilesystem.validation.ProductValidation;

@RestController
public class ProductController {
	@Autowired
	ProductValidation subscriptionValidation;
	@Autowired
	CustomerProfileValidation productBusinessProfileValidation;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	CustomerRepository customerRepository;

	@PostMapping("/addProduct")
	@Transactional
	public ResponseEntity<String> addProduct(@RequestBody Product product) {
		try {
			subscriptionValidation.validateProduct(product);
			subscriptionValidation.checkProductIdIsAlreadyPresent(product.getProductId());
			productRepository.save(product);
		} catch (MyException myException) {
			return ResponseEntity.status(HttpStatus.OK).body(myException.getErrorMessage());
		} catch (InvalidInputException invalidInputException) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(invalidInputException.getErrorMessage());
		} catch (Exception exception) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
		}
		return ResponseEntity.status(HttpStatus.CREATED).body("Succesfully Added the Product");
	}

	@PostMapping("/subscribeProduct")
	@Transactional
	public ResponseEntity<String> subscribeProduct(@RequestBody Product product) {
		try {
			Product subscribedProduct = productRepository.findById(product.getProductId()).orElseThrow(
					() -> new ResourceNotFoundException("Product not found for this id :: " + product.getProductId()));
				Integer subscriberId = product.getSubscriberId();
				subscriptionValidation.checkSubscriberExistOrNot(subscriberId);
				subscribedProduct.setSubscribed(true);
				subscribedProduct.setSubscriberId(product.getSubscriberId());
			

			if (product.getProductName() != null)
				subscribedProduct.setProductName(product.getProductName());

			productRepository.save(subscribedProduct);
			// when you try to subscribe a product check if the product exists or not
			// then update the subscriber id and subscribed
		}
		catch(ResourceNotFoundException resourceNotFoundException)
		{
		  return ResponseEntity.status(HttpStatus.OK).body(resourceNotFoundException.getErrorMessage());
		}
		catch (Exception exception) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
		}
		return ResponseEntity.status(HttpStatus.CREATED).body("Succesfully subscribed the Product");
	}

	@GetMapping("/getAllProduct")
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@GetMapping("/getAllSubscribedCustomers/{id}")
	public List<CustomerProfile> getAllCustomersSubscribed(@PathVariable("id") Integer productId) {
		return customerRepository.findCustomerSubscribedToProduct(productId);
	}

}
