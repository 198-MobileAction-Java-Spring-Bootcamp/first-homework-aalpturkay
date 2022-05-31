package com.alp.Homework.controllers;

import com.alp.Homework.dtos.UserCreationDto;
import com.alp.Homework.entities.User;
import com.alp.Homework.exceptions.UserNotFoundException;
import com.alp.Homework.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserCreationDto dto) {
        User newUser = new User();

        newUser.setName(dto.getName());
        newUser.setSurname(dto.getSurname());
        newUser.setEmail(dto.getEmail());
        newUser.setPhoneNumber(dto.getPhoneNumber());
        newUser.setDateOfBirth(dto.getDateOfBirth());
        newUser.setStatus(true);

        repository.save(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User newUser, @PathVariable Long id) {
        Optional<User> user = repository.findById(id);
        if (user.isPresent()) {
            User foundUser = user.get();
            foundUser.setName(newUser.getName());
            foundUser.setSurname(newUser.getSurname());
            foundUser.setEmail(newUser.getEmail());
            foundUser.setDateOfBirth(newUser.getDateOfBirth());
            foundUser.setPhoneNumber(newUser.getPhoneNumber());
            foundUser.setStatus(newUser.isStatus());
            repository.save(foundUser);
            return ResponseEntity.status(HttpStatus.OK).body(foundUser);
        } else
            throw new UserNotFoundException(id);
    }

    @GetMapping("/deactivate/{id}")
    public ResponseEntity<User> deactivateUser(@PathVariable Long id) {
        Optional<User> user = repository.findById(id);
        if (user.isPresent()) {
            User foundUser = user.get();
            foundUser.setStatus(false);
            repository.save(foundUser);
            return new ResponseEntity(foundUser, HttpStatus.OK);
        } else
            throw new UserNotFoundException(id);
    }
}
