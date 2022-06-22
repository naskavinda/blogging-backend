package com.assignment.bloggingbackend.controller;

import com.assignment.bloggingbackend.dto.PostDTO;
import com.assignment.bloggingbackend.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
