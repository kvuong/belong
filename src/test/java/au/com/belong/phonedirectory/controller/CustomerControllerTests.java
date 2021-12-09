package au.com.belong.phonedirectory.controller;

import au.com.belong.phonedirectory.exception.ResourceNotFoundException;
import au.com.belong.phonedirectory.model.Customer;
import au.com.belong.phonedirectory.model.PhoneNumber;
import au.com.belong.phonedirectory.model.Status;
import au.com.belong.phonedirectory.service.PhoneDirectoryService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CustomerControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PhoneDirectoryService phoneDirectoryService;

    private List<Customer> customerList;

    @BeforeAll
    public void setupTest() {
        customerList = new ArrayList<>();
        customerList.add(new Customer(1001L, "John", "Doe", null));
        customerList.add(new Customer(1002L, "Jane", "Doe", null));
    }

    @Test
    public void getCustomerPhoneNumbers_shouldReturnCustomerPhoneNumbers() throws Exception {
        // Given
        Set<PhoneNumber> phoneNumbers = new HashSet<>();
        phoneNumbers.add(new PhoneNumber(1001L, "0400111222", Status.inactive));
        phoneNumbers.add(new PhoneNumber(1001L, "0400111333", Status.inactive));
        customerList.get(0).setPhoneNumbers(phoneNumbers);

        // When
        when(phoneDirectoryService.getCustomer(anyLong())).thenReturn(customerList.get(0));

        // Then
        mockMvc.perform(get("/v1/customers/1001"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getCustomerPhoneNumbers_withIdNotExists_shouldReturn404() throws Exception {
        // When
        when(phoneDirectoryService.getCustomer(anyLong()))
                .thenThrow(new ResourceNotFoundException("Customer not found."));

        mockMvc.perform(get("/v1/customers/1004"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}