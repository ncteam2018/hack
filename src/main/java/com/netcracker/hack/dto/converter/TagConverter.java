package com.netcracker.hack.dto.converter;

import com.netcracker.hack.dto.TagDTO;
import com.netcracker.hack.mapper.TagMapper;
import com.netcracker.hack.model.Tag;

import java.util.ArrayList;
import java.util.List;

public class TagConverter {
    public static List<TagDTO> convertTo(List<Tag> tags) {

        List<TagDTO> tagDTOList = new ArrayList<>();

        for (Tag tag : tags)
            tagDTOList.add(TagMapper.INSTANCE.tagToTagDTO(tag));

        return tagDTOList;
    }

    public static List<Tag> convertFrom(List<TagDTO> tags) {

        List<Tag> tagList = new ArrayList<>();

        for (TagDTO tag : tags)
            tagList.add(TagMapper.INSTANCE.tagDTOToTag(tag));

        return tagList;
    }

    public static Tag[] convertFromM(TagDTO[] tags) {

        Tag[] tagList = new Tag[tags.length];

        int i=0;
        for (TagDTO tag : tags) {
            tagList[i] = TagMapper.INSTANCE.tagDTOToTag(tag);
            i++;
        }
        return tagList;
    }
}
