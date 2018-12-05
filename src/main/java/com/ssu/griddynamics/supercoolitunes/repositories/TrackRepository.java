package com.ssu.griddynamics.supercoolitunes.repositories;

import com.ssu.griddynamics.supercoolitunes.domain.Track;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackRepository extends JpaRepository<Track, Long> {
}
