package com.laptrinhweb.controller;


import com.laptrinhweb.model.Customer;
import com.laptrinhweb.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@CrossOrigin(origins = {"*"})
@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/customers" ,method = RequestMethod.GET)
    public ResponseEntity<List<Customer>> listAllCustomer(){
        List<Customer> customers=customerService.findAll();
        if (customers.isEmpty()){
            return new ResponseEntity<List<Customer>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Customer>>(customers,HttpStatus.OK);
    }

    //read customer

    @RequestMapping(value = "/customers/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") long id){
        System.out.println("Fetching customer with id" +id);
        Customer customer=customerService.findById(id);
        if(customer ==null){
            System.out.println("Customer with" +id+ "NOT_FOUND");
            return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Customer>(customer,HttpStatus.OK);
    }

    //create customer

    @RequestMapping(value = "/customers",method = RequestMethod.POST)
    public ResponseEntity<Void> createCustomers(@RequestBody Customer customer, UriComponentsBuilder uriComponentsBuilder){
        System.out.println("creating customer"+customer.getLastname());
        customerService.save(customer);
        HttpHeaders headers=new HttpHeaders();
        headers.setLocation(uriComponentsBuilder.path("/customers/{id}").buildAndExpand(customer.getId()).toUri());
        return new ResponseEntity<Void>(headers,HttpStatus.CREATED);
    }

    //Update customer

    @RequestMapping(value = "/customers/{id}",method = RequestMethod.PUT)
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") long id,@RequestBody Customer customer){
        System.out.println("Updating customer "+id);
        Customer currentcustomer=customerService.findById(id);
        if(currentcustomer ==null){
            System.out.println("Customer with "+id+"NOT_FOUND");
            return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
        }
        currentcustomer.setFistname(customer.getFistname());
        currentcustomer.setLastname(customer.getLastname());
        currentcustomer.setId(customer.getId());

        customerService.save(currentcustomer);
        return new ResponseEntity<Customer>(currentcustomer,HttpStatus.OK);
    }

    //Delete

    @RequestMapping(value = "/customers/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") long id){
        System.out.println("fetching & delete customer with "+id);
        Customer customer=customerService.findById(id);
        if(customer ==null){
            System.out.println("Fetching & delete customer with "+id+"NOT_FOUND");
            return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
        }
        customerService.remove(id);
        return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
    }
}
