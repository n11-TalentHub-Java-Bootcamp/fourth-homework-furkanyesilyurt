package com.furkanyesilyurt.fourthHomework.dto.payment;

import lombok.Data;

import java.util.Date;

@Data
public class PaymentRecordDTO {

    private Long debtId;
    private Date paymentDate;
    private Long userId;

}
