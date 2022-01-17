package com.furkanyesilyurt.fourthHomework.dto.payment;

import lombok.Data;

import java.util.Date;

@Data
public class PaymentDTO {

    private Long id;
    private Long debtId;
    private Date paymentDate;
    private Long userId;

}
