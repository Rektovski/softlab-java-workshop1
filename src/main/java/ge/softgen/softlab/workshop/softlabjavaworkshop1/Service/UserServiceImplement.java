package ge.softgen.softlab.workshop.softlabjavaworkshop1.Service;

import ge.softgen.softlab.workshop.softlabjavaworkshop1.Entity.Post;
import ge.softgen.softlab.workshop.softlabjavaworkshop1.Entity.User;
import ge.softgen.softlab.workshop.softlabjavaworkshop1.Exception.UserNotFoundException;
import ge.softgen.softlab.workshop.softlabjavaworkshop1.Repository.PostRepository;
import ge.softgen.softlab.workshop.softlabjavaworkshop1.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImplement implements UserService {
    final UserRepository userRepository;
    final PostRepository postRepository;

    public UserServiceImplement(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAllActive();
    }

    @Override
    public User getUserById(Integer id) {
        return checkAndGetUser(id);
    }

    @Override
    public void addUser(User user) {
        user.setActive(true);
        userRepository.save(user);
    }

    @Override
    public void updateUserByID(User user, Integer id) {
        var foundedUser = checkAndGetUser(id);
        foundedUser.setUsername(user.getUsername());
        foundedUser.setPassword(user.getPassword());
        foundedUser.setEmail(user.getEmail());
        foundedUser.setCreateDate(user.getCreateDate()); // Timestamp
        userRepository.save(foundedUser);
    }

    @Override
    public void deleteUserByID(Integer id) {
        var foundedUser = checkAndGetUser(id);
        foundedUser.setActive(false);
        userRepository.save(foundedUser);
    }

    @Override
    public List<Post> getUserPosts(Integer id) {
        return postRepository.findAll().stream()
                .filter(post -> Objects.equals(post.getUserId(), id))
                .toList();
    }

    public User checkAndGetUser(Integer id) {
        if(userRepository.findById(id).isPresent()){
            var optional = userRepository.findById(id).get();
            if(!optional.getActive()) {
                throw new UserNotFoundException("User is deleted!");
            }
        }
        return userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));
    }

}
