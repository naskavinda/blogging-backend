package com.assignment.bloggingbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    @NotBlank(message = "{name.mandatory}")
    private String name;
    @Column(nullable = false)
    @NotBlank(message = "{username.mandatory}")
    private String username;
    @Column(nullable = false)
    @Email(message = "{email.not.valid}", regexp = "^\\S+@\\S+\\.\\S+$")
    @NotBlank(message = "{email.mandatory}")
    private String email;
    private String address;
    @JsonIgnore
    @OneToMany(mappedBy = "author")
    private Set<Post> posts;
}

