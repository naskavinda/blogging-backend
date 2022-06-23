package com.assignment.bloggingbackend.service.impl.helper;

import com.assignment.bloggingbackend.dto.AuthorDTO;
import com.assignment.bloggingbackend.dto.CommentDTO;
import com.assignment.bloggingbackend.dto.PostDTO;
import com.assignment.bloggingbackend.entity.Author;
import com.assignment.bloggingbackend.entity.Comment;
import com.assignment.bloggingbackend.entity.Post;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.function.IntFunction;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MappingUtil {

    public static Author mapAuthorDTOToAuthor(AuthorDTO authorDTO) {
        return Author.builder()
                .id(authorDTO.getId())
                .address(authorDTO.getAddress())
                .email(authorDTO.getEmail())
                .name(authorDTO.getName())
                .username(authorDTO.getUsername())
                .build();
    }

    public static AuthorDTO mapAuthorToAuthorDTO(Author savedAuthor) {
        return AuthorDTO.builder()
                .id(savedAuthor.getId())
                .name(savedAuthor.getName())
                .username(savedAuthor.getUsername())
                .email(savedAuthor.getEmail())
                .address(savedAuthor.getAddress())
                .build();
    }

    public static PostDTO mapPostToPostDTO(Post post) {
        return PostDTO.builder()
                .id(post.getId())
                .title(post.getTitle())
                .authorId(post.getAuthor().getId())
                .body(post.getBody())
                .createdOn(post.getCreatedOn())
                .modifiedOn(post.getModifiedOn())
                .build();
    }

    public static Post mapPostDTOToPost(PostDTO postDTO, IntFunction<Author> function) {
        return Post.builder()
                .id(postDTO.getId())
                .title(postDTO.getTitle())
                .author(function.apply(postDTO.getAuthorId()))
                .body(postDTO.getBody())
                .createdOn(postDTO.getCreatedOn())
                .modifiedOn(postDTO.getModifiedOn())
                .build();
    }

    public static CommentDTO mapCommentToCommentDTO(Comment comment) {
        return CommentDTO.builder()
                .postId(comment.getPost().getId())
                .id(comment.getId())
                .name(comment.getName())
                .email(comment.getEmail())
                .body(comment.getBody())
                .createdOn(comment.getCreatedOn())
                .modifiedOn(comment.getModifiedOn())
                .build();
    }

    public static Comment mapCommentDTOToComment(CommentDTO commentDTO, IntFunction<Post> function) {
        return Comment.builder()
                .post(function.apply(commentDTO.getPostId()))
                .id(commentDTO.getId())
                .name(commentDTO.getName())
                .email(commentDTO.getEmail())
                .body(commentDTO.getBody())
                .createdOn(commentDTO.getCreatedOn())
                .modifiedOn(commentDTO.getCreatedOn())
                .build();
    }

}
