package com.ssu.griddynamics.supercoolitunes.services.impl;

import com.ssu.griddynamics.supercoolitunes.api.v1.mapper.AuthorMapper;
import com.ssu.griddynamics.supercoolitunes.api.v1.mapper.TrackMapper;
import com.ssu.griddynamics.supercoolitunes.api.v1.model.TrackDTO;
import com.ssu.griddynamics.supercoolitunes.domain.Track;
import com.ssu.griddynamics.supercoolitunes.exception.ResourceNotFoundException;
import com.ssu.griddynamics.supercoolitunes.repositories.TrackRepository;
import com.ssu.griddynamics.supercoolitunes.services.TrackService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrackServiceImpl implements TrackService {

    private TrackRepository trackRepository;
    private TrackMapper trackMapper;
    private AuthorMapper authorMapper;

    public TrackServiceImpl(TrackRepository trackRepository, TrackMapper trackMapper, AuthorMapper authorMapper) {
        this.trackRepository = trackRepository;
        this.trackMapper = trackMapper;
        this.authorMapper = authorMapper;
    }

    @Override
    public List<TrackDTO> findAll() {
        return trackRepository
                .findAll()
                .stream()
                .map(track -> trackMapper.trackToTrackDTO(track))
                .collect(Collectors.toList());
    }

    @Override
    public TrackDTO findByTitle(String title) {
        return trackRepository
                .findByTitle(title)
                .map(trackMapper::trackToTrackDTO)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public TrackDTO findByAuthor(String authorName) {
        return trackRepository
                .findByAuthorName(authorName)
                .map(trackMapper::trackToTrackDTO)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public TrackDTO findByAlbum(String albumTitle) {
        return trackRepository
                .findByAlbumTitle(albumTitle)
                .map(trackMapper::trackToTrackDTO)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public TrackDTO createNewTrack(TrackDTO trackDTO) {
        Track savedTrack = trackRepository.save(trackMapper.trackDTOtoTrack(trackDTO));

        return trackMapper.trackToTrackDTO(savedTrack);
    }

    @Override
    public void deleteTrackById(Long id) {
        trackRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        trackRepository.deleteAll();
    }
}
