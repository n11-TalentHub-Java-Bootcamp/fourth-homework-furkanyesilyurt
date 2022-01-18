package com.furkanyesilyurt.fourthHomework.dto.debt;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.furkanyesilyurt.fourthHomework.enums.DebtType;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties("debtType")
public class DebtRegistrationDTO {

    private Long userId;
    private Double mainDebt;
    private Double remainingDebt;
    private Date expiryDate;
    private DebtType debtType = DebtType.NORMAL;
    private Long debt;
//    private Double delayDebt;

}
