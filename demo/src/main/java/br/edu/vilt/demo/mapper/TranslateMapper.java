package br.edu.vilt.demo.mapper;

import br.edu.vilt.demo.dto.EngRespDto;
import br.edu.vilt.demo.dto.ResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TranslateMapper {
    @Mapping(target = "zipCode", expression = "java(responseDto.getCep())")
    @Mapping(target = "address", expression = "java(responseDto.getLogradouro())")
    @Mapping(target = "complement", expression = "java(responseDto.getComplemento())")
    @Mapping(target = "unit", expression = "java(responseDto.getUf())")
    @Mapping(target = "neighborhood", expression = "java(responseDto.getBairro())")
    @Mapping(target = "city", expression = "java(responseDto.getLocalidade())")
    @Mapping(target = "state", expression = "java(responseDto.getEstado())")
    @Mapping(target = "region", expression = "java(responseDto.getRegiao())")
    EngRespDto toEnglish(ResponseDto responseDto);
}