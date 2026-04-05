package com.finance.dashboard.controller;

import com.finance.dashboard.dto.LoginRequest;
import com.finance.dashboard.service.CustomUserDetailsService;
import com.finance.dashboard.util.JwtService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.
        AuthenticationManager;

import org.springframework.security.authentication.
        UsernamePasswordAuthenticationToken;

import org.springframework.security.core.userdetails.
        UserDetails;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final JwtService jwtService;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        UserDetails userDetails =
                userDetailsService
                        .loadUserByUsername(request.getEmail());

        return jwtService.generateToken(userDetails);
    }
}