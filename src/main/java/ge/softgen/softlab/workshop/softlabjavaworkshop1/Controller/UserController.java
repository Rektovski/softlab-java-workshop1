package ge.softgen.softlab.workshop.softlabjavaworkshop1.Controller;

import ge.softgen.softlab.workshop.softlabjavaworkshop1.Entity.Post;
import ge.softgen.softlab.workshop.softlabjavaworkshop1.Entity.User;
import ge.softgen.softlab.workshop.softlabjavaworkshop1.Service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public List<User> getAllUser() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserByID(@PathVariable Integer id){
        return userService.getUserById(id);
    }

    @GetMapping("/{id}/posts")
    public List<Post> getPostsOfUser(@PathVariable Integer id){
        return userService.getUserPosts(id);
    }

    @PostMapping()
    public void addPost(@RequestBody User user){
        userService.addUser(user);
    }

    @PutMapping("/{id}")
    public void updateUserById(@RequestBody User user, @PathVariable Integer id){
        userService.updateUserByID(user, id);
    }

    @DeleteMapping("/{id}")
    public void deletePostByID(@PathVariable Integer id){
        userService.deleteUserByID(id);
    }
}