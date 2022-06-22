package com.assignment.bloggingbackend.service;

import com.assignment.bloggingbackend.dto.CommentDTO;
import com.assignment.bloggingbackend.dto.PostDTO;

import java.util.List;

public interface PostService {
    List<PostDTO> findAllPosts();

    PostDTO findPostById(Integer id);

    List<CommentDTO> findCommentsByPostId(Integer id);
}
