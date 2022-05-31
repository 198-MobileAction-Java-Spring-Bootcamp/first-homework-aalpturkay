package com.alp.Homework.entities;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@Table(name = "users")
public class User {
    private @Id
    @GeneratedValue
    Long id;
    private @Column(length = 50)
    String name;
    private @Column(length = 50)
    String surname;
    private @Column(length = 50)
    String email;
    private @Column(length = 15)
    String phoneNumber;
    private boolean status;
    private @Column(name = "date_of_birth")
    Date dateOfBirth;
}
