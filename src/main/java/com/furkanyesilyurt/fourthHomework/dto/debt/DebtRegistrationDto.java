package com.furkanyesilyurt.fourthHomework.dto.debt;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.furkanyesilyurt.fourthHomework.entity.User;
import com.furkanyesilyurt.fourthHomework.enums.DebtType;
import lombok.Data;

import java.util.Date;

@Data
public class DebtRegistrationDto {

    private Long userId;
    private Double mainDebt;
    private Double remainingDebt;
    private Date expiryDate;
    private DebtType debtType;

}
