package web.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;
import web.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    final
    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(int id) {
        Optional<User> byId = userRepository.findById(id);
        return byId.orElse(null);
    }

    @Override
    @Transactional
    public void removeUser(int id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updateById(User user, int id) {
        User userForUpdate = userRepository.findById(id).orElse(null);
        userForUpdate.setSurname(user.getSurname());
        userForUpdate.setName(user.getName());
    }
}
