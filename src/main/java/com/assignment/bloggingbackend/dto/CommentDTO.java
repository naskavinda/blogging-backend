package com.assignment.bloggingbackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {

    private Integer id;
    private String name;
    private String body;
    private String email;
    private Integer postId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date createdOn;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date modifiedOn;

}
