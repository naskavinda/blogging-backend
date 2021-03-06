package com.assignment.bloggingbackend.service.impl;

import com.assignment.bloggingbackend.dto.AuthorDTO;
import com.assignment.bloggingbackend.dto.PostDTO;
import com.assignment.bloggingbackend.entity.Author;
import com.assignment.bloggingbackend.exception.BloggingException;
import com.assignment.bloggingbackend.repository.AuthorRepository;
import com.assignment.bloggingbackend.repository.CommentRepository;
import com.assignment.bloggingbackend.repository.PostRepository;
import com.assignment.bloggingbackend.service.AuthorService;
import com.assignment.bloggingbackend.service.impl.helper.MappingHelper;
import com.assignment.bloggingbackend.util.Response;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.assignment.bloggingbackend.util.ResponseDetails.*;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository,
                             PostRepository postRepository,
                             CommentRepository commentRepository) {
        this.authorRepository = authorRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public Response<AuthorDTO> saveAuthor(AuthorDTO authorDTO) {
        Author author = MappingHelper.mapAuthorDTOToAuthor(authorDTO);
        author.setId(0);
        Author savedAuthor = authorRepository.save(author);
        AuthorDTO responseAuthorDTO = MappingHelper.mapAuthorToAuthorDTO(savedAuthor);
        return new Response<>(S1001.getCode(), S1001.getDescription(), responseAuthorDTO);
    }

    @Override
    public List<AuthorDTO> findAllAuthors() {
        return authorRepository.findAll()
                .stream()
                .map(MappingHelper::mapAuthorToAuthorDTO)
                .toList();
    }

    @Override
    public AuthorDTO findAuthorById(Integer id) {
        return authorRepository.findById(id)
                .map(MappingHelper::mapAuthorToAuthorDTO)
                .orElseThrow(() -> new BloggingException(E1003.getCode(), E1003.getDescription()));
    }

    @Override
    public List<PostDTO> findPostsByAuthorId(Integer id) {
        Optional<Author> author = authorRepository.findById(id);
        if (author.isPresent()) {
            return author.get().getPosts().stream()
                    .map(MappingHelper::mapPostToPostDTO)
                    .toList();
        } else {
            throw new BloggingException(E1003.getCode(), E1003.getDescription());
        }
    }

    @Override
    public Response<Boolean> deleteAuthor(Integer id) {
        Optional<Author> author = authorRepository.findById(id);
        if (author.isPresent()) {
            author.get().getPosts()
                    .forEach(post -> {
                        commentRepository.deleteAll(post.getComments());
                        postRepository.delete(post);
                    });
            authorRepository.delete(author.get());
            return new Response<>(S1002.getCode(), S1002.getDescription(), true);
        } else {
            throw new BloggingException(E1003.getCode(), E1003.getDescription());
        }
    }

    @Override
    public Response<AuthorDTO> updateAuthor(AuthorDTO authorDTO, Integer id) {
        Optional<Author> authorOptional = authorRepository.findById(id);
        if (authorOptional.isPresent()) {
            Author author = MappingHelper.mapAuthorDTOToAuthor(authorDTO);
            author.setId(id);
            Author savedAuthor = authorRepository.save(author);
            AuthorDTO responseAuthorDTO = MappingHelper.mapAuthorToAuthorDTO(savedAuthor);
            return new Response<>(S1003.getCode(), S1003.getDescription(), responseAuthorDTO);
        } else {
            throw new BloggingException(E1003.getCode(), E1003.getDescription());
        }
    }
}
