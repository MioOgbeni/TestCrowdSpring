package cz.spitsoft.testcrowd.validator;

import cz.spitsoft.testcrowd.model.user.User;
import cz.spitsoft.testcrowd.model.user.UserImp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

public class UserValidatorTest {

    private UserValidator validator;

    private User user;

    @Before
    public void setUp() {
        validator = new UserValidator();

        user = new UserImp();
        user.setEmail("testUser@testcrowd.cz");
        user.setUsername("testUser");
        user.setFirstName("Edward");
        user.setLastName("Grant");
        user.setPassword("testPassword");
        user.setPasswordConfirm("testPassword");
    }

    @Test
    public void testUserSuccess() {
        Errors errors = new BeanPropertyBindingResult(user, "user");
        validator.validate(user, errors);

        Assert.assertFalse(errors.hasErrors());
    }
}
