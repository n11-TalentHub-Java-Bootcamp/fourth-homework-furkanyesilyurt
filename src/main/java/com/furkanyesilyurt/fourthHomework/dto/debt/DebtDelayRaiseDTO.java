package com.furkanyesilyurt.fourthHomework.dto.debt;

import com.furkanyesilyurt.fourthHomework.enums.DebtType;
import lombok.Data;

import java.util.Date;

@Data
public class DebtDelayRaiseDTO {

    private Long userId;
    private Double mainDebt;
    private Double remainingDebt;
    private Date expiryDate;
    private DebtType debtType = DebtType.DELAY_RAISE;
    private Long debt;
    private Long delayDebt;

}
