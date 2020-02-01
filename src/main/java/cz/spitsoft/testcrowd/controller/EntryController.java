package cz.spitsoft.testcrowd.controller;

import cz.spitsoft.testcrowd.model.user.RoleType;
import cz.spitsoft.testcrowd.model.user.UserImp;
import cz.spitsoft.testcrowd.service.SecurityService;
import cz.spitsoft.testcrowd.service.UserService;
import cz.spitsoft.testcrowd.validator.RegistrationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EntryController {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserService userService;

    @Autowired
    private RegistrationValidator registrationValidator;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/registration/reporter")
    public String reporterRegistration(Model model) {
        model.addAttribute("user", new UserImp());
        return "entry/reporter-registration";
    }

    @PostMapping("/registration/reporter")
    public String reporterRegistration(@ModelAttribute("user") UserImp user, BindingResult bindingResult) {
        registrationValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "entry/reporter-registration";
        }
        String passwordEncoded = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(passwordEncoded);
        user.setRoleType(RoleType.REPORTER);
        userService.save(user);
        securityService.autoLogin(user.getUsername(), user.getPasswordConfirm());
        return "redirect:/";
    }

    @GetMapping("/registration/tester")
    public String testerRegistration(Model model) {
        model.addAttribute("user", new UserImp());
        return "entry/tester-registration";
    }

    @PostMapping("/registration/tester")
    public String testerRegistration(@ModelAttribute("user") UserImp user, BindingResult bindingResult) {
        registrationValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "entry/tester-registration";
        }
        String passwordEncoded = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(passwordEncoded);
        user.setRoleType(RoleType.TESTER);
        userService.save(user);
        securityService.autoLogin(user.getUsername(), user.getPasswordConfirm());
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "entry/login";
    }
}
