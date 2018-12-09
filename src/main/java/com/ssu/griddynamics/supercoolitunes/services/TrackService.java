package com.ssu.griddynamics.supercoolitunes.services;

import com.ssu.griddynamics.supercoolitunes.api.v1.model.AlbumDTO;
import com.ssu.griddynamics.supercoolitunes.api.v1.model.AuthorDTO;
import com.ssu.griddynamics.supercoolitunes.api.v1.model.TrackDTO;

import java.util.List;

public interface TrackService {

    List<TrackDTO> findAll();

    TrackDTO findByTitle(String title);

    TrackDTO findByAuthor(String authorName);

    TrackDTO findByAlbum(String albumTitle);

    TrackDTO createNewTrack(TrackDTO trackDTO);

    void deleteTrackById(Long id);

    void deleteAll();
}
