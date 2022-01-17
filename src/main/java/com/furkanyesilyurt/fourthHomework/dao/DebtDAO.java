package com.furkanyesilyurt.fourthHomework.dao;

import com.furkanyesilyurt.fourthHomework.entity.Debt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public interface DebtDAO extends JpaRepository<Debt, Long> {

    List<Debt> findByExpiryDateBetween(Date startDate, Date endDate);

    List<Debt> findDebtByUserId(Long userId);

    @Modifying
    @Transactional
    @Query("UPDATE Debt d SET d.remainingDebt = :remainingDebt WHERE d.debt_id = :id")
    void updateDebtInfo(@Param("id") Long id, @Param("remainingDebt") Double remainingDebt);

}

