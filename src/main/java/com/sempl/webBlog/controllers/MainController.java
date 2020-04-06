package com.sempl.webBlog.controllers;

import com.sempl.webBlog.models.Post;
import com.sempl.webBlog.repo.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
   private PostRepository postService;

    @GetMapping("/")
    public String main(Model model) {
        return "main";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/support")
    public String support(Model model) {
        Iterable<Post> posts = postService.findAll();

        model.addAttribute("posts",posts);

        return "support";
    }


}
