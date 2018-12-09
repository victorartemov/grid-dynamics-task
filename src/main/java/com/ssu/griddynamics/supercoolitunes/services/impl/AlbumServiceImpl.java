package com.ssu.griddynamics.supercoolitunes.services.impl;

import com.ssu.griddynamics.supercoolitunes.api.v1.mapper.AlbumMapper;
import com.ssu.griddynamics.supercoolitunes.api.v1.model.AlbumDTO;
import com.ssu.griddynamics.supercoolitunes.api.v1.model.TrackDTO;
import com.ssu.griddynamics.supercoolitunes.repositories.AlbumRepository;
import com.ssu.griddynamics.supercoolitunes.services.AlbumService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlbumServiceImpl implements AlbumService {

    private AlbumRepository albumRepository;
    private AlbumMapper albumMapper;

    public AlbumServiceImpl(AlbumRepository albumRepository, AlbumMapper albumMapper) {
        this.albumRepository = albumRepository;
        this.albumMapper = albumMapper;
    }

    @Override
    public List<AlbumDTO> findAll() {
        return albumRepository
                .findAll()
                .stream()
                .map(albumMapper::albumToAlbumDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AlbumDTO findById(Long id) {
        return null;
    }

    @Override
    public AlbumDTO findByTitle(String title) {
        return null;
    }

    @Override
    public AlbumDTO findByAuthorName(String name) {
        return null;
    }

    @Override
    public AlbumDTO findByTrack(TrackDTO track) {
        return null;
    }

    @Override
    public AlbumDTO createNewAlbum(AlbumDTO albumDTO) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void deleteAll() {

    }
}
