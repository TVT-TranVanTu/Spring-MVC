package com.laptrinhweb.cms.service.Impl;

import com.laptrinhweb.cms.model.Customer;
import com.laptrinhweb.cms.model.Province;
import com.laptrinhweb.cms.repository.CustomerRepository;
import com.laptrinhweb.cms.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public Customer findById(Long id) {
        return customerRepository.findOne(id);
    }

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void remove(Long id) {
        customerRepository.delete(id);
    }

    @Override
    public Iterable<Customer> findAllByProvince(Province province) {
        return customerRepository.findAllByProvince(province);
    }

    @Override
    public Page<Customer> findAllByFistNameContaining(String fistname, Pageable pageable) {
        return customerRepository.findAllByFistnameContaining(fistname,pageable);
    }
}
