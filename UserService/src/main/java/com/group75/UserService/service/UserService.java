package com.group75.UserService.service;

import com.group75.UserService.entity.User;
import com.group75.UserService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        validateUser(user);
        return userRepository.save(user);
    }

    public User updateUser(Long id, User userDetails) {
        validateUser(userDetails);
        userDetails.setId(id);
        return userRepository.save(userDetails);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> findUsersByRole(String role) {
        return userRepository.findByRole(role);
    }

    private void validateUser(User user) {
        if (user.getName() == null || user.getName().isEmpty()) {
            throw new IllegalArgumentException("User name cannot be empty.");
        }
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty.");
        }
        if (user.getRole() == null || user.getUserType() == null) {
            throw new IllegalArgumentException("User role and type cannot be empty.");
        }
    }
}