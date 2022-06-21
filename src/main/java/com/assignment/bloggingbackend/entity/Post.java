package com.assignment.bloggingbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String body;
    private Timestamp createdOn;
    private Timestamp modifiedOn;
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;
    @JsonIgnore
    @OneToMany(mappedBy = "post")
    private Set<Comment> comments;
}
