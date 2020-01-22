package cz.spitsoft.testcrowd.controller;

import cz.spitsoft.testcrowd.model.UserImp;
import cz.spitsoft.testcrowd.repository.UserRepository;
import cz.spitsoft.testcrowd.service.SecurityService;
import cz.spitsoft.testcrowd.service.UserService;
import cz.spitsoft.testcrowd.validator.RegistrationValidator;
import cz.spitsoft.testcrowd.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private RegistrationValidator registrationValidator;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new UserImp());
        return "entry/registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") UserImp user, BindingResult bindingResult) {
        registrationValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "entry/registration";
        }

        // TODO: Add USER and COMPANY role switch.
        String password = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(password);
        userService.save(user);
        securityService.autoLogin(user.getUsername(), user.getPasswordConfirm());
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "entry/login";
    }

    @GetMapping("/users")
    public String userList(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "user/user-list";
    }

    @GetMapping("/users/current")
    public String userDetail(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("user", userService.findByUsername(username));
        return "user/user-detail";
    }

    @GetMapping("/users/{id}")
    public String userDetail(Model model, @PathVariable(value = "id") String id) {
        model.addAttribute("user", userService.findById(id));
        return "user/user-detail";
    }

    @GetMapping("/users/{id}/edit")
    public String userEdit(Model model, @PathVariable(value = "id") String id) {
        if (securityService.isCurrentUserById(id) || securityService.isCurrentUserAdmin()) {
            model.addAttribute("user", userService.findById(id));
            return "user/user-edit";
        }
        return "redirect:/";
    }

    @PostMapping("/users/{id}/edit")
    public String userEdit(@ModelAttribute("user") UserImp userForm, BindingResult bindingResult, @PathVariable(value = "id") String id) {
        if (securityService.isCurrentUserById(id) || securityService.isCurrentUserAdmin()) {
            // load user
            UserImp user = userService.findById(id);

            // edit user posted data
            user.setUsername(userForm.getUsername());
            user.setEmail(userForm.getEmail());
            user.setFirstName(userForm.getFirstName());
            user.setLastName(userForm.getLastName());

            // validate user
            userValidator.validate(user, bindingResult);
            if (bindingResult.hasErrors()) {
                return "user/user-edit";
            }

            // save user
            userService.save(user);
            return "user/user-detail";
        }
        return "redirect:/";
    }

    @GetMapping("/users/{id}/delete")
    public String userDelete(Model model, @PathVariable(value = "id") String id) {
        if (securityService.isCurrentUserById(id) || securityService.isCurrentUserAdmin()) {
            UserImp user = userService.findById(id);
            // TODO: Add auto logout of this user.
            userService.delete(user);
            return "user/user-list";
        }
        return "redirect:/";
    }
}
