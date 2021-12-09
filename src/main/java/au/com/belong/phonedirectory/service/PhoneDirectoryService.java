package au.com.belong.phonedirectory.service;

import au.com.belong.phonedirectory.model.Customer;
import au.com.belong.phonedirectory.model.PhoneNumber;

import java.util.List;

public interface PhoneDirectoryService {

    Customer getCustomer(final Long id);

    List<Customer> getAllCustomers();

    List<PhoneNumber> getAllPhoneNumbers();

    void activatePhoneNumber(final Long phoneNumberId);
}
