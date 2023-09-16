package com.example.classbridge.controllers;

import com.example.classbridge.config.security.ApplicationConfig;
import com.example.classbridge.config.security.JwtService;
import com.example.classbridge.dtos.LoginDto;
import com.example.classbridge.entities.Profile;
import com.example.classbridge.entities.User;
import com.example.classbridge.services.AuthenticationService;
import com.example.classbridge.services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HomeController {
    private final AuthenticationService service;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager; // Inject AuthenticationManager here
    private final JwtService jwtService; // Inject JwtService here


    @PostMapping("/login")
    public ResponseEntity<String> login(HttpServletResponse response, @Valid @RequestBody LoginDto loginDTO) {
        try {
            // Authenticate the user
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword())
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String jwtToken = jwtService.generateToken(userDetails);


            response.addHeader("Authorization", "Bearer " + jwtToken);
            System.out.println("User Roles: " + authentication.getAuthorities());
            return ResponseEntity.ok("Success");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid username or password");
        }
    }





//    @PostMapping("/login")
//    public ResponseEntity<String> login(HttpServletResponse response, @Valid @RequestBody LoginDto loginDTO) {
//        try {
//
//            service.login(response, loginDTO);
//            return ResponseEntity.ok("Success");
//
//        } catch (Exception ex) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid username or password");
//        }
//    }

    @PostMapping("/newadmin")
    public ResponseEntity<User> newUser(@RequestBody @Valid User user) {

        User new_user = new User();
        new_user.setUsername(user.getUsername());
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        new_user.setPassword(encodedPassword);

        new_user.setRole(user.getRole());
        Profile profile = new Profile();
        new_user.setProfile(profile);

        User createdUser = userService.createUser(new_user);
        return ResponseEntity.ok(createdUser);
    }


}
