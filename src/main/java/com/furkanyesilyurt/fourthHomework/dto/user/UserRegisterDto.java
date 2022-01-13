package com.furkanyesilyurt.fourthHomework.dto.user;

import lombok.Data;

import java.util.Date;

@Data
public class UserRegisterDto {

    private String firstName;
    private String lastName;
    private Date birthday;

}
