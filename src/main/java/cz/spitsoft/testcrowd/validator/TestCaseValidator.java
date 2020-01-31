package cz.spitsoft.testcrowd.validator;

import cz.spitsoft.testcrowd.model.test_case.TestCaseImp;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

//import cz.spitsoft.testcrowd.service.UserService;

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
        TestCaseImp testCase = (TestCaseImp) object;
    }

}