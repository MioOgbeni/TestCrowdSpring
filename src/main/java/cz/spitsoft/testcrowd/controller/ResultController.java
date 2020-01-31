package cz.spitsoft.testcrowd.controller;

import cz.spitsoft.testcrowd.model.test_case.TestCaseImp;
import cz.spitsoft.testcrowd.model.test_case.TestResultImp;
import cz.spitsoft.testcrowd.model.test_case.TestResultStatus;
import cz.spitsoft.testcrowd.model.user.UserImp;
import cz.spitsoft.testcrowd.service.SecurityService;
import cz.spitsoft.testcrowd.service.TestCaseService;
import cz.spitsoft.testcrowd.service.TestResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;

@Controller
public class ResultController {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private TestResultService testResultService;

    @Autowired
    private TestCaseService testCaseService;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'REPORTER', 'TESTER')")
    @GetMapping("/test-cases/{testCaseId}/take")
    public String testCaseDetail(Model model, @PathVariable(value = "testCaseId") String testCaseId) {

        // create test result
        TestCaseImp testCase = testCaseService.findById(testCaseId);
        UserImp currentUser = securityService.getCurrentUser();
        Date currentDate = new Date();
        TestResultImp testResult = new TestResultImp();
        testResult.setTestCase(testCase);
        testResult.setUser(currentUser);
        testResult.setTestResultStatus(TestResultStatus.TAKEN);
        testResult.setTakenAt(currentDate);

        // save test result and return his detail
        testResultService.save(testResult);
        model.addAttribute("testResult", testResult);
        return "test-result/test-result-detail";

    }

}
