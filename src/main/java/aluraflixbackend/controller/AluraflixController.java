package aluraflixbackend.controller;

import aluraflixbackend.model.Video;
import aluraflixbackend.repository.AluraflixRepository;
import aluraflixbackend.request.VideoRequest;
import aluraflixbackend.response.VideoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/videos")
public class AluraflixController {

    @Autowired
    private AluraflixRepository repository;

    @GetMapping
    public List<VideoResponse> getAll() {
        return VideoResponse.converter(repository.findAll());
    }

    @GetMapping(path = "/{id}")
    ResponseEntity<VideoResponse> getById(@PathVariable Long id) {
        Optional<Video> video = repository.findById(id);
        if (video.isPresent())
            return ResponseEntity.ok(new VideoResponse(video.get()));
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<VideoResponse> create(@RequestBody @Valid VideoRequest request, UriComponentsBuilder builder) {
        Video video = request.converter();
        repository.save(video);

        URI uri = builder.path("/videos/{id}").buildAndExpand(video.getId()).toUri();
        return ResponseEntity.created(uri).body(new VideoResponse(video));
    }

    @Transactional
    @PutMapping(path = "/{id}")
    public ResponseEntity<VideoResponse> update(@PathVariable Long id, @RequestBody @Valid VideoRequest request) {
        Optional<Video> video = repository.findById(id);

        if (video.isPresent()) {
            video.get().atualizar(request);
            return ResponseEntity.ok(new VideoResponse(video.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        try {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
