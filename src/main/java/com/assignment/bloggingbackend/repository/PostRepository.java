package com.assignment.bloggingbackend.repository;

import com.assignment.bloggingbackend.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    List<Post> findPostByAuthorId(Integer authorId);
}
