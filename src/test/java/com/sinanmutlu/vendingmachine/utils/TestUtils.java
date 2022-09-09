package com.sinanmutlu.vendingmachine.utils;

import com.sinanmutlu.vendingmachine.dto.UserDto;
import com.sinanmutlu.vendingmachine.dto.UserReqDto;

import java.util.Random;

public class TestUtils {

    private static Random random = new Random();

    public static UserDto user() {


        UserDto userDto = new UserDto();
        userDto.setId(random.nextLong());
        userDto.setUsername("sinanmutlu");
        userDto.setPassword("sinanpassword");
        userDto.setRoles("BUYER,SELLER");
        userDto.setDeposit(200);

        return userDto;
    }
}
