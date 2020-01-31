package cz.spitsoft.testcrowd.controller;

import cz.spitsoft.testcrowd.model.review.ReviewImp;
import cz.spitsoft.testcrowd.model.test_case.TestCaseImp;
import cz.spitsoft.testcrowd.model.user.UserImp;
import cz.spitsoft.testcrowd.service.ReviewService;
import cz.spitsoft.testcrowd.service.SecurityService;
import cz.spitsoft.testcrowd.service.TestCaseService;
import cz.spitsoft.testcrowd.validator.ReviewValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.List;

@Controller
public class ReviewController {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReviewValidator reviewValidator;

    @Autowired
    private TestCaseService testCaseService;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'TESTER')")
    @PostMapping("/test-cases/{testCaseId}/add-review")
    public String reviewAdd(Model model, @PathVariable(value = "testCaseId") String testCaseId, @ModelAttribute("review") ReviewImp review, BindingResult bindingResult) {

        // validate test case
        reviewValidator.validate(review, bindingResult);
        if (bindingResult.hasErrors()) {
            return "review/review-add";
        }

        // create review
        TestCaseImp testCase = testCaseService.findById(testCaseId);
        UserImp currentUser = securityService.getCurrentUser();
        Date currentDate = new Date();
        review.setCreatedAt(currentDate);
        review.setCreatedBy(currentUser);

        // save review and redirect to test case detail
        ReviewImp result = reviewService.save(review);
        List<ReviewImp> reviews = testCase.getReviews();
        reviews.add(result);
        testCase.setReviews(reviews);
        testCaseService.save(testCase);
        return "redirect:/test-cases/" + testCaseId;

    }

}
