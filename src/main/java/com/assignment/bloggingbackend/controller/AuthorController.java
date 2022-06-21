package com.assignment.bloggingbackend.controller;

import com.assignment.bloggingbackend.dto.AuthorDTO;
import com.assignment.bloggingbackend.dto.PostDTO;
import com.assignment.bloggingbackend.service.AuthorService;
import com.assignment.bloggingbackend.util.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping()
    public ResponseEntity<List<AuthorDTO>> findAllAuthors() {
        List<AuthorDTO> authorDTOs = authorService.findAllAuthors();
        return ResponseEntity.ok(authorDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> findAuthorById(@PathVariable Integer id) {
        AuthorDTO authorDTOs = authorService.findAuthorById(id);
        return ResponseEntity.ok(authorDTOs);
    }

    @GetMapping("/{id}/posts")
    public ResponseEntity<List<PostDTO>> findPostsByAuthorId(@PathVariable Integer id) {
        List<PostDTO> authorDTOs = authorService.findPostsByAuthorId(id);
        return ResponseEntity.ok(authorDTOs);
    }

    @PostMapping()
    public ResponseEntity<Response<AuthorDTO>> saveAuthor(@Valid @RequestBody AuthorDTO author) {
        Response<AuthorDTO> authorResponse = authorService.saveAuthor(author);
        return ResponseEntity.ok(authorResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<AuthorDTO>> updateAuthor(@Valid @RequestBody AuthorDTO authorDTO, @PathVariable Integer id) {
        Response<AuthorDTO> response = authorService.updateAuthor(authorDTO, id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Boolean>> deleteAuthor(@PathVariable Integer id) {
        Response<Boolean> response = authorService.deleteAuthor(id);
        return ResponseEntity.ok(response);
    }

}
