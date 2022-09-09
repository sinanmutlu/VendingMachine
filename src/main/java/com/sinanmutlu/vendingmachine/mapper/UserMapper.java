package com.sinanmutlu.vendingmachine.mapper;

import com.sinanmutlu.vendingmachine.dto.UserDto;
import com.sinanmutlu.vendingmachine.dto.UserReqDto;;
import com.sinanmutlu.vendingmachine.entity.UserEnt;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface UserMapper {

    UserDto toDto(UserEnt user);
    UserEnt toEntity(UserDto userDto);
    UserEnt toEntity(UserReqDto userReqDto);
    List<UserDto> toDtos(List<UserEnt> userList);
}