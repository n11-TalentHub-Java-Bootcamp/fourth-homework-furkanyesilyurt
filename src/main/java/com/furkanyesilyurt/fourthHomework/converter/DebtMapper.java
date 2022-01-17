package com.furkanyesilyurt.fourthHomework.converter;

import com.furkanyesilyurt.fourthHomework.dto.debt.DebtDTO;
import com.furkanyesilyurt.fourthHomework.dto.debt.DebtRegistrationDto;
import com.furkanyesilyurt.fourthHomework.entity.Debt;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DebtMapper {

    DebtMapper INSTANCE = Mappers.getMapper(DebtMapper.class);

    List<DebtDTO> convertAllDebtsToDebtDtos(List<Debt> debts);

    @Mapping(target = "userId", source = "user.id")
    DebtDTO convertDebtToDebtDto(Debt debt);

    @Mapping(source = "userId", target = "user.id")
    Debt convertDebtRegistrationDtoToDebt(DebtRegistrationDto debtRegistrationDto);

    @Mapping(source = "user.id", target = "userId")
    DebtRegistrationDto convertDebtToDebtRegistrationDto(Debt debt);

    @Mapping(source = "userId", target = "user.id")
    Debt convertDebtDTOToDebt(DebtDTO debtDTO);
}
