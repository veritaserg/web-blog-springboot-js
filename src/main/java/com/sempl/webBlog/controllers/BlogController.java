package com.sempl.webBlog.controllers;


import com.sempl.webBlog.models.Post;
import com.sempl.webBlog.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class BlogController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/blog/add")
    public String blogAdd(Model model) {
        return "blog-add";
    }

    @PostMapping("/blog/add")
    public String blogPostAdd(@RequestParam String title,
                              @RequestParam String anons,
                              @RequestParam String full_text,
                              Model model) {
        Post post = Post.builder()
                .title(title)
                .anons(anons)
                .full_text(full_text)
                .build();

        postRepository.save(post);

        return "redirect:/support";
    }

    @GetMapping("/blog/{id}")
    public String blogDetails(@PathVariable("id") Long id, Model model) {
        if (!postRepository.existsById(id)){
            return "redirect:/support";
        }
        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        return "blog-details";
    }

}
