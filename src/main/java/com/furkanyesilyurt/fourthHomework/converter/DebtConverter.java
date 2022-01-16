package com.furkanyesilyurt.fourthHomework.converter;

import com.furkanyesilyurt.fourthHomework.dto.debt.DebtDTO;
import com.furkanyesilyurt.fourthHomework.dto.debt.DebtRegistrationDto;
import com.furkanyesilyurt.fourthHomework.entity.Debt;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DebtConverter {

    DebtConverter INSTANCE = Mappers.getMapper(DebtConverter.class);

    List<DebtDTO> convertAllDebtsToDebtDtos(List<Debt> debts);

    Debt convertDebtRegistrationDtoToDebt(DebtRegistrationDto debtRegistrationDto);
    DebtRegistrationDto convertDebtToDebtRegistrationDto(Debt debt);
}
