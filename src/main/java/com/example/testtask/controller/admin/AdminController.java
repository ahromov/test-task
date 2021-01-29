package com.example.testtask.controller.admin;

import com.example.testtask.dto.SearchDTO;
import com.example.testtask.entity.Post;
import com.example.testtask.service.PostService;
import com.example.testtask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class AdminController {

    private final UserService userService;
    private final PostService postService;

    @Autowired
    public AdminController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    @GetMapping("/admin")
    public String userList(Model model) {
        model.addAttribute("notApprPosts", postService.findNotApproved());
        model.addAttribute("apprPosts", postService.findApproved());
        model.addAttribute("allUsers", userService.getAllUsers());
        model.addAttribute("search", new SearchDTO());
        return "admin";
    }

    @PostMapping("/admin")
    public String deleteUser(@RequestParam(required = true, defaultValue = "") Long userId,
                             @RequestParam(required = true, defaultValue = "") String action,
                             Model model) {
        if (action.equals("delete")) {
            userService.deleteUser(userId);
        }
        return userList(model);
    }

    @GetMapping("/admin/gt/{userId}")
    public String getUser(@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("allUsers", userService.findUserById(userId));
        return "admin";
    }

    @GetMapping("/admin/posts/list")
    public String postsNotApprovedList(Model model) {
        model.addAttribute("notApprPosts", postService.findNotApproved());
        return "admin";
    }

    @PostMapping("/admin/approve")
    public String postApprove(@RequestParam(required = true, defaultValue = "") Long postId,
                              @RequestParam(required = true, defaultValue = "") String action,
                              Model model) {
        Optional<Post> post = postService.findById(postId);
        if (post.isPresent() && action.equals("approve")) {
            post.get().setStatus(true);
            postService.save(post.get());
        }
        return userList(model);
    }

    @PostMapping("/admin/search")
    public String searchPost(@ModelAttribute("search") SearchDTO searchDTO,
                             BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors())
            return "admin";
        model.addAttribute("searchedPosts", postService.findSearchedApproved(searchDTO));
        return userList(model);
    }
}