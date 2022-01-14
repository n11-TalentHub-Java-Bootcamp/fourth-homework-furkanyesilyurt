package com.furkanyesilyurt.fourthHomework.dto.user;

import lombok.Data;

import java.util.Date;

@Data
public class UserDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Date birthday;

}
