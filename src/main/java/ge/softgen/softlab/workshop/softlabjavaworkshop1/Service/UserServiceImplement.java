package ge.softgen.softlab.workshop.softlabjavaworkshop1.Service;

import ge.softgen.softlab.workshop.softlabjavaworkshop1.Entity.User;
import ge.softgen.softlab.workshop.softlabjavaworkshop1.Exception.PostNotFoundException;
import ge.softgen.softlab.workshop.softlabjavaworkshop1.Exception.UserNotFoundException;
import ge.softgen.softlab.workshop.softlabjavaworkshop1.Repository.PostRepository;
import ge.softgen.softlab.workshop.softlabjavaworkshop1.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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
        var users = userRepository.findAll();
        users = users.stream().filter(user -> user.getActive()).toList();
        return users;
    }

    @Override
    public User getUserById(Integer id) {
        return checkAndGetUser(id);
    }

    @Override
    public void addUser(User user) {
        user.setActive(true);
        user.setCreateDate(LocalDate.now());
        userRepository.save(user);
    }

    @Override
    public void updateUserByID(User user, Integer id) {
        var foundedUser = checkAndGetUser(id);
        foundedUser.setUsername(user.getUsername());
        foundedUser.setPassword(user.getPassword());
        foundedUser.setEmail(user.getEmail());
        foundedUser.setCreateDate(LocalDate.now());
        userRepository.save(foundedUser);
    }

    @Override
    public void deleteUserByID(Integer id) {
        var foundedUser = checkAndGetUser(id);
        foundedUser.setActive(false);
        userRepository.save(foundedUser);
//        userRepository.deleteById(id);
    }

    @Override
    public List<User> getUserPosts(Integer id) {
        if(postRepository.findAllById(id).isEmpty() || postRepository.findAllById(id)==null){
            throw new PostNotFoundException("Posts not found, id was invalid!");
        }
        return postRepository.findAllById(id);
    }

    public User checkAndGetUser(Integer id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));
    }

}
