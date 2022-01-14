package com.furkanyesilyurt.fourthHomework.converter;

import com.furkanyesilyurt.fourthHomework.dto.user.UserDTO;
import com.furkanyesilyurt.fourthHomework.dto.user.UserRegisterDto;
import com.furkanyesilyurt.fourthHomework.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserConverter {

    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

    List<UserDTO> convertAllUsersToUserDtos(List<User> users);
    UserDTO convertUserToUserDto(User user);
    User convertUserRegisterDtoToUser(UserRegisterDto userRegisterDto);
    UserRegisterDto convertUserToUserRegisterDto(User user);

}
