package com.furkanyesilyurt.fourthHomework.dto.debt;

import com.furkanyesilyurt.fourthHomework.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class DebtExpiryDateDTO {

    private Long id;
    private User user;
    private Double mainDebt;
    private Double remainingDebt;
    private Date expiryDate;
    private Long debt;
    private Double delayDebt;

}
