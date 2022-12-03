package ge.softgen.softlab.workshop.softlabjavaworkshop1.Service;

import ge.softgen.softlab.workshop.softlabjavaworkshop1.Entity.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    List<Post> getAllPosts();
    void addPost(Post post);
    Post getPostByID(Integer id);
    void updatePostById(Post post, Integer id);
    void deletePostById(Integer id);
}
