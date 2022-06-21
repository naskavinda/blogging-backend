package com.assignment.bloggingbackend.service.impl;

import com.assignment.bloggingbackend.dto.PostDTO;
import com.assignment.bloggingbackend.repository.PostRepository;
import com.assignment.bloggingbackend.service.PostService;
import com.assignment.bloggingbackend.service.impl.helper.MappingUtil;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
