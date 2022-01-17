package com.furkanyesilyurt.fourthHomework.dao;

import com.furkanyesilyurt.fourthHomework.entity.Debt;
import com.furkanyesilyurt.fourthHomework.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PaymentDAO extends JpaRepository<Payment, Long> {

    List<Payment> findByPaymentDateBetween(Date startDate, Date endDate);

    List<Payment> findPaymentByUserId(Long userId);

}
