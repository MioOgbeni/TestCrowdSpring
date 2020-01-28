package cz.spitsoft.testcrowd.controller;

import cz.spitsoft.testcrowd.model.test_case.TestGroupImp;
import cz.spitsoft.testcrowd.model.user.UserImp;
import cz.spitsoft.testcrowd.service.SecurityService;
import cz.spitsoft.testcrowd.service.test_group.TestGroupService;
import cz.spitsoft.testcrowd.validator.TestGroupValidator;
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
public class TestGroupController {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private TestGroupValidator testGroupValidator;

    @Autowired
    private TestGroupService testGroupService;

    @PreAuthorize("hasAuthority('REPORTER')")
    @GetMapping("/test-groups")
    public String testGroupListForReporter(Model model, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "10") int size) {
        // TODO dodělat nějaké vyhledávání a filtrování, alespon vyhledávání podle jména a filtrování podle time a skill difficulty

        Page<TestGroupImp> testGroups = testGroupService.findAllAvailableToBeforeAndCreatedBy(new Date(), securityService.getCurrentUser(), PageRequest.of(page, size));
        MakePagedTestGroups(model, testGroups);

        return "test-group/test-group-list";
    }

    @PreAuthorize("hasAuthority('TESTER')")
    @GetMapping("/test-groups")
    public String testGroupListForTester(Model model, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "10") int size) {
        // TODO dodělat nějaké vyhledávání a filtrování, alespon vyhledávání podle jména a filtrování podle time a skill difficulty

        Page<TestGroupImp> testGroups = testGroupService.findAllAvailableToBefore(new Date(), PageRequest.of(page, size));
        MakePagedTestGroups(model, testGroups);

        return "test-group/test-group-list";
    }

    private void MakePagedTestGroups(Model model, Page<TestGroupImp> testGroups) {
        model.addAttribute("testGroups", testGroups);

        int totalPages = testGroups.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(0, totalPages - 1)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pages", pageNumbers);
        }
    }

    @PreAuthorize("hasAnyAuthority('REPORTER', 'TESTER')")
    @GetMapping("/test-groups/{id}")
    public String testGroupDetail(Model model, @PathVariable(value = "id") String id) {
        model.addAttribute("testGroup", testGroupService.findById(id));
        return "test-group/test-group-detail";
    }

    @PreAuthorize("hasAuthority('REPORTER')")
    @GetMapping("/test-groups/add")
    public String testGroupAdd(Model model) {
        if (!securityService.isCurrentUserReporter()) {
            return "redirect:/";
        }

        model.addAttribute("testGroup", new TestGroupImp());
        return "test-group/test-group-add";
    }

    @PreAuthorize("hasAuthority('REPORTER')")
    @PostMapping("/test-groups/add")
    public String testGroupAdd(@ModelAttribute("testGroup") TestGroupImp testGroup, BindingResult bindingResult) {
        if (!securityService.isCurrentUserReporter()) {
            return "redirect:/";
        }

        testGroupValidator.validate(testGroup, bindingResult);
        if (bindingResult.hasErrors()) {
            return "test-group/test-group-add";
        }

        Date currentDate = new Date();
        UserImp currentUser = securityService.getCurrentUser();

        // TODO je to jen hodně nahrubo, doplnit zbytek

        testGroup.setCreatedAt(currentDate);
        testGroup.setCreatedBy(currentUser);
        testGroupService.save(testGroup);
        return "redirect:/test-groups";
    }

    @PreAuthorize("hasAuthority('REPORTER')")
    @PostMapping("/test-groups/{id}/edit")
    public String testGroupEdit(@ModelAttribute("testGroup") TestGroupImp testGroupForm, BindingResult bindingResult, @PathVariable(value = "id") String id) {
        if (!securityService.isCurrentUserReporter()) {
            return "redirect:/";
        }

        testGroupValidator.validate(testGroupForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "test-group/test-group-add";
        }

        TestGroupImp testGroup = testGroupService.findById(id);
        testGroup.setName(testGroupForm.getName());
        testGroup.setDescription(testGroupForm.getDescription());
        testGroup.setAvailableTo(testGroupForm.getAvailableTo());
        testGroup.setRating(testGroupForm.getRating());
        testGroup.setSkillDifficulty(testGroupForm.getSkillDifficulty());
        testGroup.setTimeDifficulty(testGroupForm.getTimeDifficulty());

        // TODO je to jen hodně nahrubo, musí se jinak přistupovat k file, inGroup, reviews, testStatus a rating (ten se musí vypočítávat s reviews, tahle metoda z modelu zmizela)

        testGroupService.save(testGroup);
        return "redirect:/test-group/" + testGroup.getId();
    }
}
