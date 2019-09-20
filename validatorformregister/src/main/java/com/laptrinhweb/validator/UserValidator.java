package com.laptrinhweb.validator;

import com.laptrinhweb.request.UserRequest;
import com.laptrinhweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;
    @Override
    public boolean supports(Class<?> clazz) {
        return UserRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserRequest userRequest= (UserRequest) target;
        String phonenumber=userRequest.getPhonenumber();

        ValidationUtils.rejectIfEmpty(errors,"phonenumber","phonenumber.empty");
        if(phonenumber.length()<10 || phonenumber.length()>11){
            errors.rejectValue("phonenumber","phonenumber.length");
        }
        if(phonenumber.startsWith("0")){
            errors.rejectValue("phonenumber","phonenumber.startsWith");
        }
        if(phonenumber.matches("(^$|[0-9]*$)")){
            errors.rejectValue("phonenumber","phonenumber.matches");
        }

        if(userService.existsByEmail(userRequest.getEmail())){
            errors.rejectValue("email","email.exits");
        }

    }
}
