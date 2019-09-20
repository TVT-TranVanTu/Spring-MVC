package com.laptrinhweb.convert;

import com.laptrinhweb.entity.UserEntity;
import com.laptrinhweb.reponse.UserResponse;
import com.laptrinhweb.request.UserRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserConverter{

    public static UserEntity convertUserRequestToUserEntity(UserRequest userRequest) {
    return UserEntity.builder().fistname(userRequest.getFistname()).lastname(userRequest.getLastname())
            .age(userRequest.getAge()).phonenumber(userRequest.getPhonenumber()).email(userRequest.getEmail()).build();
    }
    public static UserResponse convertUserEntityToUserResponse(UserEntity userEntity) {
        return UserResponse.builder().id(userEntity.getId())
                .fistname(userEntity.getFistname())
                .lastname(userEntity.getLastname())
                .age(userEntity.getAge())
                .phonenumber(userEntity.getPhonenumber())
                .email(userEntity.getEmail()).build();
    }
    public void convertMapJava8() {
        List<UserEntity> userEntities = new ArrayList<>();
        userEntities.stream().map(UserConverter::convertUserEntityToUserResponse).collect(Collectors.toList());
    }

}
