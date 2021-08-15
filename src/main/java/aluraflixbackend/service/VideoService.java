package aluraflixbackend.service;

import aluraflixbackend.model.Video;
import aluraflixbackend.repository.VideosRepository;
import aluraflixbackend.request.VideoRequest;
import aluraflixbackend.response.VideoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VideoService {

    @Autowired
    private VideosRepository repository;

    //getbycategory -
    public List<VideoResponse> getByCategory(Long id) {
        List<Video> categorias = repository.findByCategoryId(id);
        return categorias.stream().map(VideoResponse::new).collect(Collectors.toList());
    }

    //    getAll
    public List<VideoResponse> getAll(String title) {
        return Optional.ofNullable(title).map(t -> VideoResponse.converter(repository.findByTitle(t)))
                .orElse(VideoResponse.converter(repository.findAll()));
    }

    //    getById
    public Optional<VideoResponse> getById(Long id) {
        Optional<Video> video = repository.findById(id);
        return video.map(VideoResponse::new);
    }

    //    post - preciso salvar essa req na base
    public Video create(@Valid VideoRequest request) {
        return repository.save(request.converter());
    }

    //    put
    @Transactional
    public Optional<VideoResponse> update(@Valid VideoRequest request, Long id) {
        Optional<Video> video = repository.findById(id);
        return video.map(v -> {
            v.atualizar(request);
        return new VideoResponse(v);
        });
    }
//    delete
    public void delete(Long id) { repository.deleteById(id); }
}
