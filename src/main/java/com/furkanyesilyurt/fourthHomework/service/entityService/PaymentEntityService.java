package com.furkanyesilyurt.fourthHomework.service.entityService;

import com.furkanyesilyurt.fourthHomework.converter.DebtMapper;
import com.furkanyesilyurt.fourthHomework.converter.PaymentMapper;
import com.furkanyesilyurt.fourthHomework.dao.PaymentDAO;
import com.furkanyesilyurt.fourthHomework.dto.debt.DebtDTO;
import com.furkanyesilyurt.fourthHomework.dto.debt.DebtDelayRaiseDTO;
import com.furkanyesilyurt.fourthHomework.dto.payment.PaymentDTO;
import com.furkanyesilyurt.fourthHomework.dto.payment.PaymentRecordDTO;
import com.furkanyesilyurt.fourthHomework.entity.Debt;
import com.furkanyesilyurt.fourthHomework.entity.Payment;
import com.furkanyesilyurt.fourthHomework.enums.DebtType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentEntityService {

    private final PaymentDAO paymentDAO;
    private final DebtEntityService debtEntityService;

    @Transactional
    public List<PaymentDTO> findAll() {
        List<Payment> payments = paymentDAO.findAll();
        List<PaymentDTO> paymentDTOS = new ArrayList<>();
        for (Payment payment : payments) {
            paymentDTOS.add(PaymentMapper.INSTANCE.convertPaymentToPaymentDTO(payment));
        }
        return paymentDTOS;
    }

    @Transactional
    public PaymentRecordDTO save(PaymentRecordDTO paymentRecordDTO) {

        //tahsilat yapılması istenen debt id
        DebtDTO debtDTO = debtEntityService.findById(paymentRecordDTO.getDebtId());

        //ilgili borcun gecikme zammı
        Double delayDebt = debtEntityService.findDelayRaiseByDebtId(debtDTO.getDebt_id());

        //gecikme zammını save et
        Debt debt = DebtMapper.INSTANCE.convertDebtDTOToDebt(debtDTO);
        debt.setMainDebt(delayDebt);
        debt.setRemainingDebt(0.0);
        debt.setDebtType(DebtType.DELAY_RAISE);
        debt.setDebt(paymentRecordDTO.getDebtId());
        DebtDelayRaiseDTO debtDelayRaiseDTO = DebtMapper.INSTANCE.convertDebtToDebtDelayRaiseDto(debt);
        debtDelayRaiseDTO = debtEntityService.saveDelayRaise(debtDelayRaiseDTO);

        //TODO remaingdebt sıfırla(update)
        debtEntityService.updateDebtInfo(paymentRecordDTO.getDebtId(), 0.0);

        //tahsilatı kaydet
        Payment payment = PaymentMapper.INSTANCE.convertPaymentRecordDTOToPayment(paymentRecordDTO);
        payment = paymentDAO.save(payment);
        return PaymentMapper.INSTANCE.convertPaymentToPaymentRecordDto(payment);
    }

    @Transactional
    public List<PaymentDTO> findByPaymentDateBetween(Date startDate, Date endDate) {
        List<Payment> payments = paymentDAO.findByPaymentDateBetween(startDate, endDate);
        List<PaymentDTO> paymentDTOS = new ArrayList<>();
        for(Payment payment : payments){
            paymentDTOS.add(PaymentMapper.INSTANCE.convertPaymentToPaymentDTO(payment));
        }
        return paymentDTOS;
    }

    @Transactional
    public List<PaymentDTO> findPaymentByUserId(Long userId){
        List<Payment> payments = paymentDAO.findPaymentByUserId(userId);
        List<PaymentDTO> paymentDTOS = new ArrayList<>();
        for (Payment payment : payments) {
            paymentDTOS.add(PaymentMapper.INSTANCE.convertPaymentToPaymentDTO(payment));
        }
        return paymentDTOS;
    }

}
