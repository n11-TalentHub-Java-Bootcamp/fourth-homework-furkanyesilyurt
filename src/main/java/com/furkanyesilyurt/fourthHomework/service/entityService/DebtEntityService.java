package com.furkanyesilyurt.fourthHomework.service.entityService;

import com.furkanyesilyurt.fourthHomework.converter.DebtMapper;
import com.furkanyesilyurt.fourthHomework.dao.DebtDAO;
import com.furkanyesilyurt.fourthHomework.dto.debt.DebtDTO;
import com.furkanyesilyurt.fourthHomework.dto.debt.DebtRegistrationDto;
import com.furkanyesilyurt.fourthHomework.entity.Debt;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DebtEntityService {

    private final DebtDAO debtDAO;

    public List<DebtDTO> findAll(){
        List<Debt> debts = debtDAO.findAll();
        List<DebtDTO> debtDTOS = new ArrayList<>();
        for(Debt debt : debts){
            debtDTOS.add(DebtMapper.INSTANCE.convertDebtToDebtDto(debt));
        }
        return debtDTOS;
    }

    public DebtRegistrationDto saveDebt(DebtRegistrationDto debtRegistrationDto){
        Debt debt = DebtMapper.INSTANCE.convertDebtRegistrationDtoToDebt(debtRegistrationDto);
        debt = debtDAO.save(debt);
        return DebtMapper.INSTANCE.convertDebtToDebtRegistrationDto(debt);
    }

    public List<DebtDTO> findByExpiryDateBetween(Date startDate, Date endDate){
        List<Debt> debts = debtDAO.findByExpiryDateBetween(startDate,endDate);
        List<DebtDTO> debtDTOS = new ArrayList<>();
        for(Debt debt : debts){
            debtDTOS.add(DebtMapper.INSTANCE.convertDebtToDebtDto(debt));
        }
        return debtDTOS;
    }

    public List<DebtDTO> findDebtByUserId(Long userId){
        List<Debt> debts = debtDAO.findDebtByUserId(userId);
        List<DebtDTO> debtDTOS = new ArrayList<>();
        for(Debt debt : debts){
            if(debt.getRemainingDebt() != 0) {
                debtDTOS.add(DebtMapper.INSTANCE.convertDebtToDebtDto(debt));
            }
        }
        return debtDTOS;
    }

}
