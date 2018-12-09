package com.ssu.griddynamics.supercoolitunes.repositories;

import com.ssu.griddynamics.supercoolitunes.domain.Album;
import com.ssu.griddynamics.supercoolitunes.domain.Track;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlbumRepository extends JpaRepository<Album, Long> {

    Optional<Album> findByAuthorName(String name);

    Optional<Album> findByTitle(String title);

    //Optional<Album> findByTrack(Track track);
}
