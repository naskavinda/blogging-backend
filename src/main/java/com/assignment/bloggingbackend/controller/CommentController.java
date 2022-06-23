package com.assignment.bloggingbackend.controller;


import com.assignment.bloggingbackend.dto.CommentDTO;
import com.assignment.bloggingbackend.service.CommentService;
import com.assignment.bloggingbackend.util.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping()
    public ResponseEntity<List<CommentDTO>> findAllPosts() {
        List<CommentDTO> commentDTOs = commentService.findAllComments();
        return ResponseEntity.ok(commentDTOs);
    }


    @GetMapping("/{id}")
    public ResponseEntity<CommentDTO> findCommentById(@PathVariable Integer id) {
        CommentDTO commentDTOs = commentService.findCommentById(id);
        return ResponseEntity.ok(commentDTOs);
    }

    @PostMapping()
    public ResponseEntity<Response<CommentDTO>> saveAuthor(@Valid @RequestBody CommentDTO commentDTO) {
        Response<CommentDTO> authorResponse = commentService.saveComment(commentDTO);
        return ResponseEntity.ok(authorResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<CommentDTO>> updateAuthor(@Valid @RequestBody CommentDTO commentDTO, @PathVariable Integer id) {
        commentDTO.setId(id);
        Response<CommentDTO> response = commentService.updateComment(commentDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Boolean>> deleteComment(@PathVariable Integer id) {
        Response<Boolean> response = commentService.deleteComment(id);
        return ResponseEntity.ok(response);
    }
}
