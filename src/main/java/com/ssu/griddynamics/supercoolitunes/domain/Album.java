package com.ssu.griddynamics.supercoolitunes.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean isSingle;

    private boolean isCompilation;

    private String title;

    private String genre;

    private Date dateOfProduction;

    @OneToMany(mappedBy = "album")
    private List<Track> tracks = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "fk_author")
    private Author author;
}
