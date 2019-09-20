package com.laptrinhweb.service;

import com.laptrinhweb.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface UserService {
    Page<UserEntity> findAll(Pageable pageable);

    UserEntity findById(Long id);

    void save(UserEntity userEntity);

    void remove(Long id);

    boolean existsByEmail(String email);
}
