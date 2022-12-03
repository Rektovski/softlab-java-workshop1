package ge.softgen.softlab.workshop.softlabjavaworkshop1.Repository;

import ge.softgen.softlab.workshop.softlabjavaworkshop1.Entity.Post;
import ge.softgen.softlab.workshop.softlabjavaworkshop1.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    List<User> findAllById(Integer id);
}
