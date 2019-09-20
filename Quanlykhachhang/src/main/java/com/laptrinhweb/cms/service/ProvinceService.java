package com.laptrinhweb.cms.service;

import com.laptrinhweb.cms.model.Province;

public interface ProvinceService {
    Iterable<Province> findAll();
    Province findById(Long id);
    void save(Province province);
    void remove(Long id);
}
