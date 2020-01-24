package cz.spitsoft.testcrowd.controller;

import cz.spitsoft.testcrowd.model.test_category.TestCategoryImp;
import cz.spitsoft.testcrowd.model.user.UserImp;
import cz.spitsoft.testcrowd.service.SecurityService;
import cz.spitsoft.testcrowd.service.test_category.TestCategoryService;
import cz.spitsoft.testcrowd.validator.TestCategoryValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    @GetMapping("/test-categories/{id}")
    public String testCategoryDetail(Model model, @PathVariable(value = "id") String id) {
        model.addAttribute("testCategory", testCategoryService.findById(id));
        return "test-category/test-category-detail";
    }

    @GetMapping("/test-categories/add")
    public String testCategoryAdd(Model model) {
        if (!securityService.isCurrentUserAdmin()) {
            return "redirect:/";
        }

        model.addAttribute("testCategory", new TestCategoryImp());
        return "test-category/test-category-add";
    }

    @PostMapping("/test-categories/add")
    public String testCategoryAdd(@ModelAttribute("testCategory") TestCategoryImp testCategory, BindingResult bindingResult) {
        if (!securityService.isCurrentUserAdmin()) {
            return "redirect:/";
        }

        Date currentDate = new Date();
        UserImp currentUser = securityService.getCurrentUser();
        testCategory.setUpdatedAt(currentDate);
        testCategory.setUpdatedBy(currentUser);
        testCategory.setCreatedAt(currentDate);
        testCategory.setCreatedBy(currentUser);

        testCategoryValidator.validate(testCategory, bindingResult);
        if (bindingResult.hasErrors()) {
            return "test-category/test-category-add";
        }

        testCategoryService.save(testCategory);
        return "redirect:/test-categories";
    }

    @GetMapping("/test-categories/{id}/edit")
    public String testCategoryEdit(Model model, @PathVariable(value = "id") String id) {
        if (!securityService.isCurrentUserAdmin()) {
            return "redirect:/";
        }

        model.addAttribute("testCategory", testCategoryService.findById(id));
        return "test-category/test-category-edit";
    }

    @PostMapping("/test-categories/{id}/edit")
    public String testCategoryEdit(@ModelAttribute("testCategory") TestCategoryImp testCategoryForm, BindingResult bindingResult, @PathVariable(value = "id") String id) {
        if (!securityService.isCurrentUserAdmin()) {
            return "redirect:/";
        }

        TestCategoryImp testCategory = testCategoryService.findById(id);

        testCategory.setName(testCategoryForm.getName());
        testCategory.setDescription(testCategoryForm.getDescription());
        testCategory.setEnabled(testCategoryForm.getEnabled());

        Date currentDate = new Date();
        UserImp currentUser = securityService.getCurrentUser();
        testCategory.setUpdatedAt(currentDate);
        testCategory.setUpdatedBy(currentUser);

        testCategoryValidator.validate(testCategory, bindingResult);
        if (bindingResult.hasErrors()) {
            return "test-category/test-category-add";
        }

        testCategoryService.save(testCategory);
        return "redirect:/test-categories/" + testCategory.getId();
    }

    @GetMapping("/test-categories/{id}/delete")
    public String testCategoryDelete(Model model, @PathVariable(value = "id") String id) {
        if (!securityService.isCurrentUserAdmin()) {
            return "redirect:/";
        }

        TestCategoryImp testCategory = testCategoryService.findById(id);
        testCategoryService.delete(testCategory);
        return "redirect:/test-categories";
    }
}
