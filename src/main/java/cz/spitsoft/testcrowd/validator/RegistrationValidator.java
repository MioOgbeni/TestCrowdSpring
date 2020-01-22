package cz.spitsoft.testcrowd.validator;

import cz.spitsoft.testcrowd.model.UserImp;
import cz.spitsoft.testcrowd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class RegistrationValidator implements Validator {
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
        } else if (username.length() < 6 || username.length() > 32) {
            errors.rejectValue("username", "length");
        } else if (userService.findByUsername(username) != null) {
            errors.rejectValue("username", "duplication");
        }

        String email = user.getEmail().trim();
        if (email.length() < 1) {
            errors.rejectValue("email", "empty");
        } else if (!email.matches("^[0-9a-zA-Z-._]+@[0-9a-zA-Z-]+.[0-9a-zA-Z-.]+$")) {
            errors.rejectValue("email", "invalid");
        } else if (userService.findByEmail(email) != null) {
            errors.rejectValue("email", "duplication");
        }

        String password = user.getPassword();
        if (password.length() < 40) {
            if (password.length() < 1) {
                errors.rejectValue("password", "empty");
            } else if (password.length() < 8 || password.length() > 32) {
                errors.rejectValue("password", "length");
            }
        }

        String passwordConfirm = user.getPasswordConfirm();
        if (passwordConfirm.length() < 1) {
            errors.rejectValue("passwordConfirm", "empty");
        } else if (!passwordConfirm.equals(password)) {
            errors.rejectValue("passwordConfirm", "difference");
        }
    }
}