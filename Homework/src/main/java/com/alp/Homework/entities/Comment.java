package com.alp.Homework.entities;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Date;
import java.time.Instant;

@Entity
@Data
public class Comment {
    private @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CMT_SEQ")
    Long id;
    @Lob
    @Column(length = 500)
    private String comment;
    @CreatedDate
    private Instant createdAt;
    private Long productId;
    @ManyToOne(targetEntity = User.class)
    private User user;

}
