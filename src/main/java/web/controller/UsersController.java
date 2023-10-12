package web.controller;

import net.bytebuddy.matcher.StringMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.dao.UserDao;
import web.model.User;

import java.util.List;

@Controller
@RequestMapping(value = "/")
public class UsersController {

    private UserDao userDao;

    public UsersController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping()
    public String printUserList(ModelMap model) {
        List<User> userList = userDao.getAllUsers();
        model.addAttribute("userList", userList);
        return "index";
    }

    @GetMapping(value = "/new")
    public String addUser(@ModelAttribute("user") User user) {
        return "new";
    }

    @PostMapping()
    public String saveUser(@ModelAttribute User user) {
        userDao.saveUser(user);
        return "redirect:/";
    }

    @GetMapping(value = "/{id}")
    public String showUserById(Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("user", userDao.getUserById(id));
        return "show";
    }

    @GetMapping(value = "/{id}/edit")
    public String editUser(Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("user", userDao.getUserById(id));
        return "edit";
    }


    @PatchMapping(value = "/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable(value = "id") int id){
        userDao.updateById(user,id);
    return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String removeUserById(@PathVariable(value = "id") int id) {
        userDao.removeUser(id);
        return "redirect:/";
    }

}