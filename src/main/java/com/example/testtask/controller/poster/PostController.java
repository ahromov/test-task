package com.example.testtask.controller.poster;

import com.example.testtask.dto.SearchDTO;
import com.example.testtask.entity.Post;
import com.example.testtask.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/post")
    public String post(Model model) {
        model.addAttribute("postForm", new Post());
        return "post";
    }

    @PostMapping("/post")
    public String createPost(@ModelAttribute("postForm") Post postForm,
                             BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors())
            return "poster";
        Post post = postService.save(postForm);
        model.addAttribute("id", post.getId());
        model.addAttribute("title", post.getTitle());
        model.addAttribute("body", post.getBody());
        model.addAttribute("status", post.isStatus());
        model.addAttribute("createdAt", post.getCreatedAt());
        return "redirect:/poster";
    }

    @GetMapping("/post/{postId}")
    public String editPost(@PathVariable("postId") Long postId, Model model) {
        model.addAttribute("post", postService.findById(postId));
        return "poster";
    }

    @PostMapping("/post/edit")
    public String editPost(@RequestParam(required = true) Long postId,
                           @RequestParam(required = true) String title,
                           @RequestParam(required = true) String body,
                           @RequestParam(required = true) String action,
                           Model model) {
        Post post = postService.editPost(postId, title, body, action);
        model.addAttribute("id", post.getId());
        model.addAttribute("title", post.getTitle());
        model.addAttribute("body", post.getBody());
        model.addAttribute("createdAt", post.getCreatedAt());
        model.addAttribute("status", post.isStatus());
        return "redirect:/poster";
    }
}
