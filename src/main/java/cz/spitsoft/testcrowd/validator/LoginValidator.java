package cz.spitsoft.testcrowd.validator;

import cz.spitsoft.testcrowd.model.user.UserImp;
import cz.spitsoft.testcrowd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class LoginValidator implements Validator {
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
        } else if (username.length() < 4 || username.length() > 32) {
            errors.rejectValue("username", "length");
        } else if (userService.findByUsername(username) == null) {
            errors.rejectValue("username", "notExist");
        }

        String password = user.getPassword();
        if (password.length() < 1) {
            errors.rejectValue("password", "empty");
        } else if (password.length() < 8 || password.length() > 40) {
            errors.rejectValue("password", "length");
        }
    }
}
