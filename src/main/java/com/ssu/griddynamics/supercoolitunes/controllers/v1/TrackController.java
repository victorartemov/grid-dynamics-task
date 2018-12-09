package com.ssu.griddynamics.supercoolitunes.controllers.v1;

import com.ssu.griddynamics.supercoolitunes.api.v1.model.TrackDTO;
import com.ssu.griddynamics.supercoolitunes.api.v1.model.TrackListDTO;
import com.ssu.griddynamics.supercoolitunes.services.TrackService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(TrackController.BASE_URL)
public class TrackController {

    public static final String BASE_URL = "/api/v1/tracks";

    private final TrackService trackService;

    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public TrackListDTO getAllTracks() {
        return new TrackListDTO(trackService.findAll());
    }

    @GetMapping({"/search/title"})
    @ResponseStatus(HttpStatus.OK)
    public TrackDTO findByTitle(@RequestParam(value = "title") String title) {
        return trackService.findByTitle(title);
    }

    @GetMapping({"/search/author"})
    @ResponseStatus(HttpStatus.OK)
    public TrackDTO findByAuthor(@RequestParam(value = "authorName") String authorName) {
        return trackService.findByAuthor(authorName);
    }

    @GetMapping({"/search/album"})
    @ResponseStatus(HttpStatus.OK)
    public TrackDTO findByAlbum(@RequestParam(value = "albumTitle") String albumTitle) {
        return trackService.findByAlbum(albumTitle);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TrackDTO createNewTrack(@RequestBody TrackDTO trackDTO) {
        return trackService.createNewTrack(trackDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTrackById(@PathVariable Long id) {
        trackService.deleteTrackById(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllTracks() {
        trackService.deleteAll();
    }
}
