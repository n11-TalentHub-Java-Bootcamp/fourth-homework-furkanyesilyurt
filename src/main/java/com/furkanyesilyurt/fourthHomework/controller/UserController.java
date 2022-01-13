package com.furkanyesilyurt.fourthHomework.controller;

import com.furkanyesilyurt.fourthHomework.dto.user.UserDTO;
import com.furkanyesilyurt.fourthHomework.dto.user.UserRegisterDto;
import com.furkanyesilyurt.fourthHomework.service.entityService.UserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserEntityService userEntityService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> findAll(){
        var userDtos = userEntityService.findAll();
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/id", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> findById(@PathVariable Long id){
        var userDto = userEntityService.findById(id);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/{firstName}/firstName", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> findByFirstname(@PathVariable String firstName){
        var userDto = userEntityService.findByFirstname(firstName);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserRegisterDto userRegisterDto){
        var userDto = userEntityService.save(userRegisterDto);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}/id", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable Long id){
        userEntityService.deleteById(id);
    }
}
