package hotelsolution.hotelservice.repository;

import hotelsolution.hotelservice.model.entity.Address;
import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, BigInteger> {

}
