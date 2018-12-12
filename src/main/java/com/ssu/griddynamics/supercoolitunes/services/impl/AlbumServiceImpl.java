package com.ssu.griddynamics.supercoolitunes.services.impl;

import com.ssu.griddynamics.supercoolitunes.api.v1.mapper.AlbumMapper;
import com.ssu.griddynamics.supercoolitunes.api.v1.mapper.AuthorMapper;
import com.ssu.griddynamics.supercoolitunes.api.v1.mapper.TrackMapper;
import com.ssu.griddynamics.supercoolitunes.api.v1.model.AlbumDTO;
import com.ssu.griddynamics.supercoolitunes.api.v1.model.TrackDTO;
import com.ssu.griddynamics.supercoolitunes.domain.Album;
import com.ssu.griddynamics.supercoolitunes.domain.Author;
import com.ssu.griddynamics.supercoolitunes.domain.Track;
import com.ssu.griddynamics.supercoolitunes.exception.ResourceNotFoundException;
import com.ssu.griddynamics.supercoolitunes.repositories.AlbumRepository;
import com.ssu.griddynamics.supercoolitunes.repositories.AuthorRepository;
import com.ssu.griddynamics.supercoolitunes.repositories.TrackRepository;
import com.ssu.griddynamics.supercoolitunes.services.AlbumService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;
    private final TrackRepository trackRepository;
    private final AuthorRepository authorRepository;
    private final AlbumMapper albumMapper;
    private final AuthorMapper authorMapper;
    private final TrackMapper trackMapper;

    public AlbumServiceImpl(AlbumRepository albumRepository, TrackRepository trackRepository,
                            AuthorRepository authorRepository, AlbumMapper albumMapper,
                            AuthorMapper authorMapper, TrackMapper trackMapper) {
        this.albumRepository = albumRepository;
        this.trackRepository = trackRepository;
        this.authorRepository = authorRepository;
        this.albumMapper = albumMapper;
        this.authorMapper = authorMapper;
        this.trackMapper = trackMapper;
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

        Album album = albumMapper.albumDTOtoAlbum(albumDTO);

        Author albumAuthor = checkIfAuthorAlreadyExistsOrSave(obtainAuthorForAlbum(albumDTO));

        album.setAuthor(albumAuthor);

        album.setTracks(obtainTracksForAlbum(albumDTO));

        return albumMapper.albumToAlbumDTO(albumRepository.save(album));
    }

    private List<Track> obtainTracksForAlbum(AlbumDTO albumDTO) {

        List<Track> tracks = new ArrayList<>();

        if (albumDTO.getTracks() != null) {
            albumDTO.getTracks()
                    .forEach(trackDTO -> {
                        Track track = trackMapper.trackDTOtoTrack(trackDTO);
                        Author trackAuthor = checkIfAuthorAlreadyExistsOrSave(obtainAuthorForTrack(trackDTO));
                        track.setAuthor(trackAuthor);
                        tracks.add(checkIfTrackAlreadyExistsOrSave(track));
                    });
        }

        return tracks;
    }

    private Track checkIfTrackAlreadyExistsOrSave(Track track) {

        Optional<Track> trackInDb = trackRepository.findByTitle(track.getTitle());

        if (trackInDb.isPresent()) {
            return trackInDb.get();
        }

        return trackRepository.saveAndFlush(track);
    }

    private Author checkIfAuthorAlreadyExistsOrSave(Author trackAuthor) {

        Optional<Author> authorInDb = authorRepository.findByNickName(trackAuthor.getNickName());

        if (authorInDb.isPresent()) {
            return authorInDb.get();
        }

        return authorRepository.saveAndFlush(trackAuthor);
    }

    private Author obtainAuthorForAlbum(AlbumDTO albumDTO) {

        Author trackAuthor = new Author();

        if (albumDTO.getAuthor() != null) {
            return authorMapper.authorDTOtoAuthor(albumDTO.getAuthor());
        }

        return trackAuthor;
    }

    private Author obtainAuthorForTrack(TrackDTO trackDTO) {

        Author trackAuthor = new Author();

        if (trackDTO.getAuthor() != null) {
            return authorMapper.authorDTOtoAuthor(trackDTO.getAuthor());
        }

        return trackAuthor;
    }

    @Override
    public void deleteById(Long id) {
        albumRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        albumRepository.findAll();
    }
}
