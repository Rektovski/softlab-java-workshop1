package ge.softgen.softlab.workshop.softlabjavaworkshop1.Repository;

import ge.softgen.softlab.workshop.softlabjavaworkshop1.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
