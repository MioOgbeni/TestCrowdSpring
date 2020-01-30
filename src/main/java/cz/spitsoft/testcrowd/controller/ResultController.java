package cz.spitsoft.testcrowd.controller;

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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class ResultController {

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
    private UserService userService;
}
