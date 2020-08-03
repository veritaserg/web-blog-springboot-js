package com.sempl.webBlog.service;

import com.sempl.webBlog.models.Post;
import com.sempl.webBlog.repo.PostRepository;
import com.sempl.webBlog.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    public boolean existsById(Long id) {
        return postRepository.existsById(id);
    }

    public <S extends Post> S save(S s) {
        postRepository.save(s);

        return null;
    }

    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    public Iterable<Post> findAll() {
        return postRepository.findAll();
    }

    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }
}
