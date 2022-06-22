package com.assignment.bloggingbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
@EntityListeners(AuditingEntityListener.class)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    @NotBlank(message = "{title.mandatory}")
    private String title;
    @Column(nullable = false)
    @NotBlank(message = "{body.mandatory}")
    private String body;
    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdOn;
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date modifiedOn;
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;
    @JsonIgnore
    @OneToMany(mappedBy = "post")
    private Set<Comment> comments;
}
