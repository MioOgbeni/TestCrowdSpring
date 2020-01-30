package cz.spitsoft.testcrowd.controller;

import cz.spitsoft.testcrowd.model.file.FileImp;
import cz.spitsoft.testcrowd.model.test_case.TestCaseImp;
import cz.spitsoft.testcrowd.model.test_case.TestStatus;
import cz.spitsoft.testcrowd.model.user.UserImp;
import cz.spitsoft.testcrowd.service.*;
import cz.spitsoft.testcrowd.validator.TestCaseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
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

    @Autowired
    private TestCategoryService testCategoryService;

    @Autowired
    private SoftwareTypeService softwareTypeService;

    @Autowired
    private FileService fileStorageService;

    @Autowired
    private UserService userService;

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

        UserImp currentUser = securityService.getCurrentUser();
        Page<TestCaseImp> testCases;
        if (securityService.isCurrentUserReporter()) {
            testCases = testCaseService.findByCreatedBy(currentUser, PageRequest.of(page, size));
        } else {
            testCases = testCaseService.findAll(PageRequest.of(page, size));
        }
        MakePagedTestCases(model, testCases);

        return "test-case/test-case-list";

    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'REPORTER', 'TESTER')")
    @GetMapping("/test-cases/{id}")
    public String testCaseDetail(Model model, @PathVariable(value = "id") String id) {

        // load test case
        TestCaseImp testCase = testCaseService.findById(id);

        // check if user is author of test case or admin
        if (!testCaseService.isCurrentUserAuthorOrTester(testCase)) {
            return "error/error-401";
        }

        // return test case detail
        model.addAttribute("testCase", testCase);
        return "test-case/test-case-detail";

    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'REPORTER')")
    @GetMapping("/test-cases/add")
    public String testCaseAdd(Model model) {

        // create new empty test case object and return add form
        model.addAttribute("testCase", new TestCaseImp());
        model.addAttribute("testCategories", testCategoryService.findByEnabledTrue());
        model.addAttribute("softwareTypes", softwareTypeService.findByEnabledTrue());
        return "test-case/test-case-add";

    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'REPORTER')")
    @PostMapping("/test-cases/add")
    public String testCaseAdd(Model model,
                              @ModelAttribute("testCase") TestCaseImp testCase,
                              @RequestParam("file") MultipartFile[] files,
                              BindingResult bindingResult) {

        // validate test case
        testCaseValidator.validate(testCase, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("testCategories", testCategoryService.findByEnabledTrue());
            model.addAttribute("softwareTypes", softwareTypeService.findByEnabledTrue());
            return "test-case/test-case-add";
        }

        Date currentDate = new Date();
        testCase.setTestStatus(TestStatus.AVAILABLE);

        List<FileImp> uploadedFiles = new ArrayList<>();
        for (MultipartFile file : files) {
            uploadedFiles.add(fileStorageService.saveFile(file));
        }
        testCase.setFiles(uploadedFiles);

        testCase.setCreatedAt(currentDate);
        testCase.setCreatedBy(securityService.getCurrentUser());
        testCaseService.save(testCase);
        return "redirect:/test-cases";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'REPORTER')")
    @GetMapping("/test-cases/{id}/edit")
    public String testCaseEdit(Model model, @PathVariable(value = "id") String id) {

        // load test case
        TestCaseImp testCase = testCaseService.findById(id);

        // check if user is author of test case or admin
        if (!testCaseService.isCurrentUserAuthorOrAdmin(testCase)) {
            return "error/error-401";
        }

        // return test case edit form
        model.addAttribute("testCase", testCase);
        model.addAttribute("testCategories", testCategoryService.findByEnabledTrue());
        model.addAttribute("softwareTypes", softwareTypeService.findByEnabledTrue());
        return "test-case/test-case-edit";

    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'REPORTER')")
    @PostMapping("/test-cases/{id}/edit")
    public String testCaseEdit(Model model,
                               @ModelAttribute("testCase") TestCaseImp testCaseForm,
                               @RequestParam("file") MultipartFile[] files,
                               BindingResult bindingResult,
                               @PathVariable(value = "id") String id) {

        // load test case
        TestCaseImp testCase = testCaseService.findById(id);

        // check if user is author of test case or admin
        if (!testCaseService.isCurrentUserAuthorOrAdmin(testCase)) {
            return "error/error-401";
        }

        // validate test case
        testCaseValidator.validate(testCaseForm, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("testCategories", testCategoryService.findByEnabledTrue());
            model.addAttribute("softwareTypes", softwareTypeService.findByEnabledTrue());
            return "test-case/test-case-edit";
        }

        // save test case and return test case detail
        testCase.setName(testCaseForm.getName());
        testCase.setDescription(testCaseForm.getDescription());
        testCase.setSkillDifficulty(testCaseForm.getSkillDifficulty());
        testCase.setTimeDifficulty(testCaseForm.getTimeDifficulty());
        testCase.setTestCategory(testCaseForm.getTestCategory());
        testCase.setSoftwareType(testCaseForm.getSoftwareType());

        List<FileImp> uploadedFiles = testCase.getFiles();
        for (MultipartFile file : files) {
            uploadedFiles.add(fileStorageService.saveFile(file));
        }
        testCase.setFiles(uploadedFiles);

        testCase.setReward(testCaseForm.getReward());
        testCase.setAvailableTo(testCaseForm.getAvailableTo());
        testCaseService.save(testCase);
        return "redirect:/test-cases/" + testCase.getId();

    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'REPORTER')")
    @GetMapping("/test-cases/{id}/delete")
    public String testCaseDelete(Model model, @PathVariable(value = "id") String id) {

        // load test case
        TestCaseImp testCase = testCaseService.findById(id);

        // check if user is author of test case or admin
        if (!testCaseService.isCurrentUserAuthorOrAdmin(testCase)) {
            return "error/error-401";
        }

        // delete test case and return test case list
        testCaseService.delete(testCase);
        return "redirect:/test-cases";

    }

    @GetMapping("/test-cases/download/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) {
        // Load file from database
        FileImp file = fileStorageService.getFile(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
                .body(new ByteArrayResource(file.getData()));
    }

}
