package au.com.belong.phonedirectory.controller;

import au.com.belong.phonedirectory.service.PhoneDirectoryService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1")
public class PhoneNumberController {

    private final PhoneDirectoryService phoneDirectoryService;

    public PhoneNumberController(final PhoneDirectoryService phoneDirectoryService) {
        this.phoneDirectoryService = phoneDirectoryService;
    }

    @GetMapping(value = "/phone_numbers", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getAllPhoneNumbers() {
        return ResponseEntity.ok(phoneDirectoryService.getAllPhoneNumbers());
    }

    @PostMapping(value = "/phone_numbers/{phone_number_id}/activate")
    public ResponseEntity activatePhoneNumber(@PathVariable final Long phone_number_id) {
        phoneDirectoryService.activatePhoneNumber(phone_number_id);
        return ResponseEntity.ok().build();
    }
}