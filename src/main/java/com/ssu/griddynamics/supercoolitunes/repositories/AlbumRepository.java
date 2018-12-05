package com.ssu.griddynamics.supercoolitunes.repositories;

import com.ssu.griddynamics.supercoolitunes.domain.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long> {
}
