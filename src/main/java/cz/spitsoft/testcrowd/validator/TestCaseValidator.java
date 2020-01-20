package cz.spitsoft.testcrowd.validator;

import cz.spitsoft.testcrowd.model.testcases.TestCaseImp;
//import cz.spitsoft.testcrowd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class TestCaseValidator implements Validator {
    //@Autowired
    //private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return TestCaseImp.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        TestCaseImp user = (TestCaseImp) object;
    }
}