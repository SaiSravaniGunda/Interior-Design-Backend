package com.example.interior.controllers;

import com.example.interior.dto.AuthRequest;
import com.example.interior.security.JwtUtil;
import com.example.interior.services.AuthService;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/auth")
public class AuthController {
	
//	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final AuthService authService;
    private final JwtUtil jwtUtil;
    
    
    public AuthController(AuthService authService,JwtUtil jwtUtil) {
        this.authService = authService;
        this.jwtUtil = jwtUtil;
        
    }
    
    @GetMapping("/me")
    public ResponseEntity<?> getUserFromToken(@CookieValue(name = "jwt", required = false) String token) {
    	
        if (token == null || token.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not authenticated");
        }

        try {
            Claims claims = jwtUtil.parseToken(token);
            Long userId = ((Number) claims.get("id")).longValue(); // Ensures correct type conversion
            String role = claims.get("role", String.class);

            if (userId == null || role == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token data");
            }

            Map<String, Object> response = new HashMap<>();
            response.put("id", userId);
            response.put("role", role);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired token");
        }
    }
    
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthRequest request) {
//    	System.out.println("Received: " + request);
//    	 logger.info("Received Register Request: {}", request);
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest request ,HttpServletResponse response) {
    	String token = authService.login(request);
    	System.out.print(token);
    	 Cookie cookie = new Cookie("jwt", token);
    	 cookie.setHttpOnly(true);
    	 cookie.setSecure(false);  // Set to true if using HTTPS
    	 cookie.setPath("/");
    	 cookie.setMaxAge(86400);  // 1 day

    	    response.addCookie(cookie);
//        return ResponseEntity.ok(authService.login(request));
    	    return ResponseEntity.ok("Login successful");
    }
    
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("jwt", null); // Set cookie value to null
        cookie.setHttpOnly(true);
        cookie.setSecure(false);  // Set to true if using HTTPS
        cookie.setPath("/");
        cookie.setMaxAge(0); // Expire the cookie immediately

        response.addCookie(cookie);
        return ResponseEntity.ok("Logged out successfully");
    }

    
}
