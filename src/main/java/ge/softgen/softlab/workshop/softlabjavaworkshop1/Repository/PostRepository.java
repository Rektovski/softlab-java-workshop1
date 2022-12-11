package ge.softgen.softlab.workshop.softlabjavaworkshop1.Repository;

import ge.softgen.softlab.workshop.softlabjavaworkshop1.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    @Query("select post from Post post where post.active=true")
    List<Post> getAllActivePosts();
}
