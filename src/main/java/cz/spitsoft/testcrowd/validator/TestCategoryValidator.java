package cz.spitsoft.testcrowd.validator;

import cz.spitsoft.testcrowd.model.test_category.TestCategoryImp;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class TestCategoryValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return TestCategoryImp.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        TestCategoryImp testCategory = (TestCategoryImp) object;

        String name = testCategory.getName().trim();
        if (name.length() < 1) {
            errors.rejectValue("name", "empty");
        } else if (name.length() > 80) {
            errors.rejectValue("name", "length");
        }

        String description = testCategory.getDescription().trim();
        if (description.length() > 240) {
            errors.rejectValue("description", "length");
        }

        Boolean enabled = testCategory.getEnabled();
        if (enabled == null) {
            errors.rejectValue("enabled", "incorrect");
        }
    }

}