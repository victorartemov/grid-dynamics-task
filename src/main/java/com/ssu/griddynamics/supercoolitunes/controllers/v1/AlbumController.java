package com.ssu.griddynamics.supercoolitunes.controllers.v1;

import com.ssu.griddynamics.supercoolitunes.api.v1.model.AlbumDTO;
import com.ssu.griddynamics.supercoolitunes.api.v1.model.AlbumListDTO;
import com.ssu.griddynamics.supercoolitunes.services.AlbumService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AlbumController.BASE_URL)
public class AlbumController {

    public static final String BASE_URL = "/api/v1/albums";

    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping
    public AlbumListDTO getAllAlbums() {
        return new AlbumListDTO(albumService.findAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AlbumDTO createNewAlbum(@RequestBody AlbumDTO albumDTO) {
        return albumService.createNewAlbum(albumDTO);
    }

    @GetMapping("/search/by-id")
    @ResponseStatus(HttpStatus.OK)
    public AlbumDTO findById(@RequestParam Long id) {
        return albumService.findById(id);
    }
}
