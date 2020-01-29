package cz.spitsoft.testcrowd.controller;

import cz.spitsoft.testcrowd.model.test_category.TestCategoryImp;
import cz.spitsoft.testcrowd.model.user.UserImp;
import cz.spitsoft.testcrowd.service.SecurityService;
import cz.spitsoft.testcrowd.service.TestCategoryService;
import cz.spitsoft.testcrowd.validator.TestCategoryValidator;
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
public class TestCategoryController {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private TestCategoryValidator testCategoryValidator;

    @Autowired
    private TestCategoryService testCategoryService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/test-categories")
    public String testCategoryList(Model model, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "10") int size) {
        Page<TestCategoryImp> testCategories = testCategoryService.findAll(PageRequest.of(page, size));
        model.addAttribute("testCategories", testCategories);

        int totalPages = testCategories.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(0, totalPages - 1)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pages", pageNumbers);
        }

        return "test-category/test-category-list";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/test-categories/{id}")
    public String testCategoryDetail(Model model, @PathVariable(value = "id") String id) {
        model.addAttribute("testCategory", testCategoryService.findById(id));
        return "test-category/test-category-detail";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/test-categories/add")
    public String testCategoryAdd(Model model) {
        model.addAttribute("testCategory", new TestCategoryImp());
        return "test-category/test-category-add";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/test-categories/add")
    public String testCategoryAdd(@ModelAttribute("testCategory") TestCategoryImp testCategory, BindingResult bindingResult) {
        testCategoryValidator.validate(testCategory, bindingResult);
        if (bindingResult.hasErrors()) {
            return "test-category/test-category-add";
        }

        Date currentDate = new Date();
        UserImp currentUser = securityService.getCurrentUser();
        testCategory.setUpdatedAt(currentDate);
        testCategory.setUpdatedBy(currentUser);
        testCategory.setCreatedAt(currentDate);
        testCategory.setCreatedBy(currentUser);
        testCategoryService.save(testCategory);
        return "redirect:/test-categories";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/test-categories/{id}/edit")
    public String testCategoryEdit(Model model, @PathVariable(value = "id") String id) {
        model.addAttribute("testCategory", testCategoryService.findById(id));
        return "test-category/test-category-edit";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/test-categories/{id}/edit")
    public String testCategoryEdit(@ModelAttribute("testCategory") TestCategoryImp testCategoryForm, BindingResult bindingResult, @PathVariable(value = "id") String id) {
        testCategoryValidator.validate(testCategoryForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "test-category/test-category-edit";
        }

        TestCategoryImp testCategory = testCategoryService.findById(id);
        testCategory.setName(testCategoryForm.getName());
        testCategory.setDescription(testCategoryForm.getDescription());
        testCategory.setEnabled(testCategoryForm.getEnabled());

        Date currentDate = new Date();
        UserImp currentUser = securityService.getCurrentUser();
        testCategory.setUpdatedAt(currentDate);
        testCategory.setUpdatedBy(currentUser);
        testCategoryService.save(testCategory);
        return "redirect:/test-categories/" + testCategory.getId();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/test-categories/{id}/delete")
    public String testCategoryDelete(Model model, @PathVariable(value = "id") String id) {
        TestCategoryImp testCategory = testCategoryService.findById(id);
        testCategoryService.delete(testCategory);
        return "redirect:/test-categories";
    }

}
