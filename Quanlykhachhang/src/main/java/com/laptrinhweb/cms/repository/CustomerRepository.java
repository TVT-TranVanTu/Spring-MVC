package com.laptrinhweb.cms.repository;

import com.laptrinhweb.cms.model.Customer;
import com.laptrinhweb.cms.model.Province;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerRepository extends PagingAndSortingRepository<Customer,Long> {
    Iterable<Customer> findAllByProvince(Province province);
    Page<Customer> findAllByFistnameContaining(String fistname, Pageable pageable);
}
