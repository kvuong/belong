package au.com.belong.phonedirectory.service;

import au.com.belong.phonedirectory.exception.ResourceNotFoundException;
import au.com.belong.phonedirectory.model.Customer;
import au.com.belong.phonedirectory.model.PhoneNumber;
import au.com.belong.phonedirectory.model.Status;
import au.com.belong.phonedirectory.repository.CustomerRepository;
import au.com.belong.phonedirectory.repository.PhoneNumberRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PhoneDirectoryServiceImplTests {

    private static Long CUSTOMERID = 1001L;
    private static Long INVALID_CUSTOMERID = 2001L;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private PhoneNumberRepository phoneNumberRepository;

    @InjectMocks
    private PhoneDirectoryServiceImpl phoneDirectoryService;

    @Test
    public void whenFindCustomerById_thenReturnCustomer() {
        // Given
        Customer expectedCustomer = new Customer(CUSTOMERID, "John", "Doe", null);

        // When
        when(customerRepository.findById(CUSTOMERID)).thenReturn(Optional.of(expectedCustomer));
        Customer actualCustomer = phoneDirectoryService.getCustomer(CUSTOMERID);

        // Then
        assertNotNull(actualCustomer);
        assertEquals(expectedCustomer.getId(), actualCustomer.getId());
    }

    @Test
    public void whenGetAllPhoneNumbers_thenReturnAllPhoneNumbers() {
        // Given
        List<PhoneNumber> allPhoneNumbers = createListOfPhoneNumbers();

        // When
        when(phoneNumberRepository.findAll()).thenReturn(allPhoneNumbers);
        List<PhoneNumber> phoneNumbers = phoneDirectoryService.getAllPhoneNumbers();

        // Then
        assertEquals(2, phoneNumbers.size());
    }

    @Test
    public void givenPhoneNumberExist_whenActivatePhoneNumber_thenPhoneNumberStatusActivated() {
        // Given
        List<PhoneNumber> allPhoneNumbers = createListOfPhoneNumbers();

        // When
        when(phoneNumberRepository.findById(anyLong())).thenReturn(Optional.ofNullable(allPhoneNumbers.get(0)));
        phoneDirectoryService.activatePhoneNumber(anyLong());
    }

    @Test
    public void whenGetCustomerWithInvalidId_thenResourceNotFoundException() {
        Exception exception = assertThrows(ResourceNotFoundException.class, () ->
                phoneDirectoryService.getCustomer(INVALID_CUSTOMERID));

        assertTrue(exception instanceof ResourceNotFoundException);
    }

    @Test
    public void whenActivatePhoneNumberThatNotExist_thenResourceNotFoundException() {
        Exception exception = assertThrows(ResourceNotFoundException.class, () ->
                phoneDirectoryService.activatePhoneNumber(anyLong()));

        assertTrue(exception instanceof ResourceNotFoundException);
    }

    private List<PhoneNumber> createListOfPhoneNumbers() {
        return new ArrayList<>(Arrays.asList(
                new PhoneNumber(1001L, "0400111222", Status.active),
                new PhoneNumber(1002L, "04111222333", Status.active)
        ));
    }
}
