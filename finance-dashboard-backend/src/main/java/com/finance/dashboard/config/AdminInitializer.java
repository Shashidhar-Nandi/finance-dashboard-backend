package com.finance.dashboard.config;

import com.finance.dashboard.entity.Role;
import com.finance.dashboard.entity.User;
import com.finance.dashboard.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class AdminInitializer {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner createAdmin() {

        return args -> {

            // Check if ADMIN exists
            boolean adminExists =
                    userRepository.existsByRole(Role.ADMIN);

            if (!adminExists) {

                User admin = User.builder()
                        .name("System Admin")
                        .email("admin@gmail.com")
                        .password(
                                passwordEncoder.encode("123456")
                        )
                        .role(Role.ADMIN)
                        .active(true)
                        .build();

                userRepository.save(admin);

                System.out.println(
                        "✅ Default ADMIN created: admin@gmail.com / 123456"
                );
            }
        };
    }
}