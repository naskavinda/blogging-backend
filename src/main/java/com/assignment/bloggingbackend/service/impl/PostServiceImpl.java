package com.assignment.bloggingbackend.service.impl;

import com.assignment.bloggingbackend.dto.AuthorDTO;
import com.assignment.bloggingbackend.dto.CommentDTO;
import com.assignment.bloggingbackend.dto.PostDTO;
import com.assignment.bloggingbackend.entity.Post;
import com.assignment.bloggingbackend.exception.BloggingException;
import com.assignment.bloggingbackend.repository.PostRepository;
import com.assignment.bloggingbackend.service.AuthorService;
import com.assignment.bloggingbackend.service.PostService;
import com.assignment.bloggingbackend.service.impl.helper.MappingUtil;
import com.assignment.bloggingbackend.util.Response;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.assignment.bloggingbackend.util.ResponseDetails.*;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final AuthorService authorService;

    public PostServiceImpl(PostRepository postRepository, AuthorService authorService) {
        this.postRepository = postRepository;
        this.authorService = authorService;
    }

    @Override
    public List<PostDTO> findAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(MappingUtil::mapPostToPostDTO)
                .toList();
    }

    @Override
    public PostDTO findPostById(Integer id) {
        return postRepository.findById(id)
                .map(MappingUtil::mapPostToPostDTO)
                .orElseThrow(() -> new BloggingException(E1003.getCode(), E1003.getDescription()));
    }

    @Override
    public List<CommentDTO> findCommentsByPostId(Integer id) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isPresent()) {
            return post.get().getComments().stream()
                    .map(MappingUtil::mapCommentToCommentDTO)
                    .toList();
        } else {
            throw new BloggingException(E1003.getCode(), E1003.getDescription());
        }
    }

    @Override
    public Response<PostDTO> savePost(PostDTO postDTO) {
        AuthorDTO authorDTO = authorService.findAuthorById(postDTO.getAuthorId());
        Post post = MappingUtil.mapPostDTOToPost(postDTO, MappingUtil.mapAuthorDTOToAuthor(authorDTO));
        Post savedPost = postRepository.save(post);
        PostDTO responsePostDTO = MappingUtil.mapPostToPostDTO(savedPost);
        return new Response<>(S1001.getCode(), S1001.getDescription(), responsePostDTO);
    }

    @Override
    public Response<PostDTO> updatePost(PostDTO postDTO) {
        Optional<Post> postOptional = postRepository.findById(postDTO.getId());
        if (postOptional.isPresent()) {
            AuthorDTO authorDTO = authorService.findAuthorById(postDTO.getAuthorId());
            Post post = MappingUtil.mapPostDTOToPost(postDTO, MappingUtil.mapAuthorDTOToAuthor(authorDTO));
            Post savedPost = postRepository.save(post);
            PostDTO responsePostDTO = MappingUtil.mapPostToPostDTO(savedPost);
            return new Response<>(S1003.getCode(), S1003.getDescription(), responsePostDTO);
        } else {
            throw new BloggingException(E1003.getCode(), E1003.getDescription());
        }
    }

    // TODO: 6/22/2022 add comment deletion
    @Override
    public Response<Boolean> deletePost(Integer id) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isPresent()) {
            postRepository.delete(post.get());
            return new Response<>(S1002.getCode(), S1002.getDescription(), true);
        } else {
            throw new BloggingException(E1003.getCode(), E1003.getDescription());
        }
    }
}
