package com.example.profilesystem.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.profilesystem.exception.InvalidInputException;
import com.example.profilesystem.exception.MyException;
import com.example.profilesystem.pojo.CustomerProfile;
import com.example.profilesystem.repository.CustomerRepository;
import com.example.profilesystem.repository.ProductRepository;

@Service
public class CustomerProfileValidation {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	ProductRepository productRepository;

	
	public void checkUserAlreadyExistOrNot(Integer customerId) throws MyException {
		if (customerRepository.findById(customerId).isPresent())
			throw new MyException("CustomerId already exist please give different userid");
	}

	public void checkProductIdIAlreadyPresentOrNot(Integer productId) throws MyException {
		if (productRepository.findById(productId).isPresent())
			throw new MyException("ProductId already exist please give different ProductId");
	}

	public void validateBusinessProfile(CustomerProfile customerProfile) throws InvalidInputException {

		if (customerProfile.getCompanyName() == null || customerProfile.getLegalName() == null ||customerProfile.getPhoneNumber() == null
				|| customerProfile.getEmail() == null || customerProfile.getBusinessAddress() == null)
			throw new InvalidInputException("company name ,legalName ,phone number ,email and address are mandatory  fields");

		validatePhoneNumber(customerProfile.getPhoneNumber());
		validatePassword(customerProfile.getPassword());
		validateEmail(customerProfile.getEmail());
		validateWebsite(customerProfile.getWebsite());

	}

	public void validatePhoneNumber(String phoneNumber) throws InvalidInputException {
		String regex = "(0/91)?[7-9][0-9]{9}";
		if (!phoneNumber.matches(regex))
			throw new InvalidInputException(
					"Phone Number should be starting with 0/91 and should have digits between 0-9");
	}

	public void validatePassword(String password) throws InvalidInputException {
		/*
		 * It contains at least 8 characters and at most 20 characters. It contains at
		 * least one digit. It contains at least one upper case alphabet. It contains at
		 * least one lower case alphabet. It contains at least one special character
		 * which includes !@#$%&*()-+=^. It doesn’t contain any white space.
		 */
		String regex = "^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$).{8,20}$";
		if (!password.matches(regex))
			throw new InvalidInputException(
					"Password should contains at least 8 characters and at most 20 characters. It contains at"
							+ "		 * least one digit or  at least one upper case alphabet or at\r\n"
							+ "		 * least one lower case alphabet or  at least one special character\r\n"
							+ "		 * which includes !@#$%&*()-+=^. It dshould not contain any white space");
	}

	public void validateEmail(String email) throws InvalidInputException {
		String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
		if (!email.matches(regex))
			throw new InvalidInputException(
					"Email should contain A-Z characters \r\n" + "or a-z characters \r\n" + "or 0-9 numbers \r\n"
							+ "or Additionally email may contain only dot(.), dash(-) and underscore(_ ");
	}

	public void validateWebsite(String website) throws InvalidInputException {
		String URL_REGEX = "^((((https?|ftps?|gopher|telnet|nntp)://)|(mailto:|news:))"
				+ "(%[0-9A-Fa-f]{2}|[-()_.!~*';/?:@&=+$,A-Za-z0-9])+)" + "([).!';/?:,][[:blank:]])?$";
		if (!website.matches(URL_REGEX))
			throw new InvalidInputException("website isn't valid");

	}

	public void validatePanNumber(String panNumber) throws InvalidInputException {
		String regex = "[A-Z]{5}[0-9]{4}[A-Z]{1}";
		if (!panNumber.matches(regex))
			throw new InvalidInputException("Pan Number should be ten characters long \n"
					+ "The first five characters should be any upper case alphabets.\r\n"
					+ "The next four-characters should be any number from 0 to 9.\r\n"
					+ "The last(tenth) character should be any upper case alphabet\r\n"
					+ "It should not contain any white spaces.");

	}
}
