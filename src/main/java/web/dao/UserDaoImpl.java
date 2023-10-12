package web.dao;

import org.springframework.stereotype.Component;
import web.model.User;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDaoImpl implements UserDao {
    private List<User> userList;

    public static int ID = 0;

    public UserDaoImpl() {
        userList = new ArrayList<>();
        userList.add(new User(++ID, "name", "surname"));
        userList.add(new User(++ID, "name12", "surname2"));
        userList.add(new User(++ID, "name23", "surname3"));
        userList.add(new User(++ID, "name4545", "surname4"));
        userList.add(new User(++ID, "name34232", "surname5"));
    }

    public List<User> getAllUsers() {
        return userList;
    }

    public void saveUser(User user) {
        userList.add(user);
    }

    public User getUserById (int id) {
        return userList.stream().filter(user -> user.getId() == id).findAny().orElse(null);
    }

    public void removeUser(int id) {
        User userToDelete = userList.stream().filter(user -> user.getId() == id).findAny().orElse(null);
        userList.remove(userToDelete);
    }

    public void updateById(User updatedUser, int id) {
        User userForUpdate = userList.stream().filter(user -> user.getId() == id).findAny().orElse(null);
        userForUpdate.setName(updatedUser.getName());
        userForUpdate.setSurname(updatedUser.getSurname());
    }
}
