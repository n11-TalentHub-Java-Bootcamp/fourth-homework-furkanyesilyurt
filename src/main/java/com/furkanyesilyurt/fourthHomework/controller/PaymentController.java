package com.furkanyesilyurt.fourthHomework.controller;

import com.furkanyesilyurt.fourthHomework.dto.debt.DebtDTO;
import com.furkanyesilyurt.fourthHomework.dto.payment.PaymentDTO;
import com.furkanyesilyurt.fourthHomework.dto.payment.PaymentRecordDTO;
import com.furkanyesilyurt.fourthHomework.service.entityService.PaymentEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentEntityService paymentEntityService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<PaymentDTO>> findAll(){
        var paymentDtos = paymentEntityService.findAll();
        return new ResponseEntity<>(paymentDtos, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<PaymentRecordDTO> savePayment(@RequestBody PaymentRecordDTO paymentRecordDTO){
        var paymenDto = paymentEntityService.save(paymentRecordDTO);
        return new ResponseEntity<>(paymenDto, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/expirydate", method = RequestMethod.GET)
    public ResponseEntity<List<PaymentDTO>> findByPaymentDateBetween(@RequestParam String startDate, @RequestParam String endDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDated = sdf.parse(startDate);
        Date endDated = sdf.parse(endDate);
        var paymentDTOS = paymentEntityService.findByPaymentDateBetween(startDated, endDated);
        return new ResponseEntity<>(paymentDTOS, HttpStatus.OK);
    }

    @RequestMapping(value = "/userId", method = RequestMethod.GET)
    public ResponseEntity<List<PaymentDTO>> findPaymentByUserId(@RequestParam Long userId){
        var paymentDtos = paymentEntityService.findPaymentByUserId(userId);
        return new ResponseEntity<>(paymentDtos, HttpStatus.OK);
    }

}
