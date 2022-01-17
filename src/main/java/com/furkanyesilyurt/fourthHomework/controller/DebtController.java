package com.furkanyesilyurt.fourthHomework.controller;

import com.furkanyesilyurt.fourthHomework.dto.debt.DebtDTO;
import com.furkanyesilyurt.fourthHomework.dto.debt.DebtRegistrationDTO;
import com.furkanyesilyurt.fourthHomework.service.entityService.DebtEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/debts")
@RequiredArgsConstructor
public class DebtController {

    private final DebtEntityService debtEntityService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<DebtDTO>> findAll(){
        var debtDtos = debtEntityService.findAll();
        return new ResponseEntity<>(debtDtos, HttpStatus.OK);
    }

    @RequestMapping(value = "/debtId", method = RequestMethod.GET)
    public ResponseEntity<DebtDTO> findById(@RequestParam Long id){
        var debtDto = debtEntityService.findById(id);
        return new ResponseEntity<>(debtDto, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<DebtRegistrationDTO> saveDebt(@RequestBody DebtRegistrationDTO debtRegistrationDto){
        var respdebtRegistrationDto = debtEntityService.saveDebt(debtRegistrationDto);
        return new ResponseEntity<>(respdebtRegistrationDto, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/expirydate", method = RequestMethod.GET)
    public ResponseEntity<List<DebtDTO>> findByExpiryDateBetween(@RequestParam String startDate, @RequestParam String endDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDated = sdf.parse(startDate);
        Date endDated = sdf.parse(endDate);
        var debtDtos = debtEntityService.findByExpiryDateBetween(startDated, endDated);
        return new ResponseEntity<>(debtDtos, HttpStatus.OK);
    }

    @RequestMapping(value = "/userId", method = RequestMethod.GET)
    public ResponseEntity<List<DebtDTO>> findDebtByUserId(@RequestParam Long userId){
        var debtDtos = debtEntityService.findDebtByUserId(userId);
        return new ResponseEntity<>(debtDtos, HttpStatus.OK);
    }

    @RequestMapping(value = "/userId/expirydate", method = RequestMethod.GET)
    public ResponseEntity<List<DebtDTO>> findDelayDebtByUserId(@RequestParam Long userId) {
        var debDtos = debtEntityService.findDelayDebtByUserId(userId);
        return new ResponseEntity<>(debDtos, HttpStatus.OK);
    }

    @RequestMapping(value = "/totalDebt",method = RequestMethod.GET)
    public ResponseEntity<Double> findTotalDebtByUserId(@RequestParam Long userId){
        var totalDebt = debtEntityService.findTotalDebtByUserId(userId);
        return new ResponseEntity<>(totalDebt, HttpStatus.OK);
    }

    @RequestMapping(value = "/totalDebt/expirtdate", method = RequestMethod.GET)
    public ResponseEntity<Double> findDelayTotalDebtByUserId(@RequestParam Long userId){
        var totalDebt = debtEntityService.findDelayTotalDebtByUserId(userId);
        return new ResponseEntity<>(totalDebt, HttpStatus.OK);
    }

    @RequestMapping(value = "/delaydebt", method = RequestMethod.GET)
    public ResponseEntity<Double> findDelayRaiseByUserId(@RequestParam Long userId){
        var totalDebt = debtEntityService.findDelayRaiseByUserId(userId);
        return new ResponseEntity<>(totalDebt, HttpStatus.OK);
    }

}
