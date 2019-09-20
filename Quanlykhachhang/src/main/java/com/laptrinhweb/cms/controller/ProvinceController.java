package com.laptrinhweb.cms.controller;


import com.laptrinhweb.cms.model.Customer;
import com.laptrinhweb.cms.model.Province;
import com.laptrinhweb.cms.service.CustomerService;
import com.laptrinhweb.cms.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProvinceController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ProvinceService provinceService;

    @GetMapping("/provinces")
    public ModelAndView listProvince(){
        Iterable<Province> provinces= provinceService.findAll();
        ModelAndView modelAndView = new ModelAndView("/provinces/list");
        modelAndView.addObject("provinces",provinces);
        return modelAndView;
    }
    @GetMapping("/create-provinces")
    public ModelAndView ShowCreateForm(){
        ModelAndView modelAndView= new ModelAndView("/provinces/create");
        modelAndView.addObject("province",new Province());
        return modelAndView;
    }
    @PostMapping("/create-provinces")
    public ModelAndView SaveProvinces(@ModelAttribute("province") Province province){
        provinceService.save(province);
        ModelAndView modelAndView= new ModelAndView("/provinces/create");
        modelAndView.addObject("province",new Province());
        modelAndView.addObject("message","New province create successfully !");
        return modelAndView;
    }
    @GetMapping("/edit-provinces/{id}")
    public ModelAndView ShowEditForm(@PathVariable Long id){
        Province province= provinceService.findById(id);
        if(province != null){
            ModelAndView modelAndView= new ModelAndView("/provinces/edit");
            modelAndView.addObject("province",province);
            return modelAndView;
        }
        else {
            ModelAndView modelAndView= new ModelAndView("/error.404");
            return modelAndView;
        }
    }
    @PostMapping("/edit-provinces")
    public ModelAndView UpdateProvince(@ModelAttribute("province") Province province){
        provinceService.save(province);
        ModelAndView modelAndView= new ModelAndView("/provinces/edit");
        modelAndView.addObject("province",new Province());
        modelAndView.addObject("message","Update province successfully !");
        return modelAndView;
    }
    @GetMapping("/delete-provinces/{id}")
    public ModelAndView ShowDeleteForm(@PathVariable Long id){
        Province province= provinceService.findById(id);
        if(province != null){
            ModelAndView modelAndView = new ModelAndView("/provinces/delete");
            modelAndView.addObject("province",province);
            return modelAndView;
        }
        else {
            ModelAndView modelAndView= new ModelAndView("/error.404");
            return modelAndView;
        }
    }
    @PostMapping("/delete-provinces")
    public String DeleteProvince(@ModelAttribute("province") Province province){
        provinceService.remove(province.getId());
        return "redirect:provinces";
    }
    @GetMapping("/view-provinces/{id}")
    public ModelAndView ViewProvince(@PathVariable Long id){
        Province province = provinceService.findById(id);
        if(province ==null){
            return new ModelAndView("/error.404");
        }
        Iterable<Customer> customers= customerService.findAllByProvince(province);
        ModelAndView modelAndView=new ModelAndView("/provinces/view");
        modelAndView.addObject("province",province);
        modelAndView.addObject("customers",customers);
        return modelAndView;
    }
}
