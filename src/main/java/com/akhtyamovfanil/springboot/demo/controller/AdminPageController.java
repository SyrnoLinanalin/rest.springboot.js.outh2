package com.akhtyamovfanil.springboot.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminPageController {


    @GetMapping("/adminPage")
    public String all() {
        return "admin";
    }







}
