package com.alp.Homework.controllers;

import com.alp.Homework.dtos.CommentCreationDto;
import com.alp.Homework.dtos.CommentUpdatingDto;
import com.alp.Homework.entities.Comment;
import com.alp.Homework.entities.User;
import com.alp.Homework.repositories.CommentRepository;
import com.alp.Homework.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public CommentController(CommentRepository commentRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody CommentCreationDto newComment) {
        Optional<User> user = userRepository.findById(newComment.getUserId());
        if (user.isPresent()) {
            Comment comment = new Comment();
            comment.setComment(newComment.getComment());
            comment.setProductId(newComment.getProductId());
            comment.setUser(user.get());
            comment.setCreatedAt(Instant.now());
            commentRepository.save(comment);
            return new ResponseEntity<>(comment, HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteComment(@PathVariable Long id) {
        commentRepository.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long id, @RequestBody CommentUpdatingDto dto) {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();
            comment.setComment(dto.getComment());
            commentRepository.save(comment);
            return new ResponseEntity<>(comment, HttpStatus.OK);
        }
        
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
