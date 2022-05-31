package com.alp.Homework.dtos;

import lombok.Data;

import java.sql.Date;

@Data
public class UserCreationDto {
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private Date dateOfBirth;
}
