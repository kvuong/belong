package au.com.belong.phonedirectory.controller;

import au.com.belong.phonedirectory.model.PhoneNumber;
import au.com.belong.phonedirectory.model.Status;
import au.com.belong.phonedirectory.service.PhoneDirectoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PhoneNumberController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PhoneNumberControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PhoneDirectoryService phoneDirectoryService;

    @Test
    public void getAllPhoneNumbers_shouldReturnListOfPhoneNumbers() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        List<PhoneNumber> phoneNumbers = new ArrayList<>();
        phoneNumbers.add(new PhoneNumber(1001L, "0400111222", Status.inactive));
        phoneNumbers.add(new PhoneNumber(1002L, "0391112222", Status.active));

        when(phoneDirectoryService.getAllPhoneNumbers()).thenReturn(phoneNumbers);

        MvcResult result = mockMvc.perform(get("/v1/phone_numbers"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String actualResponseBody = result.getResponse().getContentAsString();
        String expectedResponse = objectMapper.writeValueAsString(phoneNumbers);
        assertEquals(expectedResponse, actualResponseBody);
    }
}