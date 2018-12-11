package com.ssu.griddynamics.supercoolitunes.services.impl;

import com.ssu.griddynamics.supercoolitunes.api.v1.mapper.AuthorMapper;
import com.ssu.griddynamics.supercoolitunes.api.v1.mapper.TrackMapper;
import com.ssu.griddynamics.supercoolitunes.api.v1.model.TrackDTO;
import com.ssu.griddynamics.supercoolitunes.domain.Author;
import com.ssu.griddynamics.supercoolitunes.domain.Track;
import com.ssu.griddynamics.supercoolitunes.exception.ResourceNotFoundException;
import com.ssu.griddynamics.supercoolitunes.repositories.AuthorRepository;
import com.ssu.griddynamics.supercoolitunes.repositories.TrackRepository;
import com.ssu.griddynamics.supercoolitunes.services.TrackService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TrackServiceImpl implements TrackService {

    private final TrackRepository trackRepository;
    private final AuthorRepository authorRepository;
    private final TrackMapper trackMapper;
    private final AuthorMapper authorMapper;

    public TrackServiceImpl(TrackRepository trackRepository, AuthorRepository authorRepository,
                            TrackMapper trackMapper, AuthorMapper authorMapper) {
        this.trackRepository = trackRepository;
        this.authorRepository = authorRepository;
        this.trackMapper = trackMapper;
        this.authorMapper = authorMapper;
    }

    @Override
    public List<TrackDTO> findAll() {
        return trackRepository
                .findAll()
                .stream()
                .map(trackMapper::trackToTrackDTO)
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

        Track track = trackMapper.trackDTOtoTrack(trackDTO);

        Author trackAuthor = obtainAuthorForTrack(trackDTO);

        trackAuthor = checkIfAuthorAlreadyExistsOrSave(trackAuthor);

        track.setAuthor(trackAuthor);

        return trackMapper.trackToTrackDTO(trackRepository.save(track));
    }

    private Author checkIfAuthorAlreadyExistsOrSave(Author trackAuthor) {

        Optional<Author> authorInDb = authorRepository.findByNickName(trackAuthor.getNickName());

        if (authorInDb.isPresent()) {
            return authorInDb.get();
        }

        return authorRepository.saveAndFlush(trackAuthor);
    }

    private Author obtainAuthorForTrack(TrackDTO trackDTO) {

        Author trackAuthor = new Author();

        if (trackDTO.getAuthor() != null) {
            return authorMapper.authorDTOtoAuthor(trackDTO.getAuthor());
        }

        return trackAuthor;
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
