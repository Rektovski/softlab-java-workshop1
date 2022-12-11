package ge.softgen.softlab.workshop.softlabjavaworkshop1.Repository;

import ge.softgen.softlab.workshop.softlabjavaworkshop1.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select user from User user where user.active = true ")
    List<User> findAllActive();
}
