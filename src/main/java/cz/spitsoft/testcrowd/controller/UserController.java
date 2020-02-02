package cz.spitsoft.testcrowd.controller;

import cz.spitsoft.testcrowd.model.user.UserImp;
import cz.spitsoft.testcrowd.service.SecurityService;
import cz.spitsoft.testcrowd.service.UserService;
import cz.spitsoft.testcrowd.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class UserController {

    @Autowired
    @Qualifier("sessionRegistry")
    private SessionRegistry sessionRegistry;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/users")
    public String userList(Model model, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "10") int size, @RequestParam(value = "search", defaultValue = "") String search) {

        Page<UserImp> users = userService.findAll(PageRequest.of(page, size), search);
        model.addAttribute("users", users);
        int totalPages = users.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(0, totalPages - 1)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pages", pageNumbers);
        }
        return "user/user-list";

    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'REPORTER', 'TESTER')")
    @GetMapping("/users/current")
    public String userDetail(Model model) {

        model.addAttribute("user", securityService.getCurrentUser());
        return "user/user-detail";

    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'REPORTER', 'TESTER')")
    @GetMapping("/users/{id}")
    public String userDetail(Model model, @PathVariable(value = "id") String id) {

        if (!securityService.isCurrentUserById(id) && !securityService.isCurrentUserAdmin()) {
            return "error/error-401";
        }

        model.addAttribute("user", userService.findById(id));
        return "user/user-detail";

    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'REPORTER', 'TESTER')")
    @GetMapping("/users/{id}/edit")
    public String userEdit(Model model, @PathVariable(value = "id") String id) {

        if (!securityService.isCurrentUserById(id) && !securityService.isCurrentUserAdmin()) {
            return "error/error-401";
        }

        model.addAttribute("user", userService.findById(id));
        return "user/user-edit";

    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'REPORTER', 'TESTER')")
    @PostMapping("/users/{id}/edit")
    public String userEdit(@ModelAttribute("user") UserImp userForm, BindingResult bindingResult, @PathVariable(value = "id") String id, HttpServletRequest request) {

        if (!securityService.isCurrentUserById(id) && !securityService.isCurrentUserAdmin()) {
            return "error/error-401";
        }

        userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            return "user/user-edit";
        }

        UserImp user = userService.findById(id);
        user.setEmail(userForm.getEmail());
        user.setFirstName(userForm.getFirstName());
        user.setLastName(userForm.getLastName());
        String password = userForm.getPassword();
        if (password.length() > 0) {
            String passwordEncoded = bCryptPasswordEncoder.encode(password);
            user.setPassword(passwordEncoded);
        }

        if (user.getUsername().equals(userForm.getUsername())) {
            user.setUsername(userForm.getUsername());
            userService.save(user);

            return "user/user-detail";
        } else {
            user.setUsername(userForm.getUsername());

            if (user.getId().equals(securityService.getCurrentUser().getId())) {
                HttpSession session = request.getSession();
                SecurityContextHolder.clearContext();
                if (session != null) {
                    session.invalidate();
                }

                userService.save(user);

                return "redirect:/login";
            } else {
                deleteAnotherUserSession(user);

                userService.save(user);

                return "user/user-detail";
            }
        }
    }

    private void deleteAnotherUserSession(UserImp user) {
        List<Object> usersPrincipals = sessionRegistry.getAllPrincipals();

        for (Object userPrincipal : usersPrincipals) {
            UserImp tempUser = userService.findByUsername(((User) userPrincipal).getUsername());

            if (tempUser.equals(user)) {
                List<SessionInformation> sessions = sessionRegistry.getAllSessions(userPrincipal, false);
                sessionRegistry.getSessionInformation(sessions.get(0).getSessionId()).expireNow();
            }
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'REPORTER', 'TESTER')")
    @GetMapping("/users/{id}/delete")
    public String userDelete(Model model, @PathVariable(value = "id") String id, HttpServletRequest request) {
        if (!securityService.isCurrentUserById(id) && !securityService.isCurrentUserAdmin()) {
            return "error/error-401";
        }

        UserImp user = userService.findById(id);

        if (user.getId().equals(securityService.getCurrentUser().getId())) {
            HttpSession session = request.getSession();
            SecurityContextHolder.clearContext();
            if (session != null) {
                session.invalidate();
            }

            userService.delete(user);

            return "redirect:/login";
        } else {
            deleteAnotherUserSession(user);

            userService.delete(user);

            return "redirect:/users";
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'REPORTER')")
    @GetMapping("/users/{id}/recharge-credit")
    public String userRechargeCredits(Model model, @PathVariable(value = "id") String id) {

        if (!securityService.isCurrentUserById(id)) {
            return "error/error-401";
        }

        model.addAttribute("user", userService.findById(id));
        return "user/user-recharge-credit";

    }
}
