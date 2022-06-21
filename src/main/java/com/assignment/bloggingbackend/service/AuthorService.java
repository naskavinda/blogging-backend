package com.assignment.bloggingbackend.service;

import com.assignment.bloggingbackend.dto.AuthorDTO;
import com.assignment.bloggingbackend.dto.PostDTO;
import com.assignment.bloggingbackend.util.Response;

import java.util.List;

public interface AuthorService {
    Response<AuthorDTO> saveAuthor(AuthorDTO author);

    List<AuthorDTO> findAllAuthors();

    AuthorDTO findAuthorById(Integer id);

    List<PostDTO> findPostsByAuthorId(Integer id);

    Response<Boolean> deleteAuthor(Integer id);

    Response<AuthorDTO> updateAuthor(AuthorDTO authorDTO, Integer id);
}
