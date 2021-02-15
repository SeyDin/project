package com.greenbox.test.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping(value ={"/", "/index"})
    public String index (ModelMap modelMap){
        //System.out.println("Index controller method");
        modelMap.addAttribute("message", "Welcome=)");
        modelMap.addAttribute("title", "GreenBox inc.");
        return "index";
    }
}
