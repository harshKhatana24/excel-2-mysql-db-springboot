package com.algorithmavengers.excel2database.Controllers;


import com.algorithmavengers.excel2database.Services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/home")
    public String homePage(){
        return "index";
    }

}
