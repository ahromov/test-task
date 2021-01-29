package com.example.testtask.controller.visitor;

import com.example.testtask.dto.SearchDTO;
import com.example.testtask.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class VisitorController {

    private final PostService postService;

    @Autowired
    public VisitorController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/visitor")
    public String userList(Model model) {
        model.addAttribute("apprPosts", postService.findApproved());
        model.addAttribute("search", new SearchDTO());
        return "visitor";
    }

    @PostMapping("/visitor/search")
    public String searchPost(@ModelAttribute("search") SearchDTO searchDTO,
                             BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors())
            return "visitor";
        model.addAttribute("searchedPosts", postService.findSearchedApproved(searchDTO));
        return userList(model);
    }
}