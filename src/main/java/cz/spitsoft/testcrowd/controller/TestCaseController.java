package cz.spitsoft.testcrowd.controller;

import cz.spitsoft.testcrowd.model.test_case.TestCaseImp;
import cz.spitsoft.testcrowd.service.SecurityService;
import cz.spitsoft.testcrowd.service.test_case.TestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class TestCaseController {

    @Autowired
    private SecurityService securityService;

    //@Autowired
    //private TestCaseValidator testCaseValidator;

    @Autowired
    private TestCaseService testCaseService;

    @GetMapping("/test-cases/add")
    public String testCategoryAdd(Model model) {
        if (!securityService.isCurrentUserReporter()) {
            return "redirect:/";
        }

        model.addAttribute("testCase", new TestCaseImp());
        return "test-case/test-case-add";
    }

}
