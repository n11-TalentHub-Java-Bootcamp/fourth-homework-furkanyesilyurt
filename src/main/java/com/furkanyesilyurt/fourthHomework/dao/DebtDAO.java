package com.furkanyesilyurt.fourthHomework.dao;

import com.furkanyesilyurt.fourthHomework.dto.debt.DebtDTO;
import com.furkanyesilyurt.fourthHomework.dto.debt.DebtExpiryDateDTO;
import com.furkanyesilyurt.fourthHomework.entity.Debt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DebtDAO extends JpaRepository<Debt, Long> {

    List<Debt> findByExpiryDateBetween(Date startDate, Date endDate);

    List<Debt> findDebtByUserId(Long userId);

}

