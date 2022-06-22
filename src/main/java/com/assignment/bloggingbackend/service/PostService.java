package com.assignment.bloggingbackend.service;

import com.assignment.bloggingbackend.dto.CommentDTO;
import com.assignment.bloggingbackend.dto.PostDTO;
import com.assignment.bloggingbackend.util.Response;

import java.util.List;

public interface PostService {
    List<PostDTO> findAllPosts();

    PostDTO findPostById(Integer id);

    List<CommentDTO> findCommentsByPostId(Integer id);

    Response<PostDTO> savePost(PostDTO postDTO);
}
