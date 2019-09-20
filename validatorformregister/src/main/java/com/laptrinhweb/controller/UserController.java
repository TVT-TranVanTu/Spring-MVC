package com.laptrinhweb.controller;

import com.laptrinhweb.convert.UserConverter;
import com.laptrinhweb.request.UserRequest;
import com.laptrinhweb.service.UserService;
import com.laptrinhweb.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;
    @GetMapping("/register")
    public ModelAndView ShowForm(){
        ModelAndView modelAndView= new ModelAndView("/user/register");
        modelAndView.addObject("userRequest",new UserRequest());
        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView CheckValidator(@Valid @ModelAttribute("userRequest") UserRequest userRequest, BindingResult bindingResult){
        userValidator.validate(userRequest,bindingResult);
        if(bindingResult.hasFieldErrors()){
          ModelAndView  modelAndView = new ModelAndView("/user/register");
            modelAndView.addObject("user",userRequest);
            return modelAndView;
        }
        else{
            userService.save(UserConverter.convertUserRequestToUserEntity(userRequest));
            ModelAndView modelAndView= new ModelAndView("/user/result");
            modelAndView.addObject("message","New customer created successfully");
            return modelAndView;
        }
    }
}
