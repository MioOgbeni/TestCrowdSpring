package cz.spitsoft.testcrowd.controller;

import cz.spitsoft.testcrowd.model.software_type.SoftwareTypeImp;
import cz.spitsoft.testcrowd.model.user.UserImp;
import cz.spitsoft.testcrowd.service.SecurityService;
import cz.spitsoft.testcrowd.service.SoftwareTypeService;
import cz.spitsoft.testcrowd.service.UserService;
import cz.spitsoft.testcrowd.validator.UserValidator;
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
public class SoftwareTypeController {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private SoftwareTypeService softwareTypeService;

    @GetMapping("/software-types")
    public String softwareTypeList(Model model, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "10") int size) {
        Page<SoftwareTypeImp> softwareTypes = softwareTypeService.findAll(PageRequest.of(page, size));
        model.addAttribute("softwareTypes", softwareTypes);
        int totalPages = softwareTypes.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(0, totalPages - 1)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "software-type/software-type-list";
    }

    @GetMapping("/software-types/{id}")
    public String softwareTypeDetail(Model model, @PathVariable(value = "id") String id) {
        model.addAttribute("softwareType", softwareTypeService.findById(id));
        return "software-type/software-type-detail";
    }

    @GetMapping("/software-types/add")
    public String softwareTypeAdd(Model model) {
        //if (securityService.isCurrentUserById(id) || securityService.isCurrentUserAdmin()) {
        model.addAttribute("softwareType", new SoftwareTypeImp());
        return "software-type/software-type-add";
        //}
        //return "redirect:/";
    }

    @PostMapping("/software-types/add")
    public String softwareTypeAdd(@ModelAttribute("softwareType") SoftwareTypeImp softwareType, BindingResult bindingResult) {
        //if (securityService.isCurrentUserById(id) || securityService.isCurrentUserAdmin()) {

        Date currentDate = new Date();
        UserImp currentUser = securityService.getCurrentUser();
        softwareType.setCreatedAt(currentDate);
        softwareType.setUpdatedAt(currentDate);
        softwareType.setCreatedBy(currentUser);
        softwareType.setUpdatedBy(currentUser);

        //userValidator.validate(user, bindingResult);
        //if (bindingResult.hasErrors()) {
        //    System.out.println(bindingResult.getAllErrors());
        //    return "software-type/software-type-add";
        //}

        // save user
        softwareTypeService.save(softwareType);
        //return "software-type/software-type-detail";
        //}
        return "redirect:/";
    }

    @GetMapping("/software-types/{id}/edit")
    public String softwareTypeEdit(Model model, @PathVariable(value = "id") String id) {
        //if (securityService.isCurrentUserById(id) || securityService.isCurrentUserAdmin()) {
        model.addAttribute("user", userService.findById(id));
        return "software-type/software-type-edit";
        //}
        //return "redirect:/";
    }

    @PostMapping("/software-types/{id}/edit")
    public String softwareTypeEdit(@ModelAttribute("user") UserImp userForm, BindingResult bindingResult, @PathVariable(value = "id") String id) {
        //if (securityService.isCurrentUserById(id) || securityService.isCurrentUserAdmin()) {
        // load user
        UserImp user = userService.findById(id);

        // edit user posted data
        // TODO: Edit user password.
        // TODO: Automatically logout if username, email or password are updated.
        user.setUsername(userForm.getUsername());
        user.setEmail(userForm.getEmail());
        user.setFirstName(userForm.getFirstName());
        user.setLastName(userForm.getLastName());

        // validate user
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            return "software-type/software-type-edit";
        }

        // save user
        userService.save(user);
        return "software-type/software-type-detail";
        //}
        //return "redirect:/";
    }

    @GetMapping("/software-types/{id}/delete")
    public String softwareTypeDelete(Model model, @PathVariable(value = "id") String id) {
        //if (securityService.isCurrentUserById(id) || securityService.isCurrentUserAdmin()) {
        UserImp user = userService.findById(id);
        // TODO: Automatically logout deleted user.
        userService.delete(user);
        return "software-type/software-type-list";
        //}
        //return "redirect:/";
    }
}
