package com.assignment.bloggingbackend.controller;

import com.assignment.bloggingbackend.dto.CommentDTO;
import com.assignment.bloggingbackend.dto.PostDTO;
import com.assignment.bloggingbackend.service.PostService;
import com.assignment.bloggingbackend.util.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping()
    public ResponseEntity<List<PostDTO>> findAllPosts() {
        List<PostDTO> postDTOs = postService.findAllPosts();
        return ResponseEntity.ok(postDTOs);
    }


    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> findPostById(@PathVariable Integer id) {
        PostDTO postDTOs = postService.findPostById(id);
        return ResponseEntity.ok(postDTOs);
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<List<CommentDTO>> findPostsByAuthorId(@PathVariable Integer id) {
        List<CommentDTO> authorDTOs = postService.findCommentsByPostId(id);
        return ResponseEntity.ok(authorDTOs);
    }

    @PostMapping()
    public ResponseEntity<Response<PostDTO>> saveAuthor(@Valid @RequestBody PostDTO postDTO) {
        Response<PostDTO> authorResponse = postService.savePost(postDTO);
        return ResponseEntity.ok(authorResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<PostDTO>> updateAuthor(@Valid @RequestBody PostDTO postDTO, @PathVariable Integer id) {
        postDTO.setId(id);
        Response<PostDTO> response = postService.updatePost(postDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Boolean>> deletePost(@PathVariable Integer id) {
        Response<Boolean> response = postService.deletePost(id);
        return ResponseEntity.ok(response);
    }
}
