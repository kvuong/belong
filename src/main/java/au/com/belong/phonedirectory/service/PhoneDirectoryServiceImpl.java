package au.com.belong.phonedirectory.service;

import au.com.belong.phonedirectory.exception.ResourceNotFoundException;
import au.com.belong.phonedirectory.model.Customer;
import au.com.belong.phonedirectory.model.PhoneNumber;
import au.com.belong.phonedirectory.model.Status;
import au.com.belong.phonedirectory.repository.CustomerRepository;
import au.com.belong.phonedirectory.repository.PhoneNumberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneDirectoryServiceImpl implements PhoneDirectoryService {

    private final CustomerRepository customerRepository;
    private final PhoneNumberRepository phoneNumberRepository;

    public PhoneDirectoryServiceImpl(final CustomerRepository customerRepository,
                                     final PhoneNumberRepository phoneNumberRepository) {
        this.customerRepository = customerRepository;
        this.phoneNumberRepository = phoneNumberRepository;
    }

    @Override
    public Customer getCustomer(Long id) {
        return customerRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Customer not found."));
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public List<PhoneNumber> getAllPhoneNumbers() {
        return phoneNumberRepository.findAll();
    }

    @Override
    public void activatePhoneNumber(Long phoneNumberId) {
        PhoneNumber phoneNumber = phoneNumberRepository.findById(phoneNumberId).orElseThrow(() ->
                new ResourceNotFoundException("Phone number not found."));

        phoneNumber.setStatus(Status.active);
        phoneNumberRepository.save(phoneNumber);
    }
}