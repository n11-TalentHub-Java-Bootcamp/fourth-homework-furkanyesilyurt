package com.furkanyesilyurt.fourthHomework.converter;

import com.furkanyesilyurt.fourthHomework.dto.payment.PaymentDTO;
import com.furkanyesilyurt.fourthHomework.dto.payment.PaymentRecordDTO;
import com.furkanyesilyurt.fourthHomework.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PaymentMapper {

    PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);

    @Mapping(source = "debt.debt_id", target = "debtId")
    PaymentDTO convertPaymentToPaymentDTO(Payment payment);

    @Mapping(source = "debtId", target = "debt.debt_id")
    Payment convertPaymentRecordDTOToPayment(PaymentRecordDTO paymentRecordDTO);

    @Mapping(source = "debt.debt_id", target = "debtId")
    PaymentRecordDTO convertPaymentToPaymentRecordDto(Payment payment);

}
