package com.furkanyesilyurt.fourthHomework.service.entityService;

import com.furkanyesilyurt.fourthHomework.converter.DebtConverter;
import com.furkanyesilyurt.fourthHomework.dao.DebtDAO;
import com.furkanyesilyurt.fourthHomework.dto.debt.DebtDTO;
import com.furkanyesilyurt.fourthHomework.dto.debt.DebtExpiryDateDTO;
import com.furkanyesilyurt.fourthHomework.dto.debt.DebtRegistrationDto;
import com.furkanyesilyurt.fourthHomework.entity.Debt;
import com.furkanyesilyurt.fourthHomework.exception.debtException.DebtNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DebtEntityService {

    private final DebtDAO debtDAO;

    public List<DebtDTO> findAll(){
        List<Debt> debts = debtDAO.findAll();
        if(debts.isEmpty()){
            throw new DebtNotFoundException("No debts found!");
        }
        return DebtConverter.INSTANCE.convertAllDebtsToDebtDtos(debts);
    }

    public DebtRegistrationDto saveDebt(DebtRegistrationDto debtRegistrationDto){
        System.out.println("Entity "+debtRegistrationDto);
        var debt = DebtConverter.INSTANCE.convertDebtRegistrationDtoToDebt(debtRegistrationDto);
        debt = debtDAO.save(debt);
        var respdebtRegistrationDto = DebtConverter.INSTANCE.convertDebtToDebtRegistrationDto(debt);
        return respdebtRegistrationDto;
    }

    public List<DebtDTO> findByExpiryDateBetween(Date startDate, Date endDate){
        List<Debt> debts = debtDAO.findByExpiryDateBetween(startDate,endDate);
        return DebtConverter.INSTANCE.convertAllDebtsToDebtDtos(debts);
    }

}
