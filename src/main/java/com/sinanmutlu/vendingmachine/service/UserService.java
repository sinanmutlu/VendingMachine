package com.sinanmutlu.vendingmachine.service;


import com.sinanmutlu.vendingmachine.dto.UserDto;
import com.sinanmutlu.vendingmachine.dto.UserReqDto;
import com.sinanmutlu.vendingmachine.dto.UserUpdateReqDto;
import com.sinanmutlu.vendingmachine.entity.Role;
import com.sinanmutlu.vendingmachine.entity.UserEnt;

import java.util.List;

public interface UserService {

    UserEnt getUser(Long userId);

    UserDto getUserInfo(Long userId);

    void updateUser(UserEnt user);
    UserDto addUser(UserReqDto userReqDto);

    UserDto updateUser(UserUpdateReqDto userUpdateReqDto);

    void removeUser(Long userId);

    List<Role> insertRoles();
}
