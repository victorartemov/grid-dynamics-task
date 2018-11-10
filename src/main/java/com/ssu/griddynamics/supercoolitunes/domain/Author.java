package com.ssu.griddynamics.supercoolitunes.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name, nickName, genre, country;
    private Date dateOfBirth;

    @ManyToMany
    @JoinTable(name = "authors_tracks",
            joinColumns = {@JoinColumn(name = "fk_author")},
            inverseJoinColumns = {@JoinColumn(name = "fk_track")})
    private List<Track> tracks = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "authors_albums",
            joinColumns = {@JoinColumn(name = "fk_author")},
            inverseJoinColumns = {@JoinColumn(name = "fk_album")})
    private List<Album> albums = new ArrayList<>();
}
