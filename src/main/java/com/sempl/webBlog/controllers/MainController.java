package com.sempl.webBlog.controllers;

import com.sempl.webBlog.models.Post;

import com.sempl.webBlog.service.PostService;
import net.bytebuddy.description.type.TypeDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

@Controller
public class MainController {

    @Autowired
    private PostService postService;

    @GetMapping("/")
    public String main(Model model) {
        return "main";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/blog-list")
    public String support(Model model) {
        Iterable<Post> posts = postService.findAll();
        ArrayList<Post> postList = new ArrayList<>();
        Iterator iterator = posts.iterator();
        while (iterator.hasNext()) {
            postList.add((Post) iterator.next());
        }
        Collections.reverse(postList);
        model.addAttribute("posts", postList);
        return "blog-list";
    }
}
