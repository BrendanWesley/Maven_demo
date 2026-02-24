package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.exception.UserException;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class UserService {
    private final Map<String, User> userStore = new HashMap<>();

    public UserService() {
        initializeDefaultUsers();
    }

    private void initializeDefaultUsers() {
        User user1 = new User("user", "user@example.com", "John", "Doe");
        user1.setId("1");
        user1.setRole("USER");
        userStore.put("user", user1);

        User user2 = new User("admin", "admin@example.com", "Admin", "User");
        user2.setId("2");
        user2.setRole("ADMIN");
        userStore.put("admin", user2);
    }

    /**
     * Get user by username
     */
    public User getUserByUsername(String username) {
        User user = userStore.get(username);
        if (user == null) {
            throw new UserException("User not found with username: " + username, "USER_NOT_FOUND", 404);
        }
        return user;
    }

    /**
     * Get all users
     */
    public List<User> getAllUsers() {
        return new ArrayList<>(userStore.values());
    }

    /**
     * Create a new user
     */
    public User createUser(User user) {
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new UserException("Username cannot be empty", "INVALID_USERNAME", 400);
        }

        if (userStore.containsKey(user.getUsername())) {
            throw new UserException("User already exists with username: " + user.getUsername(), "USER_EXISTS", 409);
        }

        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new UserException("Email cannot be empty", "INVALID_EMAIL", 400);
        }

        user.setId(UUID.randomUUID().toString());
        user.setCreatedAt(LocalDateTime.now());
        user.setActive(true);

        userStore.put(user.getUsername(), user);
        return user;
    }

    /**
     * Update an existing user
     */
    public User updateUser(String username, User userDetails) {
        User user = getUserByUsername(username);

        if (userDetails.getEmail() != null) {
            user.setEmail(userDetails.getEmail());
        }
        if (userDetails.getFirstName() != null) {
            user.setFirstName(userDetails.getFirstName());
        }
        if (userDetails.getLastName() != null) {
            user.setLastName(userDetails.getLastName());
        }

        return user;
    }

    /**
     * Delete a user
     */
    public boolean deleteUser(String username) {
        if (!userStore.containsKey(username)) {
            throw new UserException("User not found with username: " + username, "USER_NOT_FOUND", 404);
        }
        userStore.remove(username);
        return true;
    }

    /**
     * Update last login time
     */
    public void updateLastLogin(String username) {
        User user = userStore.get(username);
        if (user != null) {
            user.setLastLogin(LocalDateTime.now());
        }
    }

    /**
     * Check if user exists
     */
    public boolean userExists(String username) {
        return userStore.containsKey(username);
    }

    /**
     * Count active users
     */
    public long countActiveUsers() {
        return userStore.values().stream()
            .filter(User::isActive)
            .count();
    }

    /**
     * Get users by role
     */
    public List<User> getUsersByRole(String role) {
        return userStore.values().stream()
            .filter(user -> user.getRole().equals(role))
            .toList();
    }

    /**
     * Deactivate user
     */
    public User deactivateUser(String username) {
        User user = getUserByUsername(username);
        user.setActive(false);
        return user;
    }

    /**
     * Activate user
     */
    public User activateUser(String username) {
        User user = getUserByUsername(username);
        user.setActive(true);
        return user;
    }
}
