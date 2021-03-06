package hotelsolution.guestserviceaxon.query.repository;

import hotelsolution.guestserviceaxon.query.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends JpaRepository<Guest, String> {

}
