package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
@RequestMapping(value = "/")
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public String printUserList(ModelMap model) {
        List<User> userList = userService.getAllUsers();
        model.addAttribute("userList", userList);
        return "index";
    }

    @GetMapping(value = "/new")
    public String addUser(@ModelAttribute("user") User user) {
        return "new";
    }

    @PostMapping()
    public String saveUser(@ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping(value = "/{id}")
    public String showUserById(Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("user", userService.getUserById(id));
        return "show";
    }

    @GetMapping(value = "/{id}/edit")
    public String editUser(Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("user", userService.getUserById(id));
        return "edit";
    }

    @PatchMapping(value = "/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable(value = "id") int id) {
        userService.updateById(user, id);
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String removeUserById(@PathVariable(value = "id") int id) {
        userService.removeUser(id);
        return "redirect:/";
    }
}