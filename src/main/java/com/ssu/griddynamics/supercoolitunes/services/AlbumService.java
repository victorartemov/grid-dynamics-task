package com.ssu.griddynamics.supercoolitunes.services;

import com.ssu.griddynamics.supercoolitunes.api.v1.model.AlbumDTO;
import com.ssu.griddynamics.supercoolitunes.api.v1.model.TrackDTO;

import java.util.List;

public interface AlbumService {

    List<AlbumDTO> findAll();

    AlbumDTO findById(Long id);

    AlbumDTO findByTitle(String title);

    AlbumDTO findByAuthorName(String name);

    AlbumDTO findByTrack(TrackDTO track);

    AlbumDTO createNewAlbum(AlbumDTO albumDTO);

    void deleteById(Long id);

    void deleteAll();
}
