package com.ssu.griddynamics.supercoolitunes.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Track {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int duration;
    private String title;
    private Year year;

    @ManyToOne
    private Album album;

    @ManyToMany(mappedBy = "tracks")
    private List<Author> authors = new ArrayList<>();
}
