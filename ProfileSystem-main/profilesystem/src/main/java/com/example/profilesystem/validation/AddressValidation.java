package com.example.profilesystem.validation;

import org.springframework.stereotype.Service;

import com.example.profilesystem.exception.InvalidInputException;
import com.example.profilesystem.pojo.Address;
@Service
public class AddressValidation {

	public void validateAddress(Address address) throws InvalidInputException {
		if (address == null)
			throw new InvalidInputException("address cannot be null");
		if (address.getLine1() != null)
			validateLine1(address.getLine1());
		if (address.getLine2() != null)
			validateLine2(address.getLine2());
		if (address.getState() != null)
			validateState(address.getState());
		if (address.getCity() != null)
			validateCity(address.getCity());

		if (address.getZipcode() != null)
			validateZipcode(address.getZipcode());
	}

	private void validateZipcode(String zipcode) throws InvalidInputException {
		// TODO Auto-generated method stub
		if (!zipcode.matches("\\d{6}"))
			throw new InvalidInputException("zipcode should be of 6 digits");
	}

	// validate line1
	public void validateLine1(String line1) throws InvalidInputException {
		if (line1.length() > 20)
			throw new InvalidInputException("line1  of address should contain less than 20 charcters");
	}

	// validate line2
	public void validateLine2(String line1) throws InvalidInputException {
		if (line1.length() > 30)
			throw new InvalidInputException("line2  of address should contain less than 30 charcters");
	}

	// validate city
	public void validateCity(String city) throws InvalidInputException {
		if (!(city.matches("([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)")) || city.length() > 20)
			throw new InvalidInputException("city name should only contain letters and less than 20 charcters");
	}

	// validate state
	public void validateState(String state) throws InvalidInputException {
		if (!(state.matches("([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)")) || state.length() > 20)
			throw new InvalidInputException("state name should only contain letters and less than 20 charcters");

	}
}
