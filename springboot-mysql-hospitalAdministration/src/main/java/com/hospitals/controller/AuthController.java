package com.hospitals.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hospitals.entity.UserEntity;
import com.hospitals.repository.UserRepository;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    // Show sign-up page
    @GetMapping("/signup")
    public String showSignUpPage() {
        return "signUp";  // returns the sign-up page
    }

    // Show sign-in page
    @GetMapping("/signin")
    public String showSignInPage() {
        return "signIn";  // returns the sign-in page
    }

    // Handle registration form submission
    @PostMapping("/register")
    public String registerUser(@RequestParam String firstName,
                                @RequestParam String lastName,
                                @RequestParam String email,
                                @RequestParam String contact,
                                @RequestParam String username,
                                @RequestParam String password,
                                @RequestParam String role,
                                Model model) {

        // Create a new User object
        UserEntity newUser = new UserEntity();
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setEmail(email);
        newUser.setContact(contact);
        newUser.setUsername(username);
        newUser.setPassword(password);  // Ideally, you would hash the password
        newUser.setRole(role);

        // Save the new user to the database
        userRepository.save(newUser);

        // Redirect to the sign-in page after successful registration
        return "redirect:/signin";
    }

    // Handle login form submission (sign-in)
    @PostMapping("/login")
    public String loginUser(@RequestParam String username,
                            @RequestParam String password,
                            @RequestParam String role,
                            Model model) {

        // Find the user by username
        UserEntity user = userRepository.findByUsername(username);

        // Check if the user exists and the password matches (no authentication mechanism here yet)
        if (user != null && user.getPassword().equals(password) && user.getRole().equals(role)) {
            // Redirect to the appropriate dashboard based on the role
            if (role.equals("ADMIN")) {
                return "redirect:/admin/dashboard";
            } else if (role.equals("DOCTOR")) {
                return "redirect:/doctor/profile";
            } else if (role.equals("PATIENT")) {
                return "redirect:/patient/dashboard";
            }
        }

        // If login fails, show an error message
        model.addAttribute("error", "Invalid username, password, or role.");
        return "signIn";  // return to sign-in page
    }
}
