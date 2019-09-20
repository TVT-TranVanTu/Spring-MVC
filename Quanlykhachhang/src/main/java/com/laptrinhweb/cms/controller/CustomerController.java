package com.laptrinhweb.cms.controller;

import com.laptrinhweb.cms.model.Customer;
import com.laptrinhweb.cms.model.Province;
import com.laptrinhweb.cms.service.CustomerService;
import com.laptrinhweb.cms.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ProvinceService provinceService;

    @ModelAttribute("provinces")
    public Iterable<Province> provinces(){
        return provinceService.findAll();
    }
    @GetMapping("/customers")
    public ModelAndView listCustomer(@RequestParam("s") Optional<String> s, Pageable pageable){
        Page<Customer> customers;
        if(s.isPresent()){
            customers= customerService.findAllByFistNameContaining(s.get(),pageable);
        }
        else{
            customers=customerService.findAll(pageable);
        }
        ModelAndView modelAndView= new ModelAndView("/customer/list");
        modelAndView.addObject("customers",customers);
        return modelAndView;
    }
    @GetMapping("/create-customer")
    public ModelAndView ShowCreateForm(){
        ModelAndView modelAndView= new ModelAndView("/customer/create");
        modelAndView.addObject("customer",new Customer());
        return modelAndView;
    }
    @PostMapping("/create-customer")
    public ModelAndView SaveCustomer(@ModelAttribute("customer") Customer customer){
        customerService.save(customer);
        ModelAndView modelAndView= new ModelAndView("/customer/create");
        modelAndView.addObject("customer",new Customer());
        modelAndView.addObject("message","New customer create successfuly!");
        return modelAndView;
    }
    @GetMapping("/edit-customer/{id}")
    public ModelAndView ShowEditForm(@PathVariable Long id){
        Customer customer= customerService.findById(id);
        if(customer !=null){
            ModelAndView modelAndView= new ModelAndView("/customer/edit");
            modelAndView.addObject("customer",customer);
            return modelAndView;
        }
        else{
            ModelAndView modelAndView= new ModelAndView("/error.404");
            return modelAndView;
        }

    }
    @PostMapping("/edit-customer")
    public ModelAndView UpdateCustomer(@ModelAttribute("customer") Customer customer){
        customerService.save(customer);
        ModelAndView modelAndView= new ModelAndView("/customer/edit");
        modelAndView.addObject("customer",customer);
        modelAndView.addObject("message","Customer update successfully!");
        return modelAndView;
    }
    @GetMapping("/delete-customer/{id}")
    public ModelAndView ShowDeleteForm(@PathVariable Long id){
        Customer customer= customerService.findById(id);
        if(customer !=null){
            ModelAndView modelAndView= new ModelAndView("/customer/delete");
            modelAndView.addObject("customer",customer);
            return modelAndView;
        }
        else{
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }
    @PostMapping("/delete-customer")
    public String DeleteCustomer(@ModelAttribute("customer") Customer customer){
        customerService.remove(customer.getId());
        return "redirect:customers";
    }
}
