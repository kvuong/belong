package au.com.belong.phonedirectory.repository;

import au.com.belong.phonedirectory.model.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Long>  {
}
