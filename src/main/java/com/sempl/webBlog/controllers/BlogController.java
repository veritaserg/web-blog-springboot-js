package com.sempl.webBlog.controllers;


import com.sempl.webBlog.models.Post;
import com.sempl.webBlog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class BlogController {

    @Autowired
    private PostService postService;

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

        postService.save(post);
        return "redirect:/blog-list";
    }

    @GetMapping("/blog/{id}")
    public String blogDetails(@PathVariable("id") Long id, Model model) {
        if (!postService.existsById(id)) {
            return "redirect:/blog-list";
        }
        Optional<Post> post = postService.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        return "blog-details";
    }

    @GetMapping("/blog/{id}/edit")
    public String blogEdit(@PathVariable("id") Long id, Model model) {
        if (!postService.existsById(id)) {
            return "redirect:/support";
        }
        Optional<Post> post = postService.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        return "blog-edit";
    }

    @PostMapping("/blog/{id}/edit")
    public String blogPostEdit(@PathVariable("id") Long id,
                               @RequestParam String title,
                               @RequestParam String anons,
                               @RequestParam String full_text,
                               Model model) {
        Post post = Post.builder()
                .id(id)
                .title(title)
                .anons(anons)
                .full_text(full_text)
                .build();

        postService.save(post);
        return "redirect:/blog-list";
    }

    @PostMapping("/blog/{id}/remove")
    public String blogPostRemove(@PathVariable("id") Long id, Model model) {
        if (!postService.existsById(id)) {
            return "redirect:/support";
        }
        postService.deleteById(id);
        return "redirect:/blog-list";
    }
}
