package com.sinanmutlu.vendingmachine.mapper;

import com.sinanmutlu.vendingmachine.dto.UserDto;
import com.sinanmutlu.vendingmachine.dto.UserReqDto;;
import com.sinanmutlu.vendingmachine.entity.User;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface UserMapper {

    UserDto toDto(User user);
    User toEntity(UserDto userDto);
    User toEntity(UserReqDto userReqDto);
    List<UserDto> toDtos(List<User> userList);
}