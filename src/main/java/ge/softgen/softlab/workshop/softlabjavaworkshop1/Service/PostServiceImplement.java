package ge.softgen.softlab.workshop.softlabjavaworkshop1.Service;

import ge.softgen.softlab.workshop.softlabjavaworkshop1.Entity.Post;
import ge.softgen.softlab.workshop.softlabjavaworkshop1.Exception.PostNotFoundException;
import ge.softgen.softlab.workshop.softlabjavaworkshop1.Repository.PostRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PostServiceImplement implements PostService {
    final PostRepository postRepository;

    public PostServiceImplement(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    @Override
    public List<Post> getAllPosts() {
        var posts = postRepository.findAll();
        posts = posts.stream().filter(post -> post.getActive()).toList();
        return posts;
    }

    @Override
    public Post getPostByID(Integer id) {
        return checkAndGetPost(id);
    }

    @Override
    public void addPost(Post post) {
        post.setActive(true);
        post.setCreateDate(LocalDate.now());
        postRepository.save(post);
    }

    @Override
    public void updatePostById(Post post, Integer id) {
        var foundedPost = checkAndGetPost(id);

        foundedPost.setTitle(post.getTitle());
        foundedPost.setBody(post.getBody());
        foundedPost.setCreateDate(LocalDate.now());

        postRepository.save(foundedPost);
    }

    @Override
    public void deletePostById(Integer id) {
        var foundedPost = checkAndGetPost(id);
        foundedPost.setActive(false);
        postRepository.save(foundedPost);
//        postRepository.deleteById(id);
    }

    public Post checkAndGetPost(Integer id) {
        return postRepository
                .findById(id)
                .orElseThrow(() -> new PostNotFoundException("Post not found!"));
    }
}
