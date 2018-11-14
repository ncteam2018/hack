package com.netcracker.hack.mapper;

import com.netcracker.hack.dto.UserDTO;
import com.netcracker.hack.model.Profile;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mappings({
            @Mapping(target = "password", constant = ""),
            @Mapping(target = "firstName", source = "userData.fName"),
            @Mapping(target = "firstName", source = "userData.sName"),
            @Mapping(target = "firstName", source = "userData.lName")

    })
    UserDTO userToUserDTO(Profile entity);
}
