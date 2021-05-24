package com.example.profilesystem.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.profilesystem.exception.InvalidInputException;
import com.example.profilesystem.exception.MyException;
import com.example.profilesystem.exception.ResourceNotFoundException;
import com.example.profilesystem.pojo.CustomerProfile;
import com.example.profilesystem.pojo.Product;
import com.example.profilesystem.repository.CustomerRepository;
import com.example.profilesystem.repository.ProductRepository;
import com.example.profilesystem.validation.AddressValidation;
import com.example.profilesystem.validation.CustomerProfileValidation;
import com.example.profilesystem.validation.ProductValidation;

@RestController
public class CustomerController {

	@Autowired
	ProductValidation productValidation;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	CustomerProfileValidation productBusinessProfileValidation;

	@Autowired
	AddressValidation addressValidation;

	@PostMapping("/addCustomer")
	@Transactional
	public ResponseEntity<String> addCustomerProfile(@RequestBody CustomerProfile customerProfile) {
		try {
			productValidation.validateUser(customerProfile);
			productValidation.checkUserAlreadyExistOrNot(customerProfile.getCustomerId());
			productBusinessProfileValidation.validateBusinessProfile(customerProfile);
			addressValidation.validateAddress(customerProfile.getBusinessAddress());
			addressValidation.validateAddress(customerProfile.getBusinessAddress());
			customerRepository.save(customerProfile);

		} catch (MyException myException) {
			return ResponseEntity.status(HttpStatus.OK).body(myException.getErrorMessage());
		} catch (InvalidInputException invalidInputException) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(invalidInputException.getErrorMessage());
		}
		return ResponseEntity.status(HttpStatus.CREATED).body("Succesfully added User");
	}

	@PutMapping("/updateCustomer/{id}")
	@Transactional
	public ResponseEntity<String> updateCustomerProfile(@PathVariable("id") Integer customerId,
			@RequestBody CustomerProfile customerProfile) throws InvalidInputException {
		if (customerId == null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer Id can not be null");
		try {

			CustomerProfile updatedcustomer = customerRepository.findById(customerId)
					.orElseThrow(() -> new ResourceNotFoundException(
							"Customere you are trying to update  for this id :: does not exist" + customerId));

			List<Product> productsSuscribed = productRepository.findProductSubscribedByCustomer(customerId);

			productValidation.applyProductSpecificValidations(productsSuscribed);
			productBusinessProfileValidation.validateBusinessProfile(customerProfile);
			addressValidation.validateAddress(customerProfile.getBusinessAddress());
			addressValidation.validateAddress(customerProfile.getBusinessAddress());
			if (customerProfile.getCustomerName() != null)
				updatedcustomer.setCustomerName(customerProfile.getCustomerName());

			if (customerProfile.getPassword() != null)
				updatedcustomer.setPassword(customerProfile.getPassword());

			if (customerProfile.getCompanyName() != null)
				updatedcustomer.setCompanyName(customerProfile.getCompanyName());

			if (customerProfile.getBusinessAddress() != null)
				updatedcustomer.setBusinessAddress(customerProfile.getBusinessAddress());

			if (customerProfile.getLegalName() != null)
				updatedcustomer.setLegalName(customerProfile.getLegalName());

			if (customerProfile.getLegalAddress() != null)
				updatedcustomer.setLegalAddress(customerProfile.getLegalAddress());

			if (customerProfile.getPhoneNumber() != null)
				updatedcustomer.setPhoneNumber(customerProfile.getPhoneNumber());

			if (customerProfile.getPanNumber() != null)
				updatedcustomer.setPanNumber(customerProfile.getPanNumber());

			if (customerProfile.getWebsite() != null)
				updatedcustomer.setWebsite(customerProfile.getWebsite());

			if (customerProfile.getEmail() != null)
				updatedcustomer.setEmail(customerProfile.getEmail());

			// perform validation with products
			customerRepository.save(updatedcustomer);

		}

		catch (ResourceNotFoundException resourceNotFoundException) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resourceNotFoundException.getErrorMessage());
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Succesfully updated User");
	}

	@GetMapping("/getSubscribedProduct/{id}")
	public List<Product> getAllProducts(@PathVariable("id") Integer customerId) {
		return productRepository.findProductSubscribedByCustomer(customerId);
	}

}
