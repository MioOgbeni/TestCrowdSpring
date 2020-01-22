package cz.spitsoft.testcrowd.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class MainController {

    @GetMapping({"/", "/welcome", "/index"})
    public String welcome(Model model) {
        return "index";
    }

}
