package com.finance.dashboard.service;

import com.finance.dashboard.dto.UserRequest;
import com.finance.dashboard.entity.User;
import com.finance.dashboard.exception.ResourceNotFoundException;
import com.finance.dashboard.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // ✅ Create User
    public User createUser(UserRequest request) {

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())

                // Encode password
                .password(
                        passwordEncoder.encode(
                                request.getPassword()))

                .role(request.getRole())
                .active(true)
                .build();

        return userRepository.save(user);
    }

    // ✅ Get All Users
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    // ✅ Get User By ID
    public User getUserById(Long id) {

        return userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with id: " + id));
    }

    // ✅ Update User
    public User updateUser(Long id,
                           UserRequest request) {

        User user = getUserById(id);

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setRole(request.getRole());

        // Update password only if provided
        if (request.getPassword() != null
                && !request.getPassword().isEmpty()) {

            user.setPassword(
                    passwordEncoder.encode(
                            request.getPassword()));
        }

        return userRepository.save(user);
    }

    // ✅ Soft Delete User (Deactivate)
    public void deleteUser(Long id) {

        User user = getUserById(id);

        user.setActive(false);

        userRepository.save(user);
    }

    // ✅ Activate User Again
    public void activateUser(Long id) {

        User user = getUserById(id);

        user.setActive(true);

        userRepository.save(user);
    }
}