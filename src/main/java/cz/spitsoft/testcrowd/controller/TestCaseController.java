package cz.spitsoft.testcrowd.controller;

import cz.spitsoft.testcrowd.model.file.FileImp;
import cz.spitsoft.testcrowd.model.review.ReviewImp;
import cz.spitsoft.testcrowd.model.test_case.TestCaseImp;
import cz.spitsoft.testcrowd.model.test_case.TestStatus;
import cz.spitsoft.testcrowd.model.user.UserImp;
import cz.spitsoft.testcrowd.service.*;
import cz.spitsoft.testcrowd.validator.TestCaseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
    public String testCaseList(Model model, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "10") int size, @RequestParam(value = "search", defaultValue = "") String search) {

        // get test cases by user role
        UserImp currentUser = securityService.getCurrentUser();
        Page<TestCaseImp> testCases;
        if (securityService.isCurrentUserReporter()) {
            testCases = testCaseService.findByCreatedBy(currentUser, PageRequest.of(page, size), search);
        } else {
            testCases = testCaseService.findAll(PageRequest.of(page, size), search);
        }
        MakePagedTestCases(model, testCases);

        return "test-case/test-case-list";

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

        // process files
        List<FileImp> uploadedFiles = new ArrayList<>();
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                uploadedFiles.add(fileStorageService.saveFile(file, testCase.getId()));
            }
        }
        if (!uploadedFiles.isEmpty()) {
            testCase.setFiles(uploadedFiles);
        }

        testCase.setCreatedAt(currentDate);
        testCase.setCreatedBy(securityService.getCurrentUser());
        testCaseService.save(testCase);
        return "redirect:/test-cases";
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
        model.addAttribute("review", new ReviewImp());
        return "test-case/test-case-detail";

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

        // process files
        List<FileImp> uploadedFiles = testCase.getFiles();
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                uploadedFiles.add(fileStorageService.saveFile(file, testCase.getId()));
            }
        }

        if (!uploadedFiles.isEmpty()) {
            testCase.setFiles(uploadedFiles);
        }

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

        List<FileImp> filesToDelete = testCase.getFiles();

        // delete test case and return test case list
        testCaseService.delete(testCase);

        // delete test case files
        for (FileImp file : filesToDelete) {
            fileStorageService.deleteFile(file);
        }

        return "redirect:/test-cases";
    }

    @GetMapping("/test-cases/{testCaseId}/download/{fileId}")
    public @ResponseBody
    void downloadFile(@PathVariable String testCaseId, @PathVariable String fileId, HttpServletResponse response) {
        // Load file from database
        FileImp file = fileStorageService.getFile(fileId);

        try {
            // get your file as InputStream
            InputStream is = new FileInputStream(file.getFilePath() + File.separator + file.getFileName());

            // copy it to response's OutputStream
            response.addHeader("Content-Disposition", "attachment; filename=" + file.getFileName());
            FileCopyUtils.copy(is, response.getOutputStream());
        } catch (IOException ex) {
            throw new RuntimeException("Error writing file to output stream. Filename was " + file.getFileName(), ex);
        }
    }

    @GetMapping("/test-cases/{testCaseId}/delete/{fileId}")
    public String deleteFile(@PathVariable String testCaseId, @PathVariable String fileId) {
        // Load file from database
        FileImp file = fileStorageService.getFile(fileId);
        TestCaseImp testCase = testCaseService.findById(testCaseId);

        // process files
        List<FileImp> uploadedFiles = testCase.getFiles();
        uploadedFiles.remove(file);
        testCase.setFiles(uploadedFiles);

        testCaseService.save(testCase);

        fileStorageService.deleteFile(file);

        return "redirect:/test-cases/" + testCaseId + "/edit";
    }
}
