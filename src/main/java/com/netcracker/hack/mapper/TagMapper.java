package com.netcracker.hack.mapper;

import com.netcracker.hack.dto.TagDTO;
import com.netcracker.hack.model.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TagMapper {
    TagMapper INSTANCE = Mappers.getMapper(TagMapper.class);
    @Mappings({
            @Mapping(target="tagName", source="tag")
    })
    TagDTO tagToTagDTO(Tag entity);

    @Mappings({
            @Mapping(target="tag", source="tagName")
    })
    Tag tagDTOToTag(TagDTO dto);
}
