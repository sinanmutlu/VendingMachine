package com.sinanmutlu.vendingmachine.service;


import com.sinanmutlu.vendingmachine.dto.UserDto;
import com.sinanmutlu.vendingmachine.dto.UserReqDto;
import com.sinanmutlu.vendingmachine.dto.UserUpdateReqDto;
import com.sinanmutlu.vendingmachine.entity.UserEnt;
import com.sinanmutlu.vendingmachine.exception.ErrorCode;
import com.sinanmutlu.vendingmachine.exception.UserException;
import com.sinanmutlu.vendingmachine.mapper.UserMapper;
import com.sinanmutlu.vendingmachine.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserEnt getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserException(ErrorCode.USER_NOT_FOUND));
    }

    @Override
    public UserDto getUserInfo(Long userId) {

        logger.info("getUserInfo " + userId.toString());
        UserEnt user = userRepository.findById(userId).orElseThrow(() -> new UserException(ErrorCode.USER_NOT_FOUND));

        UserDto userDto = userMapper.toDto(user);
        //To not show password in response (it can be masked if required)
        userDto.setPassword("****");
        logger.info(userDto.toString());

        return userDto;
    }

    @Override
    public UserEnt updateUser(UserEnt user) {
        return userRepository.save(user);
    }

    //TODO user id auth tan alıp kontrol et (auth olan endpointler için)
    @Override
    public UserDto addUser(UserReqDto userReqDto) {

        logger.info("Add new user " + userReqDto.getUsername() + ", " + userReqDto.getRoles());
        Optional<UserEnt> users = userRepository.findByUsername(userReqDto.getUsername());

        for(String role:userReqDto.getRoles().split(",")){
            role.toUpperCase();

            if(!role.contains("SELLER") && !role.contains("BUYER")){
                throw new UserException(ErrorCode.INVALID_ROLE);
            }
            if(users.isPresent() && users.get().getRoles().contains(role)){
                throw new UserException(ErrorCode.USER_ALREADY_EXIST);
            }
        }

        //TODO deposit alanı çıkarılabilir
        if (userReqDto.getDeposit() % 5 != 0){
            throw new UserException(ErrorCode.INVALID_DEPOSIT);
        }

        UserEnt user = userMapper.toEntity(userReqDto);

        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public UserDto updateUser(UserUpdateReqDto userUpdateReqDto) {
        //Assumed only password and role can be updated

        logger.info("Update user role and/or password " + userUpdateReqDto.getId());

        UserEnt user = userRepository.findById(userUpdateReqDto.getId()).orElseThrow(() -> new UserException(ErrorCode.USER_NOT_FOUND));

        logger.info("Update user role and/or password " + user.getUsername());

       String role = userUpdateReqDto.getRoles();
        role.toUpperCase();
        if(!role.contains("SELLER") && !role.contains("BUYER")){
            throw new UserException(ErrorCode.INVALID_ROLE);
        }

        user.setRoles(userUpdateReqDto.getRoles());

        //TODO check if password is changed or not, if so logout
        user.setPassword(userUpdateReqDto.getPassword());

        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public void removeUser(Long userId) {

        UserEnt user = userRepository.findById(userId).orElseThrow(() -> new UserException(ErrorCode.USER_NOT_FOUND));

        userRepository.delete(user);

        logger.info("User " + userId + " is removed");
    }

}
