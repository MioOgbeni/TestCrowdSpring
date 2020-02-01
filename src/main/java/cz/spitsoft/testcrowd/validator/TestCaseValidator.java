package cz.spitsoft.testcrowd.validator;

import cz.spitsoft.testcrowd.model.software_type.SoftwareTypeImp;
import cz.spitsoft.testcrowd.model.test_case.TestCaseImp;
import cz.spitsoft.testcrowd.model.test_category.TestCategoryImp;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.util.Date;

@Component
public class TestCaseValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return TestCaseImp.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        TestCaseImp testCase = (TestCaseImp) object;

        String name = testCase.getName().trim();
        if (name.length() < 1) {
            errors.rejectValue("name", "empty");
        } else if (name.length() > 80) {
            errors.rejectValue("name", "length");
        }

        String description = testCase.getDescription().trim();
        if (description.length() > 240) {
            errors.rejectValue("description", "length");
        }

        int skillDifficulty = testCase.getSkillDifficulty();
        if (skillDifficulty < 1 && skillDifficulty > 5) {
            errors.rejectValue("skillDifficulty", "between");
        }

        int timeDifficulty = testCase.getTimeDifficulty();
        if (timeDifficulty < 1 && timeDifficulty > 5) {
            errors.rejectValue("timeDifficulty", "between");
        }

        int reward = testCase.getReward();
        if (reward < 1 && reward > 1000000) {
            errors.rejectValue("reward", "between");
        }

        Date availableTo = testCase.getAvailableTo();
        if (availableTo.before(java.sql.Date.valueOf(LocalDate.now()))) {
            errors.rejectValue("availableTo", "before");
        }

        SoftwareTypeImp softwareType = testCase.getSoftwareType();
        if (softwareType.getId().length() <= 1) {
            errors.rejectValue("softwareType.id", "empty");
        }

        TestCategoryImp testCategory = testCase.getTestCategory();
        if (testCategory.getId().length() <= 1) {
            errors.rejectValue("testCategory.id", "empty");
        }
    }
}