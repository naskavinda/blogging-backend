package com.assignment.bloggingbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    @NotBlank(message = "{name.mandatory}")
    private String name;
    @Column(nullable = false)
    @Email(message = "{email.not.valid}", regexp = "^\\S+@\\S+\\.\\S+$")
    @NotBlank(message = "{email.mandatory}")
    private String email;
    @Column(nullable = false)
    @NotBlank(message = "{body.mandatory}")
    private String body;
    @Column(insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedOn =  new Date(System.currentTimeMillis());
    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;
}
