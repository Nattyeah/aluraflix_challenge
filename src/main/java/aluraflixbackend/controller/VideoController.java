package aluraflixbackend.controller;

import aluraflixbackend.model.Video;
import aluraflixbackend.request.VideoRequest;
import aluraflixbackend.response.VideoResponse;
import aluraflixbackend.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/videos")
public class VideoController {

    @Autowired
    private VideoService service;

    @GetMapping
    public List<VideoResponse> getAll(String title) {
        return service.getAll(title);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<VideoResponse> getById(@PathVariable Long id) {
        Optional<VideoResponse> video = service.getById(id);
        return video.map(v -> ResponseEntity.ok(v)).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<VideoResponse> create(@RequestBody @Valid VideoRequest request, UriComponentsBuilder builder) {
        try {
            Video video = service.create(request);
            URI uri = builder.path("/videos/{id}").buildAndExpand(video.getId()).toUri();
            return ResponseEntity.created(uri).body(new VideoResponse(video));
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria não cadastrada", e);
        }
    }


    @PutMapping(path = "/{id}")
    public ResponseEntity<VideoResponse> update(@PathVariable Long id, @RequestBody @Valid VideoRequest request) {
        try {
            Optional<VideoResponse> video = service.update(request, id);
           return video.map(v -> ResponseEntity.ok(v)).orElse(ResponseEntity.notFound().build());
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria não cadastrada", e);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.ok().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
