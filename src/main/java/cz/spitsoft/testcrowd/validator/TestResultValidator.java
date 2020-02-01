package cz.spitsoft.testcrowd.validator;

import cz.spitsoft.testcrowd.model.test_case.TestResultImp;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class TestResultValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return TestResultImp.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        TestResultImp testResult = (TestResultImp) object;
    }

}