package com.furkanyesilyurt.fourthHomework.service.entityService;

import com.furkanyesilyurt.fourthHomework.converter.DebtMapper;
import com.furkanyesilyurt.fourthHomework.dao.DebtDAO;
import com.furkanyesilyurt.fourthHomework.dto.debt.DebtDTO;
import com.furkanyesilyurt.fourthHomework.dto.debt.DebtRegistrationDto;
import com.furkanyesilyurt.fourthHomework.entity.Debt;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.Temporal;
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

    public List<DebtDTO> findDelayDebtByUserId(Long userId){
        List<Debt> debts = debtDAO.findDebtByUserId(userId);
        List<DebtDTO> debtDTOS = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date());
        LocalDate dated = LocalDate.parse(date);
        Date convertedDate = java.sql.Date.valueOf(dated);

        for(Debt debt : debts){
            if(debt.getRemainingDebt() != 0 && debt.getExpiryDate().before(convertedDate)) {
                debtDTOS.add(DebtMapper.INSTANCE.convertDebtToDebtDto(debt));
            }
        }

        return debtDTOS;
    }

    public Double findTotalDebtByUserId(Long userId){
        List<Debt> debts = debtDAO.findDebtByUserId(userId);
        Double totaldebt = 0.0;
        for(Debt debt : debts){
            if(debt.getRemainingDebt() != 0) {
                totaldebt += debt.getRemainingDebt();
            }
        }
        return totaldebt;
    }

    public Double findDelayTotalDebtByUserId(Long userId){
        List<Debt> debts = debtDAO.findDebtByUserId(userId);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date());
        LocalDate dated = LocalDate.parse(date);
        Date convertedDate = java.sql.Date.valueOf(dated);

        Double totaldebt = 0.0;
        for(Debt debt : debts){
            if(debt.getRemainingDebt() != 0 && debt.getExpiryDate().before(convertedDate)) {
                totaldebt += debt.getRemainingDebt();
            }
        }
        return totaldebt;
    }

    public Double findDelayRaiseByUserId(Long userId){
        List<Debt> debts = debtDAO.findDebtByUserId(userId);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date());
        LocalDate dated = LocalDate.parse(date);
        Date convertedDate = java.sql.Date.valueOf(dated);

        Double totaldelaydebt = 0.0;
        for(Debt debt : debts){
            if(debt.getRemainingDebt() != 0 && debt.getExpiryDate().before(convertedDate)) {
                totaldelaydebt += findDelayRaise(debt.getExpiryDate(),convertedDate, debt.getMainDebt());
            }
        }
        totaldelaydebt = Double.valueOf(Math.round(totaldelaydebt));
        return totaldelaydebt;

    }

    public Double findDelayRaise(Date expiryDate, Date now, Double mainDebt ){
        long difference  = now.getTime() - expiryDate.getTime();
        long daysBetween = (difference / (1000*60*60*24));
        Date myDate = parseDate("2018-01-01");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(myDate);
        LocalDate dated = LocalDate.parse(date);
        Date expiryLimit = java.sql.Date.valueOf(dated);
        if(expiryDate.before(expiryLimit)){
            return (mainDebt*2.0/100*daysBetween);
        } else {
            return (mainDebt*1.5/100*daysBetween);
        }
    }

    public static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (Exception e) {
            return null;
        }
    }

}
