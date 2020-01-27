package cz.spitsoft.testcrowd.controller;

import cz.spitsoft.testcrowd.model.test_case.TestCaseImp;
import cz.spitsoft.testcrowd.repository.TestCaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class TestCaseController {

    @Autowired
    private TestCaseRepository testCaseRepository;

    @GetMapping("tests")
    public String testCases(Model model) {
        model.addAttribute("testcases", this.testCaseRepository.findAll());
        return "tests";
    }

    @PostMapping("add")
    public String addTestCase() {
        TestCaseImp tempTest = new TestCaseImp();
        tempTest.setName("temp");
        this.testCaseRepository.save(tempTest);
        return "redirect:tests";
    }
}
