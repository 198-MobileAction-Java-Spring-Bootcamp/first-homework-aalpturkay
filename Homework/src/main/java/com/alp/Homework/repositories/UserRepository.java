package com.alp.Homework.repositories;

import com.alp.Homework.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
