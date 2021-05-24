package com.example.profilesystem;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Matchers.anyObject;

import com.example.profilesystem.exception.InvalidInputException;
import com.example.profilesystem.pojo.CustomerProfile;
import com.example.profilesystem.pojo.Product;
import com.example.profilesystem.repository.CustomerRepository;
import com.example.profilesystem.repository.ProductRepository;
import com.example.profilesystem.validation.ProductValidation;

@RunWith(MockitoJUnitRunner.class)
public class ValidationTest  {

	@InjectMocks
	@Autowired
	ProductValidation productValidation;

	@Mock
	CustomerRepository customerRepository;

	@Mock
	ProductRepository productRepository;

	Optional<CustomerProfile> customerProfileOptional;
	Optional<Product> prodcutOptionalEmpty;


	Product product;

	@Before
	public void setup() {

		Product product = new Product();
		product.setProductId(1);
		product.setProductName("testncccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc");

		customerProfileOptional = Optional.empty();
		prodcutOptionalEmpty = Optional.of(product);
		when(productRepository.findById(anyObject())).thenReturn(prodcutOptionalEmpty);

		when(customerRepository.findById(anyObject())).thenReturn(customerProfileOptional);

	}

	@Test(expected = InvalidInputException.class)
	public void testValidateProduct() throws Exception {
		productValidation.validateProduct(product);

	}

	@Test(expected = InvalidInputException.class)
	public void testValidateBidDetailsForProduct() throws Exception {

	}

}
