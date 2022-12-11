package ge.softgen.softlab.workshop.softlabjavaworkshop1.Service;

import ge.softgen.softlab.workshop.softlabjavaworkshop1.Entity.Post;
import ge.softgen.softlab.workshop.softlabjavaworkshop1.Exception.PostNotFoundException;
import ge.softgen.softlab.workshop.softlabjavaworkshop1.Repository.PostRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class PostServiceImplement implements PostService {
    final PostRepository postRepository;

    public PostServiceImplement(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    @Override
    public List<Post> getAllPosts() {
        return postRepository.getAllActivePosts();
    }

    @Override
    public Post getPostByID(Integer id) {
        return checkAndGetPost(id);
    }

    @Override
    public void addPost(Post post) {
        post.setActive(true);
        post.setCreateDate(Timestamp.from(Instant.now()));
        postRepository.save(post);
    }

    @Override
    public void updatePostById(Post post, Integer id) {
        var foundedPost = checkAndGetPost(id);
        foundedPost.setTitle(post.getTitle());
        foundedPost.setBody(post.getBody());
        foundedPost.setCreateDate(Timestamp.from(Instant.now()));
        postRepository.save(foundedPost);
    }

    @Override
    public void deletePostById(Integer id) {
        var foundedPost = checkAndGetPost(id);
        foundedPost.setActive(false);
        postRepository.save(foundedPost);
    }

    public Post checkAndGetPost(Integer id) {
        if(postRepository.findById(id).isPresent()){
            var optional = postRepository.findById(id).get();
            if(!optional.getActive()) {
                throw new PostNotFoundException("Post is deleted");
            }
        }
        return postRepository
                .findById(id)
                .orElseThrow(() -> new PostNotFoundException("Post not found!"));
    }
}
