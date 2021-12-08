package au.com.belong.phonedirectory.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneNumber {

    @Id
    @GeneratedValue
    private Long id;

    private String number;

    @Enumerated(EnumType.STRING)
    private Status status;
}
