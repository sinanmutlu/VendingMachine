package com.sinanmutlu.vendingmachine.controller;

import com.sinanmutlu.vendingmachine.dto.UserDto;
import com.sinanmutlu.vendingmachine.dto.UserReqDto;
import com.sinanmutlu.vendingmachine.dto.UserUpdateReqDto;
import com.sinanmutlu.vendingmachine.entity.Role;
import com.sinanmutlu.vendingmachine.service.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final UserService userService;

    @GetMapping("/all")
    public ResponseEntity<UserDto> getUser(@RequestParam Long userId) {
        logger.info("Getting user with " + userId);

        return ResponseEntity.ok(userService.getUserInfo(userId));
    }

    @PostMapping("/add")
    public ResponseEntity<UserDto> addUser(@Validated @RequestBody UserReqDto userReqDto) {

        logger.info("Adding new user with  " + userReqDto.toString() );
        return new ResponseEntity<UserDto>(userService.addUser(userReqDto), HttpStatus.OK);

    }

    @PutMapping("/update")
    public ResponseEntity<UserDto> updateUser(@Validated @RequestBody UserUpdateReqDto userUpdateReqDto) {

        logger.info("Updating user" );
        return new ResponseEntity<UserDto>(userService.updateUser(userUpdateReqDto), HttpStatus.OK);

    }

    @DeleteMapping("/remove")
    public ResponseEntity<Long> removeUser(@RequestParam Long userId) {

        logger.info("Removing user" );
        userService.removeUser(userId);

        return new ResponseEntity<>(userId, HttpStatus.OK);
    }

    @GetMapping("/insertRoles")
    public ResponseEntity<String> insertRoles() {

        return ResponseEntity.ok(userService.insertRoles());
    }
}
