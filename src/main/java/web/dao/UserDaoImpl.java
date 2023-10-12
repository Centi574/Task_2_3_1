package web.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

/*    private List<User> userList;

    public static int ID = 0;

    public UserDaoImpl() {
        userList = new ArrayList<>();
        userList.add(new User(++ID, "name", "surname"));
        userList.add(new User(++ID, "name12", "surname2"));
        userList.add(new User(++ID, "name23", "surname3"));
        userList.add(new User(++ID, "name4545", "surname4"));
        userList.add(new User(++ID, "name34232", "surname5"));
    }*/

    @Transactional
    public List<User> getAllUsers() {
        Session session = sessionFactory.getCurrentSession();
        List<User> fromUser = session.createQuery("FROM User ").getResultList();
        return fromUser;

    }

    @Transactional
    public void saveUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
    }

    @Transactional
    public User getUserById(int id) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, id);
        return user;
    }

    @Transactional
    public void removeUser(int id) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, id);
        session.delete(user);
    }

    @Transactional
    public void updateById(User updatedUser, int id) {
        Session session = sessionFactory.getCurrentSession();
        User userForUpdate = session.get(User.class, id);
        userForUpdate.setName(updatedUser.getName());
        userForUpdate.setSurname(updatedUser.getSurname());
    }
}
