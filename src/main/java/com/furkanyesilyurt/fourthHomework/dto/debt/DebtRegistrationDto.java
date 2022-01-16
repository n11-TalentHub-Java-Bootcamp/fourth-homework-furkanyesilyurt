package com.furkanyesilyurt.fourthHomework.dto.debt;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.furkanyesilyurt.fourthHomework.entity.User;
import lombok.Data;

import java.util.Date;

@Data
public class DebtRegistrationDto {

//    private User user;
    private Double mainDebt;
    private Double remainingDebt;
    private Date expiryDate;

}
