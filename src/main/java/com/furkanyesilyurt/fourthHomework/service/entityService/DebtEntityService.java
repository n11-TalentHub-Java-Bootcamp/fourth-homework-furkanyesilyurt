package com.furkanyesilyurt.fourthHomework.service.entityService;

import com.furkanyesilyurt.fourthHomework.converter.DebtMapper;
import com.furkanyesilyurt.fourthHomework.dao.DebtDAO;
import com.furkanyesilyurt.fourthHomework.dto.debt.DebtDTO;
import com.furkanyesilyurt.fourthHomework.dto.debt.DebtDelayRaiseDTO;
import com.furkanyesilyurt.fourthHomework.dto.debt.DebtRegistrationDTO;
import com.furkanyesilyurt.fourthHomework.entity.Debt;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class DebtEntityService {

    private final DebtDAO debtDAO;
    private final DebtEntityService debtEntityService;

    @Transactional
    public List<DebtDTO> findAll(){
        List<Debt> debts = debtDAO.findAll();
        List<DebtDTO> debtDTOS = new ArrayList<>();
        Double delayDebt = 0.0;
        for(Debt debt : debts){
//            delayDebt = debtEntityService.findDelayRaise(debt.getExpiryDate(),)
            debtDTOS.add(DebtMapper.INSTANCE.convertDebtToDebtDto(debt));
        }
        return debtDTOS;
    }

    @Transactional
    public DebtDTO findById(Long id){
        Optional<Debt> optionalDebt = debtDAO.findById(id);
        Debt debt = null;
        if(optionalDebt.isPresent()){
            debt = optionalDebt.get();
        }
        return DebtMapper.INSTANCE.convertDebtToDebtDto(debt);
    }

    @Transactional
    public DebtRegistrationDTO saveDebt(DebtRegistrationDTO debtRegistrationDto){
        Debt debt = DebtMapper.INSTANCE.convertDebtRegistrationDtoToDebt(debtRegistrationDto);
        debt = debtDAO.save(debt);
        return DebtMapper.INSTANCE.convertDebtToDebtRegistrationDto(debt);
    }

    @Transactional
    public DebtDelayRaiseDTO saveDelayRaise(DebtDelayRaiseDTO debtDelayRaiseDTO){
        Debt debt = DebtMapper.INSTANCE.convertDebtDelayRaiseDTOToDebt(debtDelayRaiseDTO);
        debt = debtDAO.save(debt);
        return DebtMapper.INSTANCE.convertDebtToDebtDelayRaiseDto(debt);
    }

    @Transactional
    public List<DebtDTO> findByExpiryDateBetween(Date startDate, Date endDate){
        List<Debt> debts = debtDAO.findByExpiryDateBetween(startDate,endDate);
        List<DebtDTO> debtDTOS = new ArrayList<>();
        for(Debt debt : debts){
            debtDTOS.add(DebtMapper.INSTANCE.convertDebtToDebtDto(debt));
        }
        return debtDTOS;
    }

    @Transactional
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

    @Transactional
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

    @Transactional
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

    @Transactional
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

    @Transactional
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

    @Transactional
    public Double findDelayRaiseByDebtId(Long id){
        Optional<Debt> optionalDebt = debtDAO.findById(id);
        Debt debt = null;
        if(optionalDebt.isPresent()){
            debt = optionalDebt.get();
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date());
        LocalDate dated = LocalDate.parse(date);
        Date convertedDate = java.sql.Date.valueOf(dated);

        Double totaldelaydebt = 0.0;
        if(debt.getRemainingDebt() != 0 && debt.getExpiryDate().before(convertedDate)) {
            totaldelaydebt = findDelayRaise(debt.getExpiryDate(),convertedDate, debt.getMainDebt());
        }
        totaldelaydebt = Double.valueOf(Math.round(totaldelaydebt));

        return totaldelaydebt;
    }

    @Transactional
    public void updateDebtInfo(Long id, Double remainingDebt){
        debtDAO.updateDebtInfo(id, remainingDebt);
    }

    public Date convertNowDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date());
        LocalDate dated = LocalDate.parse(date);
        Date convertedDate = java.sql.Date.valueOf(dated);
        return convertedDate;
    }

}
