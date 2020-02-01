package cz.spitsoft.testcrowd.validator;

import cz.spitsoft.testcrowd.model.review.ReviewImp;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ReviewValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return ReviewImp.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        ReviewImp review = (ReviewImp) object;
    }
    
}