package cz.spitsoft.testcrowd.validator;

import cz.spitsoft.testcrowd.model.software_type.SoftwareTypeImp;
import cz.spitsoft.testcrowd.service.SoftwareTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SoftwareTypeValidator implements Validator {

    @Autowired
    private SoftwareTypeService softwareTypeService;

    @Override
    public boolean supports(Class<?> aClass) {
        return SoftwareTypeImp.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        SoftwareTypeImp softwareType = (SoftwareTypeImp) object;

        String name = softwareType.getName().trim();
        if (name.length() < 1) {
            errors.rejectValue("name", "empty");
        } else if (name.length() > 80) {
            errors.rejectValue("name", "length");
        }

        String description = softwareType.getDescription().trim();
        if (description.length() > 240) {
            errors.rejectValue("description", "length");
        }

        Boolean enabled = softwareType.getEnabled();
        if (enabled == null) {
            errors.rejectValue("enabled", "incorrect");
        }
    }

}