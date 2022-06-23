package com.assignment.bloggingbackend.service.impl;

import com.assignment.bloggingbackend.dto.CommentDTO;
import com.assignment.bloggingbackend.entity.Comment;
import com.assignment.bloggingbackend.exception.BloggingException;
import com.assignment.bloggingbackend.repository.CommentRepository;
import com.assignment.bloggingbackend.service.AuthorService;
import com.assignment.bloggingbackend.service.CommentService;
import com.assignment.bloggingbackend.service.PostService;
import com.assignment.bloggingbackend.service.impl.helper.MappingUtil;
import com.assignment.bloggingbackend.util.Response;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.assignment.bloggingbackend.util.ResponseDetails.*;

@Service
public record CommentServiceImpl(CommentRepository commentRepository,
                                 PostService postService,
                                 AuthorService authorService) implements CommentService {

    @Override
    public List<CommentDTO> findAllComments() {
        return commentRepository.findAll()
                .stream()
                .map(MappingUtil::mapCommentToCommentDTO)
                .toList();
    }

    @Override
    public CommentDTO findCommentById(Integer id) {
        return commentRepository.findById(id)
                .map(MappingUtil::mapCommentToCommentDTO)
                .orElseThrow(() -> new BloggingException(E1003.getCode(), E1003.getDescription()));
    }

    @Override
    public Response<CommentDTO> saveComment(CommentDTO commentDTO) {
        Comment comment = MappingUtil.mapCommentDTOToComment(commentDTO, postService::findPostById);
        Comment savedComment = commentRepository.save(comment);
        CommentDTO responseCommentDTO = MappingUtil.mapCommentToCommentDTO(savedComment);
        return new Response<>(S1001.getCode(), S1001.getDescription(), responseCommentDTO);
    }

    @Override
    public Response<CommentDTO> updateComment(CommentDTO commentDTO) {
        Optional<Comment> commentOptional = commentRepository.findById(commentDTO.getId());
        if (commentOptional.isPresent()) {
            Comment comment = MappingUtil.mapCommentDTOToComment(commentDTO, postService::findPostById);
            Comment savedComment = commentRepository.save(comment);
            CommentDTO responseCommentDTO = MappingUtil.mapCommentToCommentDTO(savedComment);
            return new Response<>(S1003.getCode(), S1003.getDescription(), responseCommentDTO);
        } else {
            throw new BloggingException(E1003.getCode(), E1003.getDescription());
        }
    }

    @Override
    public Response<Boolean> deleteComment(Integer id) {
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isPresent()) {
            commentRepository.delete(comment.get());
            return new Response<>(S1002.getCode(), S1002.getDescription(), true);
        } else {
            throw new BloggingException(E1003.getCode(), E1003.getDescription());
        }
    }
}