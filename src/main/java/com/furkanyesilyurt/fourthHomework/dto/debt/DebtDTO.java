package com.furkanyesilyurt.fourthHomework.dto.debt;

import com.furkanyesilyurt.fourthHomework.enums.DebtType;
import lombok.Data;

import java.util.Date;

@Data
public class DebtDTO {

    private Long debt_id;
    private Long userId;
    private Double mainDebt;
    private Double remainingDebt;
    private Date expiryDate;
    private DebtType debtType;
    private Long debt;
    private Long delayDebt;

}
