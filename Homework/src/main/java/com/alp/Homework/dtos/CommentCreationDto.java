package com.alp.Homework.dtos;

import lombok.Data;

@Data
public class CommentCreationDto {
    private String comment;
    private Long productId;
    private Long userId;
}
