package cz.spitsoft.testcrowd.controller;

import cz.spitsoft.testcrowd.model.test_case.TestCaseImp;
import cz.spitsoft.testcrowd.model.test_case.TestStatus;
import cz.spitsoft.testcrowd.model.test_case.TestCaseImp;
import cz.spitsoft.testcrowd.model.user.UserImp;
import cz.spitsoft.testcrowd.service.SecurityService;
import cz.spitsoft.testcrowd.service.test_case.TestCaseService;
import cz.spitsoft.testcrowd.validator.TestCaseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class TestCaseController {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private TestCaseValidator testCaseValidator;

    @Autowired
    private TestCaseService testCaseService;

    /*@PreAuthorize("hasAuthority('REPORTER')")
    @GetMapping("/test-cases")
    public String testCaseListForReporter(Model model, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "10") int size) {
        // TODO dodělat nějaké vyhledávání a filtrování, alespon vyhledávání podle jména a filtrování podle time a skill difficulty

        //Page<TestCaseImp> testCases = testCaseService.findAllAvailableToBeforeAndCreatedBy(new Date(), securityService.getCurrentUser(), PageRequest.of(page, size));
        //MakePagedTestCases(model, testCases);

        return "test-case/test-case-list";
    }*/

    /*@PreAuthorize("hasAuthority('TESTER')")
    @GetMapping("/test-cases")
    public String testCaseListForTester(Model model, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "10") int size) {
        // TODO dodělat nějaké vyhledávání a filtrování, alespon vyhledávání podle jména a filtrování podle time a skill difficulty

        Page<TestCaseImp> testCases = testCaseService.findAllAvailableToBefore(new Date(), PageRequest.of(page, size));
        MakePagedTestCases(model, testCases);

        return "test-case/test-case-list";
    }*/

    private void MakePagedTestCases(Model model, Page<TestCaseImp> testCases) {
        model.addAttribute("testCases", testCases);

        int totalPages = testCases.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(0, totalPages - 1)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pages", pageNumbers);
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'REPORTER', 'TESTER')")
    @GetMapping("/test-cases")
    public String testCaseList(Model model, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "10") int size) {
        Page<TestCaseImp> testCases = testCaseService.findAll(PageRequest.of(page, size));
        model.addAttribute("testCases", testCases);

        int totalPages = testCases.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(0, totalPages - 1)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pages", pageNumbers);
        }

        return "test-case/test-case-list";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'REPORTER', 'TESTER')")
    @GetMapping("/test-cases/{id}")
    public String testCaseDetail(Model model, @PathVariable(value = "id") String id) {
        model.addAttribute("testCase", testCaseService.findById(id));
        return "test-case/test-case-detail";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'REPORTER')")
    @GetMapping("/test-cases/add")
    public String testCaseAdd(Model model) {
        model.addAttribute("testCase", new TestCaseImp());
        return "test-case/test-case-add";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'REPORTER')")
    @PostMapping("/test-cases/add")
    public String testCaseAdd(@ModelAttribute("testCase") TestCaseImp testCase, BindingResult bindingResult) {
        testCaseValidator.validate(testCase, bindingResult);
        if (bindingResult.hasErrors()) {
            return "test-case/test-case-add";
        }

        Date currentDate = new Date();
        UserImp currentUser = securityService.getCurrentUser();
        testCase.setTestStatus(TestStatus.AVAILABLE);
        testCase.setCreatedAt(currentDate);
        testCase.setCreatedBy(currentUser);
        testCaseService.save(testCase);
        return "redirect:/test-cases";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'REPORTER')")
    @GetMapping("/test-cases/{id}/edit")
    public String testCaseEdit(Model model, @PathVariable(value = "id") String id) {
        model.addAttribute("testCase", testCaseService.findById(id));
        return "test-case/test-case-edit";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'REPORTER')")
    @PostMapping("/test-cases/{id}/edit")
    public String testCaseEdit(@ModelAttribute("testCase") TestCaseImp testCaseForm, BindingResult bindingResult, @PathVariable(value = "id") String id) {
        testCaseValidator.validate(testCaseForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "test-cases/test-case-edit";
        }

        // TODO
        TestCaseImp testCase = testCaseService.findById(id);
        testCase.setName(testCaseForm.getName());
        testCase.setDescription(testCaseForm.getDescription());
        testCase.setTestCategory(testCaseForm.getTestCategory());
        testCase.setSoftwareType(testCaseForm.getSoftwareType());
        testCase.setAvailableTo(testCaseForm.getAvailableTo());
        testCase.setFiles(testCaseForm.getFiles());
        testCase.setInGroup(testCaseForm.isInGroup());
        testCase.setRating(testCaseForm.getRating());
        testCase.setReviews(testCaseForm.getReviews());
        testCase.setReward(testCaseForm.getReward());
        testCase.setSkillDifficulty(testCaseForm.getSkillDifficulty());
        testCase.setTimeDifficulty(testCaseForm.getTimeDifficulty());
        testCase.setTestStatus(testCaseForm.getTestStatus());
        testCaseService.save(testCase);
        return "redirect:/test-case/" + testCase.getId();
    }
}
