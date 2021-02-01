package com.example.testtask.controller;

import com.example.testtask.dto.SearchDTO;
import com.example.testtask.entity.User;
import com.example.testtask.service.PostService;
import com.example.testtask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class RegistrationController {

    private final UserService userService;
    private final PostService postService;

    @Autowired
    public RegistrationController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("userForm") @Validated User userForm, BindingResult bindingResult, Model model) {
        String view = validate(userForm, bindingResult, model);
        if (view != null) return view;
        view = registrate(userForm, model);
        if (view != null) return view;
        model.addAttribute("usernameError", "User with same name exists");
        return "registration";
    }

    private String registrate(User userForm, Model model) {
        User user = userService.saveUser(userForm);
        if (user != null) {
            switch (user.getRoles().iterator().next().getName()) {
                case ("ROLE_ADMIN") -> {
                    model.addAttribute("notApprPosts", postService.findNotApproved());
                    model.addAttribute("apprPosts", postService.findApproved());
                    model.addAttribute("allUsers", userService.getAllUsers());
                    model.addAttribute("search", new SearchDTO());
                    return "admin";
                }
                case ("ROLE_POSTER") -> {
                    model.addAttribute("apprPosts", postService.findApproved());
                    model.addAttribute("search", new SearchDTO());
                    return "poster";
                }
                case ("ROLE_VISITOR") -> {
                    model.addAttribute("apprPosts", postService.findApproved());
                    model.addAttribute("search", new SearchDTO());
                    return "visitor";
                }
            }
        }
        return "home";
    }

    private String validate(User userForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors())
            return "registration";
        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())) {
            model.addAttribute("passwordError", "Passwords is not equals");
            return "registration";
        }
        return null;
    }
}
