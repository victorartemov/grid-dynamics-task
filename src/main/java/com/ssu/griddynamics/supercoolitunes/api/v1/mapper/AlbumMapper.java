package com.ssu.griddynamics.supercoolitunes.api.v1.mapper;

import com.ssu.griddynamics.supercoolitunes.api.v1.model.AlbumDTO;
import com.ssu.griddynamics.supercoolitunes.domain.Album;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.time.Year;

@Mapper
public interface AlbumMapper {
    AlbumMapper INSTANCE = Mappers.getMapper(AlbumMapper.class);

    AlbumDTO albumToAlbumDTO(Album album);

    Album albumDTOtoAlbum(AlbumDTO albumDTO);

    default String map(Year year) {
        return year.toString();
    }

    default Year map(String string) {
        return Year.parse(string);
    }
}
