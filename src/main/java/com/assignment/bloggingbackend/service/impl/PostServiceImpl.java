package com.assignment.bloggingbackend.service.impl;

import com.assignment.bloggingbackend.dto.CommentDTO;
import com.assignment.bloggingbackend.dto.PostDTO;
import com.assignment.bloggingbackend.entity.Post;
import com.assignment.bloggingbackend.exception.BloggingException;
import com.assignment.bloggingbackend.repository.PostRepository;
import com.assignment.bloggingbackend.service.PostService;
import com.assignment.bloggingbackend.service.impl.helper.MappingUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.assignment.bloggingbackend.util.ResponseDetails.E1003;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
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
}
