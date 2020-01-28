package cz.spitsoft.testcrowd.validator;

import cz.spitsoft.testcrowd.model.test_case.TestGroupImp;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class TestGroupValidator implements Validator {
    //@Autowired
    //private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return TestGroupImp.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        TestGroupImp testGroup = (TestGroupImp) object;
    }
}