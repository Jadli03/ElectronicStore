package com.spring.eStore.service;

import com.spring.eStore.dto.UserDto;
import com.spring.eStore.entity.User;

import java.util.List;

public interface UserService {

    //create
    UserDto creatUser(UserDto userDto);
    //update
    UserDto updateUser(UserDto userDto,String userId);
    //delete
    void delete(String userId);
    //get all User
    List<UserDto> getAllUsers();
    // get single user by id
    UserDto getUserById(String userId);
    // get single user by email
    UserDto getUserByEmail(String email);
    // search user
    List<UserDto> searchUser(String keyword);
}
