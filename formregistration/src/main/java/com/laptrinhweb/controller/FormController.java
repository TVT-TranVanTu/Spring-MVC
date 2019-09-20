package com.laptrinhweb.controller;

import com.laptrinhweb.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;



import javax.validation.Valid;

@Controller
public class FormController {
    @GetMapping("/")
    public String showForm(Model model){
        model.addAttribute("user", new User());
        return "index";
    }

    @PostMapping("/")
    public String checkValidation (@Validated @ModelAttribute("user") User user, BindingResult bindingResult){
        if (bindingResult.hasFieldErrors()){
            return "index";
        }
        return "result";
    }

}
