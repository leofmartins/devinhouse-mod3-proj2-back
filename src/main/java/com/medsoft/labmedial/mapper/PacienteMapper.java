package com.medsoft.labmedial.mapper;

import com.medsoft.labmedial.models.Paciente;
import com.medsoft.labmedial.records.request.PacienteRequest;
import com.medsoft.labmedial.records.response.PacienteResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PacienteMapper {
    PacienteMapper INSTANCE = Mappers.getMapper(PacienteMapper.class);
    Paciente requestToPaciente(PacienteRequest request);
    PacienteResponse pacienteToResponse(Paciente paciente);
}
