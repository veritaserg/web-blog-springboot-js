package com.sempl.webBlog.repo;

import com.sempl.webBlog.models.Post;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PostRepository extends CrudRepository<Post,Long> {

}
