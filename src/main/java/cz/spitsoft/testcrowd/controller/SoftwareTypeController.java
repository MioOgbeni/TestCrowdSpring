package cz.spitsoft.testcrowd.controller;

import cz.spitsoft.testcrowd.model.software_type.SoftwareTypeImp;
import cz.spitsoft.testcrowd.model.user.UserImp;
import cz.spitsoft.testcrowd.service.SecurityService;
import cz.spitsoft.testcrowd.service.software_type.SoftwareTypeService;
import cz.spitsoft.testcrowd.validator.SoftwareTypeValidator;
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
public class SoftwareTypeController {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private SoftwareTypeValidator softwareTypeValidator;

    @Autowired
    private SoftwareTypeService softwareTypeService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/software-types")
    public String softwareTypeList(Model model, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "10") int size) {
        Page<SoftwareTypeImp> softwareTypes = softwareTypeService.findAll(PageRequest.of(page, size));
        model.addAttribute("softwareTypes", softwareTypes);

        int totalPages = softwareTypes.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(0, totalPages - 1)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pages", pageNumbers);
        }

        return "software-type/software-type-list";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/software-types/{id}")
    public String softwareTypeDetail(Model model, @PathVariable(value = "id") String id) {
        model.addAttribute("softwareType", softwareTypeService.findById(id));
        return "software-type/software-type-detail";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/software-types/add")
    public String softwareTypeAdd(Model model) {
        model.addAttribute("softwareType", new SoftwareTypeImp());
        return "software-type/software-type-add";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/software-types/add")
    public String softwareTypeAdd(@ModelAttribute("softwareType") SoftwareTypeImp softwareType, BindingResult bindingResult) {
        softwareTypeValidator.validate(softwareType, bindingResult);
        if (bindingResult.hasErrors()) {
            return "software-type/software-type-add";
        }

        Date currentDate = new Date();
        UserImp currentUser = securityService.getCurrentUser();
        softwareType.setUpdatedAt(currentDate);
        softwareType.setUpdatedBy(currentUser);
        softwareType.setCreatedAt(currentDate);
        softwareType.setCreatedBy(currentUser);
        softwareTypeService.save(softwareType);
        return "redirect:/software-types";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/software-types/{id}/edit")
    public String softwareTypeEdit(Model model, @PathVariable(value = "id") String id) {
        model.addAttribute("softwareType", softwareTypeService.findById(id));
        return "software-type/software-type-edit";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/software-types/{id}/edit")
    public String softwareTypeEdit(@ModelAttribute("softwareType") SoftwareTypeImp softwareTypeForm, BindingResult bindingResult, @PathVariable(value = "id") String id) {
        softwareTypeValidator.validate(softwareTypeForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "software-type/software-type-edit";
        }

        SoftwareTypeImp softwareType = softwareTypeService.findById(id);
        softwareType.setName(softwareTypeForm.getName());
        softwareType.setDescription(softwareTypeForm.getDescription());
        softwareType.setEnabled(softwareTypeForm.getEnabled());

        Date currentDate = new Date();
        UserImp currentUser = securityService.getCurrentUser();
        softwareType.setUpdatedAt(currentDate);
        softwareType.setUpdatedBy(currentUser);
        softwareTypeService.save(softwareType);
        return "redirect:/software-types/" + softwareType.getId();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/software-types/{id}/delete")
    public String softwareTypeDelete(Model model, @PathVariable(value = "id") String id) {
        SoftwareTypeImp softwareType = softwareTypeService.findById(id);
        softwareTypeService.delete(softwareType);
        return "redirect:/software-types";
    }

}
