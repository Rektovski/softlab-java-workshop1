package ge.softgen.softlab.workshop.softlabjavaworkshop1.Controller;

import ge.softgen.softlab.workshop.softlabjavaworkshop1.Entity.Post;
import ge.softgen.softlab.workshop.softlabjavaworkshop1.Service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    final PostService postService;

    public PostController(@RequestBody PostService postService) {
        this.postService = postService;
    }

    @GetMapping()
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public Post getPostById(@PathVariable Integer id) {
        return postService.getPostByID(id);
    }

    @PostMapping()
    public void addPost(@RequestBody Post post) {
        postService.addPost(post);
    }


    @PutMapping("/{id}")
    public void updatePostById(@RequestBody Post post, @PathVariable Integer id) {
        postService.updatePostById(post, id);
    }

    @DeleteMapping("/{id}")
    public void deletePostById(@PathVariable Integer id) {
        postService.deletePostById(id);
    }
}