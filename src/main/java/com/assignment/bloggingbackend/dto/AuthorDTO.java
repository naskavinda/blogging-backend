package com.assignment.bloggingbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDTO implements Serializable {

    private Integer id;
    private String name;
    private String username;
    private String email;
    private String address;
}
