package com.assignment.bloggingbackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {

    private Integer id;
    private String title;
    private String body;
    private Integer authorId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Timestamp createdOn;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Timestamp modifiedOn;

}
