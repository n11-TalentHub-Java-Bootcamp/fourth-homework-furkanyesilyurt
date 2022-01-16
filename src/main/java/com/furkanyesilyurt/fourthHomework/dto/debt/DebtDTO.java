package com.furkanyesilyurt.fourthHomework.dto.debt;

import lombok.Data;

import java.util.Date;

@Data
public class DebtDTO {

    private Long id;
    private Long userId;
    private Double mainDebt;
    private Double remainingDebt;
    private Date expiryDate;

}
