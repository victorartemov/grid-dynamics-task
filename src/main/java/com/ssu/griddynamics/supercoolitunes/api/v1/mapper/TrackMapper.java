package com.ssu.griddynamics.supercoolitunes.api.v1.mapper;

import com.ssu.griddynamics.supercoolitunes.api.v1.model.TrackDTO;
import com.ssu.griddynamics.supercoolitunes.domain.Track;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.time.Year;

@Mapper
public interface TrackMapper {

    TrackMapper INSTANCE = Mappers.getMapper(TrackMapper.class);

    TrackDTO trackToTrackDTO(Track track);

    Track trackDTOtoTrack(TrackDTO trackDTO);

    default String map(Year year) {
        return year.toString();
    }

    default Year map(String string) {
        return Year.parse(string);
    }
}
