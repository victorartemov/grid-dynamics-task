package com.ssu.griddynamics.supercoolitunes.repositories;

import com.ssu.griddynamics.supercoolitunes.domain.Track;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrackRepository extends JpaRepository<Track, Long> {
    Optional<Track> findByTitle(String title);

    Optional<Track> findByAlbumTitle(String albumTitle);

    Optional<Track> findByAuthorName(String authorName);
}
