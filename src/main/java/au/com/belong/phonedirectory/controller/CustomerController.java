package au.com.belong.phonedirectory.controller;

import au.com.belong.phonedirectory.service.PhoneDirectoryService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1")
public class CustomerController {

    private final PhoneDirectoryService phoneDirectoryService;

    public CustomerController(final PhoneDirectoryService phoneDirectoryService) {
        this.phoneDirectoryService = phoneDirectoryService;
    }

    /**
     * Get customer based upon Id
     * @param customer_id customer id
     * @return customer.
     */
    @GetMapping(value = "/customers/{customer_id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getCustomerPhoneNumbers(final @PathVariable long customer_id) {
        return ResponseEntity.ok(phoneDirectoryService.getCustomer(customer_id));
    }

    /**
     * Get all customers.
     * @return List of customers.
     */
    @GetMapping(value = "/customers", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getAllCustomers() {
        return ResponseEntity.ok(phoneDirectoryService.getAllCustomers());
    }
}
