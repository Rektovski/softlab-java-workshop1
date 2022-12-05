package ge.softgen.softlab.workshop.softlabjavaworkshop1.Service;

import ge.softgen.softlab.workshop.softlabjavaworkshop1.Entity.Post;
import ge.softgen.softlab.workshop.softlabjavaworkshop1.Entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> getAllUsers();
    void addUser(User user);
    User getUserById(Integer id);
    List<Post> getUserPosts(Integer id);
    void updateUserByID(User user, Integer id);
    void deleteUserByID(Integer id);
}
