package com.ssu.griddynamics.supercoolitunes.bootstrap;

import com.ssu.griddynamics.supercoolitunes.domain.Album;
import com.ssu.griddynamics.supercoolitunes.domain.Author;
import com.ssu.griddynamics.supercoolitunes.domain.Track;
import com.ssu.griddynamics.supercoolitunes.repositories.AlbumRepository;
import com.ssu.griddynamics.supercoolitunes.repositories.AuthorRepository;
import com.ssu.griddynamics.supercoolitunes.repositories.TrackRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Year;
import java.util.ArrayList;
import java.util.Date;

@Component
public class Bootstrap implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final TrackRepository trackRepository;
    private final AlbumRepository albumRepository;

    public Bootstrap(AuthorRepository authorRepository, TrackRepository trackRepository, AlbumRepository albumRepository) {
        this.authorRepository = authorRepository;
        this.trackRepository = trackRepository;
        this.albumRepository = albumRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        //check http://localhost:8080/h2-console to see created tables

        loadSampleData();
    }

    private void loadSampleData() {

        Author author1 = new Author();
        author1.setName("Eric Patrick Clapton");
        author1.setCountry("England");
        author1.setGenre("Blues");
        author1.setNickName("Clapton");
        author1.setDateOfBirth(new Date(1945, 4, 30));

        Author author2 = new Author();
        author2.setName("Farrokh Bulsara");
        author2.setCountry("England");
        author2.setGenre("Rock");
        author2.setNickName("Freddie Mercury");
        author2.setDateOfBirth(new Date(1946, 9, 5));

        authorRepository.save(author1);
        authorRepository.save(author2);

        Album album1 = new Album();
        album1.setCompilation(true);
        album1.setAuthor(author1);
        album1.setGenre("Blues");
        album1.setSingle(false);
        album1.setDateOfProduction(new Date(1992, 8, 18));
        album1.setTitle("Unplugged");
        album1.setTracks(new ArrayList<>());

        Album album2 = new Album();
        album2.setCompilation(true);
        album2.setAuthor(author2);
        album2.setGenre("Rock");
        album2.setSingle(false);
        album2.setDateOfProduction(new Date(1977, 10, 28));
        album2.setTitle("News of the World");
        album2.setTracks(new ArrayList<>());

        Track track1 = new Track();
        track1.setAlbum(album1);
        album1.getTracks().add(track1);
        track1.setAuthor(author1);
        track1.setDuration(3);
        track1.setTitle("Layla");
        track1.setYear(Year.of(1970));

        Track track2 = new Track();
        track2.setAlbum(album2);
        album2.getTracks().add(track2);
        track2.setAuthor(author2);
        track2.setDuration(4);
        track2.setTitle("We Will Rock You");
        track2.setYear(Year.of(1977));

        albumRepository.save(album1);
        albumRepository.save(album2);

        trackRepository.save(track1);
        trackRepository.save(track2);
    }
}
