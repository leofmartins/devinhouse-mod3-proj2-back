package com.medsoft.labmedial.mapper;

import com.medsoft.labmedial.dtos.request.MedicamentoRequest;
import com.medsoft.labmedial.dtos.response.MedicamentoResponse;
import com.medsoft.labmedial.models.Medicamento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface MedicamentoMapper {
    MedicamentoMapper INSTANCE = Mappers.getMapper(MedicamentoMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "idPaciente", target = "paciente.id")
    @Mapping(target = "situacao", ignore = true)
    Medicamento requestToMedicamento(MedicamentoRequest request);

    MedicamentoResponse medicamentoToMedicamentoResponse(Medicamento medicamento);

    default Medicamento optionalMedicamentoToMedicamento(Optional<Medicamento> optionalMedicamento) {
        return optionalMedicamento.orElse(null);
    }

}
