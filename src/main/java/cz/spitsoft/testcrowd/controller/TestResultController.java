package cz.spitsoft.testcrowd.controller;

import cz.spitsoft.testcrowd.model.test_case.TestCaseImp;
import cz.spitsoft.testcrowd.model.test_case.TestResultImp;
import cz.spitsoft.testcrowd.model.test_case.TestResultStatus;
import cz.spitsoft.testcrowd.model.user.UserImp;
import cz.spitsoft.testcrowd.service.SecurityService;
import cz.spitsoft.testcrowd.service.TestCaseService;
import cz.spitsoft.testcrowd.service.TestResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class TestResultController {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private TestResultService testResultService;

    @Autowired
    private TestCaseService testCaseService;

    private void MakePagedTestResults(Model model, Page<TestResultImp> testResults) {
        model.addAttribute("testResults", testResults);

        int totalPages = testResults.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(0, totalPages - 1)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pages", pageNumbers);
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'TESTER')")
    @GetMapping("/test-results")
    public String testResultList(Model model, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "10") int size) {

        Page<TestResultImp> testResults;
        if (securityService.isCurrentUserAdmin()) {
            testResults = testResultService.findAll(PageRequest.of(page, size));
        } else {
            UserImp currentUser = securityService.getCurrentUser();
            testResults = testResultService.findByUser(currentUser, PageRequest.of(page, size));
        }
        MakePagedTestResults(model, testResults);

        return "test-result/test-result-list";

    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'REPORTER', 'TESTER')")
    @GetMapping("/test-cases/{testCaseId}/take")
    public String testResultAdd(Model model, @PathVariable(value = "testCaseId") String testCaseId) {

        // create test result
        TestCaseImp testCase = testCaseService.findById(testCaseId);
        UserImp currentUser = securityService.getCurrentUser();
        Date currentDate = new Date();
        TestResultImp testResult = new TestResultImp();
        testResult.setTestCase(testCase);
        testResult.setUser(currentUser);
        testResult.setTestResultStatus(TestResultStatus.TAKEN);
        testResult.setTakenAt(currentDate);
        testResult.setReward(testCase.getReward());

        // TODO: odebrat reward od original user


        // save test result and redirect to his detail
        TestResultImp result = testResultService.save(testResult);
        return "redirect:/test-results/" + result.getId();

    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'REPORTER', 'TESTER')")
    @GetMapping("/test-cases/{testCaseId}/test-results")
    public String testCaseTestResultList(Model model, @PathVariable(value = "testCaseId") String testCaseId, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "10") int size) {

        Page<TestResultImp> testResults;
        TestCaseImp currentTestCase = testCaseService.findById(testCaseId);
        if (securityService.isCurrentUserAdmin()) {
            testResults = testResultService.findByTestCase(currentTestCase, PageRequest.of(page, size));
        } else {
            UserImp currentUser = securityService.getCurrentUser();
            testResults = testResultService.findByTestCaseAndUser(currentTestCase, currentUser, PageRequest.of(page, size));
        }
        MakePagedTestResults(model, testResults);
        model.addAttribute("testCase", currentTestCase);

        return "test-result/test-result-list";

    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'TESTER')")
    @GetMapping("/test-results/{id}")
    public String testResultDetail(Model model, @PathVariable(value = "id") String id) {

        // load test result
        TestResultImp testResult = testResultService.findById(id);

        // return test result detail
        model.addAttribute("testResult", testResult);
        return "test-result/test-result-detail";

    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'TESTER')")
    @GetMapping("/test-results/{id}/finish")
    public String testResultFinish(Model model, @PathVariable(value = "id") String id) {

        // load test result
        TestResultImp testResult = testResultService.findById(id);

        // return test result edit form
        model.addAttribute("testResult", testResult);
        return "test-result/test-result-edit";

    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'TESTER')")
    @PostMapping("/test-results/{id}/finish")
    public String testResultFinish(Model model,
                                   @ModelAttribute("testResult") TestResultImp testResultForm,
                                   @RequestParam("file") MultipartFile[] files,
                                   BindingResult bindingResult,
                                   @PathVariable(value = "id") String id) {

        // load, finish and save test result
        Date currentDate = new Date();
        TestResultImp testResult = testResultService.findById(id);
        testResult.setTestResultStatus(TestResultStatus.DONE);
        testResult.setFinishedAt(currentDate);
        // TODO: pridat reward k user

        testResultService.save(testResult);
        return "redirect:/test-results/" + testResult.getId();

    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'TESTER')")
    @GetMapping("/test-results/{id}/delete")
    public String testResultDelete(Model model, @PathVariable(value = "id") String id) {

        // load test result
        TestResultImp testResult = testResultService.findById(id);
        // TODO: pridat reward k original user

        // delete test result and return test result list
        testResultService.delete(testResult);
        return "redirect:/test-results";

    }

}
