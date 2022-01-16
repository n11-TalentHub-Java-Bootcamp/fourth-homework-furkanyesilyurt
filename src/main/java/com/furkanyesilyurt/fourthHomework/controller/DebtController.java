package com.furkanyesilyurt.fourthHomework.controller;

import com.furkanyesilyurt.fourthHomework.converter.DebtConverter;
import com.furkanyesilyurt.fourthHomework.dto.debt.DebtDTO;
import com.furkanyesilyurt.fourthHomework.dto.debt.DebtExpiryDateDTO;
import com.furkanyesilyurt.fourthHomework.dto.debt.DebtRegistrationDto;
import com.furkanyesilyurt.fourthHomework.entity.Debt;
import com.furkanyesilyurt.fourthHomework.service.entityService.DebtEntityService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<DebtRegistrationDto> saveDebt(@RequestBody DebtRegistrationDto debtRegistrationDto){
        System.out.println(debtRegistrationDto);
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

}
