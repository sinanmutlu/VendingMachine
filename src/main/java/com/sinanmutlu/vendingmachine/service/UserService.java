package com.sinanmutlu.vendingmachine.service;


import com.sinanmutlu.vendingmachine.dto.UserDto;
import com.sinanmutlu.vendingmachine.dto.UserReqDto;
import com.sinanmutlu.vendingmachine.dto.UserUpdateReqDto;
import com.sinanmutlu.vendingmachine.entity.UserEnt;

import java.util.List;

public interface UserService {

    UserEnt getUser(Long userId);

    UserDto getUserInfo(Long userId);

    UserEnt updateUser(UserEnt user);
    UserDto addUser(UserReqDto userReqDto);

    UserDto updateUser(UserUpdateReqDto userUpdateReqDto);

    void removeUser(Long userId);

}
