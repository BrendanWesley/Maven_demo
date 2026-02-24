package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.response.ApiResponse;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserApiController {
    
    @Autowired
    private UserService userService;

    /**
     * Get all users
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<User>>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(ApiResponse.success("Users retrieved successfully", users));
    }

    /**
     * Get user by username
     */
    @GetMapping("/{username}")
    public ResponseEntity<ApiResponse<User>> getUserByUsername(@PathVariable String username) {
        User user = userService.getUserByUsername(username);
        return ResponseEntity.ok(ApiResponse.success("User retrieved successfully", user));
    }

    /**
     * Create a new user
     */
    @PostMapping
    public ResponseEntity<ApiResponse<User>> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("User created successfully", createdUser));
    }

    /**
     * Update user
     */
    @PutMapping("/{username}")
    public ResponseEntity<ApiResponse<User>> updateUser(
            @PathVariable String username,
            @RequestBody User userDetails) {
        User updatedUser = userService.updateUser(username, userDetails);
        return ResponseEntity.ok(ApiResponse.success("User updated successfully", updatedUser));
    }

    /**
     * Delete user
     */
    @DeleteMapping("/{username}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
        return ResponseEntity.ok(ApiResponse.success("User deleted successfully"));
    }

    /**
     * Get users by role
     */
    @GetMapping("/role/{role}")
    public ResponseEntity<ApiResponse<List<User>>> getUsersByRole(@PathVariable String role) {
        List<User> users = userService.getUsersByRole(role);
        return ResponseEntity.ok(ApiResponse.success("Users retrieved successfully", users));
    }

    /**
     * Deactivate user
     */
    @PostMapping("/{username}/deactivate")
    public ResponseEntity<ApiResponse<User>> deactivateUser(@PathVariable String username) {
        User user = userService.deactivateUser(username);
        return ResponseEntity.ok(ApiResponse.success("User deactivated successfully", user));
    }

    /**
     * Activate user
     */
    @PostMapping("/{username}/activate")
    public ResponseEntity<ApiResponse<User>> activateUser(@PathVariable String username) {
        User user = userService.activateUser(username);
        return ResponseEntity.ok(ApiResponse.success("User activated successfully", user));
    }

    /**
     * Get user statistics
     */
    @GetMapping("/stats/count")
    public ResponseEntity<ApiResponse<Long>> getActiveUserCount() {
        long count = userService.countActiveUsers();
        return ResponseEntity.ok(ApiResponse.success("Active user count retrieved", count));
    }

    /**
     * Check if user exists
     */
    @GetMapping("/{username}/exists")
    public ResponseEntity<ApiResponse<Boolean>> userExists(@PathVariable String username) {
        boolean exists = userService.userExists(username);
        return ResponseEntity.ok(ApiResponse.success("User existence check completed", exists));
    }
}
