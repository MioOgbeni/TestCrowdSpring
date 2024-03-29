package cz.spitsoft.testcrowd.validator;

import cz.spitsoft.testcrowd.model.user.UserImp;
import cz.spitsoft.testcrowd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return UserImp.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        UserImp user = (UserImp) object;

        String username = user.getUsername().trim();
        if (username.length() < 1) {
            errors.rejectValue("username", "empty");
        } else if (!username.matches("^[0-9a-zA-Z-._]+$")) {
            errors.rejectValue("username", "invalid");
        } else if (username.length() < 4 || username.length() > 40) {
            errors.rejectValue("username", "length");
        } else {
            UserImp userByUsername;
            try {
                userByUsername = userService.findByUsername(username);
            } catch (Exception e) {
                userByUsername = null;
            }

            if (userByUsername != null && !userByUsername.getId().equals(user.getId())) {
                errors.rejectValue("username", "duplication");
            }
        }

        String email = user.getEmail().trim();
        if (email.length() < 1) {
            errors.rejectValue("email", "empty");
        } else if (!email.matches("^[0-9a-zA-Z-._]+@[0-9a-zA-Z-]+.[0-9a-zA-Z-.]+$")) {
            errors.rejectValue("email", "invalid");
        } else {
            UserImp userByEmail;
            try {
                userByEmail = userService.findByEmail(username);
            } catch (Exception e) {
                userByEmail = null;
            }

            if (userByEmail != null && !userByEmail.getEmail().equals(user.getEmail())) {
                errors.rejectValue("email", "duplication");
            }
        }

        String firstName = user.getFirstName().trim();
        if (firstName.length() > 80) {
            errors.rejectValue("firstName", "length");
        }

        String lastName = user.getLastName().trim();
        if (lastName.length() > 80) {
            errors.rejectValue("lastName", "length");
        }

        String password = user.getPassword();
        if (password.length() > 0) {
            if (password.length() < 8 || password.length() > 40) {
                errors.rejectValue("password", "length");
            }
            String passwordConfirm = user.getPasswordConfirm();
            if (!passwordConfirm.equals(password)) {
                errors.rejectValue("passwordConfirm", "difference");
            }
        }
    }
}