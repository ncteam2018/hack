package com.netcracker.hack.mapper;

import com.netcracker.hack.dto.HackDTO;
import com.netcracker.hack.model.Hack;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface HackMapper {
    HackMapper INSTANCE = Mappers.getMapper(HackMapper.class);

    @Mappings({
            @Mapping(target="title", source="name"),
            @Mapping(target="duration", source="countOfDays")
    })
    HackDTO hackToHackDTO (Hack entity);


    @Mappings({
            @Mapping(target="name", source="title"),
            @Mapping(target="countOfDays", source="duration")
    })
    Hack hackDTOToHack (HackDTO dto);
}
