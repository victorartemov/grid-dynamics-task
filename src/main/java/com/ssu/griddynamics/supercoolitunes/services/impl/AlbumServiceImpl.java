package com.ssu.griddynamics.supercoolitunes.services.impl;

import com.ssu.griddynamics.supercoolitunes.api.v1.mapper.AlbumMapper;
import com.ssu.griddynamics.supercoolitunes.api.v1.model.AlbumDTO;
import com.ssu.griddynamics.supercoolitunes.api.v1.model.TrackDTO;
import com.ssu.griddynamics.supercoolitunes.domain.Album;
import com.ssu.griddynamics.supercoolitunes.exception.ResourceNotFoundException;
import com.ssu.griddynamics.supercoolitunes.repositories.AlbumRepository;
import com.ssu.griddynamics.supercoolitunes.services.AlbumService;
import com.ssu.griddynamics.supercoolitunes.services.AuthorService;
import com.ssu.griddynamics.supercoolitunes.services.TrackService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;
    private final AlbumMapper albumMapper;
    private final TrackService trackService;
    private final AuthorService authorService;

    public AlbumServiceImpl(AlbumRepository albumRepository, AlbumMapper albumMapper,
                            TrackService trackService, AuthorService authorService) {
        this.albumRepository = albumRepository;
        this.albumMapper = albumMapper;
        this.trackService = trackService;
        this.authorService = authorService;
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
        return albumRepository
                .findById(id)
                .map(albumMapper::albumToAlbumDTO)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public AlbumDTO findByTitle(String title) {
        return albumRepository
                .findByTitle(title)
                .map(albumMapper::albumToAlbumDTO)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public AlbumDTO findByAuthorName(String name) {
        return albumRepository
                .findByAuthorName(name)
                .map(albumMapper::albumToAlbumDTO)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public AlbumDTO findByTrack(TrackDTO track) {
        return null;
    }

    @Override
    public AlbumDTO createNewAlbum(AlbumDTO albumDTO) {

        Album savedAlbum = albumRepository.save(albumMapper.albumDTOtoAlbum(albumDTO));

        return albumMapper.albumToAlbumDTO(savedAlbum);
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void deleteAll() {

    }
}
