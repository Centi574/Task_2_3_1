package web.service;

import web.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    void saveUser(User user);

    User getUserById(int id);

    void removeUser(int id);

    void updateById(User user, int id);
}
