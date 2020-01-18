package cz.spitsoft.testcrowd.controller;

import cz.spitsoft.testcrowd.model.RoleImp;
import cz.spitsoft.testcrowd.model.RoleType;
import cz.spitsoft.testcrowd.model.UserImp;
import cz.spitsoft.testcrowd.repository.testcases.RoleRepository;
import cz.spitsoft.testcrowd.service.SecurityService;
import cz.spitsoft.testcrowd.service.UserService;
import cz.spitsoft.testcrowd.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new UserImp());

        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") UserImp userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        Set<RoleImp> roles = new HashSet<>();
        RoleImp adminRole = roleRepository.findByName(RoleType.ADMIN);
        roles.add(adminRole);

        userService.save(userForm, roles);

        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/logout")
    public String logout(Model model) {
        return "/";
    }

    @GetMapping({"/", "/welcome", "/index"})
    public String welcome(Model model) {
        return "index";
    }
}
