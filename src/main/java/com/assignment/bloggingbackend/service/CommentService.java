package com.assignment.bloggingbackend.service;

import com.assignment.bloggingbackend.dto.CommentDTO;
import com.assignment.bloggingbackend.util.Response;

import java.util.List;

public interface CommentService {
    List<CommentDTO> findAllComments();

    CommentDTO findCommentById(Integer id);

    Response<CommentDTO> saveComment(CommentDTO commentDTO);

    Response<CommentDTO> updateComment(CommentDTO commentDTO);

    Response<Boolean> deleteComment(Integer id);
}
