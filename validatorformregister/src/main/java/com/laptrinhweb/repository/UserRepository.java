package com.laptrinhweb.repository;

import com.laptrinhweb.entity.UserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<UserEntity,Long> {
    boolean existsByEmail(String email);
}
