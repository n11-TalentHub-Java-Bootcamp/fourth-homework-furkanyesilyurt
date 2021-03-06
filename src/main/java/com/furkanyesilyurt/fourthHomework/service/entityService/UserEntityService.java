package com.furkanyesilyurt.fourthHomework.service.entityService;

import com.furkanyesilyurt.fourthHomework.converter.UserMapper;
import com.furkanyesilyurt.fourthHomework.dao.UserDAO;
import com.furkanyesilyurt.fourthHomework.dto.user.UserDTO;
import com.furkanyesilyurt.fourthHomework.dto.user.UserRegisterDto;
import com.furkanyesilyurt.fourthHomework.entity.User;
import com.furkanyesilyurt.fourthHomework.exception.userException.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserEntityService {

    private final UserDAO userDAO;

    @Transactional
    public List<UserDTO> findAll(){
        List<User> users = userDAO.findAll();
        if(users.isEmpty()){
            throw new UserNotFoundException("No users found!");
        }
        return UserMapper.INSTANCE.convertAllUsersToUserDtos(users);
    }

    @Transactional
    public UserDTO findById(Long id){
        Optional<User> optionalUser = userDAO.findById(id);
        if(optionalUser.isEmpty()){
            throw new UserNotFoundException("The user with " + id + " id number is not found!");
        }
        return UserMapper.INSTANCE.convertUserToUserDto(optionalUser.get());
    }

    @Transactional
    public UserDTO findByFirstname(String firstName){
        User user = userDAO.findByFirstName(firstName);
        if(user == null){
            throw new UserNotFoundException("The user named " + firstName + " has not been found!");
        }
        return UserMapper.INSTANCE.convertUserToUserDto(user);
    }

    @Transactional
    public UserRegisterDto save(UserRegisterDto userRegisterDto){
        User user = UserMapper.INSTANCE.convertUserRegisterDtoToUser(userRegisterDto);
        user = userDAO.save(user);
        return UserMapper.INSTANCE.convertUserToUserRegisterDto(user);
    }

    @Transactional
    public void deleteById(Long id){
        if (userDAO.findById(id).isEmpty()){
            throw new UserNotFoundException("The user with " + id + " id number is not found!");
        }
        userDAO.deleteById(id);
    }

    @Transactional
    public UserDTO update(UserRegisterDto userRegisterDto, Long id){
        var user = userDAO.findById(id).orElse(null);
        if(user == null){
            throw new UserNotFoundException("The user with " + id + " id number is not found!");
        }
        user.setFirstName(userRegisterDto.getFirstName());
        user.setLastName(userRegisterDto.getLastName());
        user.setEmail(userRegisterDto.getEmail());
        user.setPhone(userRegisterDto.getPhone());
        user.setBirthday(userRegisterDto.getBirthday());
        user = userDAO.save(user);

        var respUserDto = UserMapper.INSTANCE.convertUserToUserDto(user);
        return respUserDto;
    }

}
