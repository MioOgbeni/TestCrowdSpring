package cz.spitsoft.testcrowd.controller;

import cz.spitsoft.testcrowd.exception.ResourceNotFoundException;
import cz.spitsoft.testcrowd.model.User2;
import cz.spitsoft.testcrowd.repository.UserRepository;
import cz.spitsoft.testcrowd.security.CurrentUser;
import cz.spitsoft.testcrowd.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public User2 getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }
}
