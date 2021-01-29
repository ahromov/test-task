package com.example.testtask.controller.poster;

import com.example.testtask.dto.SearchDTO;
import com.example.testtask.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class PosterController {

    private final PostService postService;

    @Autowired
    public PosterController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/poster")
    public String userList(Model model) {
        model.addAttribute("apprPosts", postService.findApproved());
        model.addAttribute("search", new SearchDTO());
        return "poster";
    }

    @PostMapping("/poster/search")
    public String searchPost(@ModelAttribute("search") SearchDTO searchDTO,
                             BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors())
            return "poster";
        model.addAttribute("searchedPosts", postService.findSearchedApproved(searchDTO));
        return userList(model);
    }
}