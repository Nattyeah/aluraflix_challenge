package aluraflixbackend.service;

import aluraflixbackend.model.Categoria;
import aluraflixbackend.model.Video;
import aluraflixbackend.repository.CategoriaRepository;
import aluraflixbackend.repository.VideosRepository;
import aluraflixbackend.request.CategoriaRequest;
import aluraflixbackend.response.CategoriaResponse;
import aluraflixbackend.response.VideoResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private VideosRepository videoRepository;

    @Autowired
    private ModelMapper mapper;

    //    getAll
    public List<CategoriaResponse> list(String embed) {

        if (embed != null) {
            List<CategoriaResponse> categoriasResponse = new ArrayList<>();
            List<Categoria> categorias = categoriaRepository.findAll();
            categorias.forEach(c -> {
                CategoriaResponse categoria = mapper.map(c, CategoriaResponse.class);
                List<Video> videos = videoRepository.findByCategoryId(categoria.getId());
                categoria.setVideos(VideoResponse.converter(videos));
                categoriasResponse.add(categoria);
            });
                return categoriasResponse;
        }
        return categoriaRepository.findAll().stream().map(c -> mapper.map(c, CategoriaResponse.class))
                .collect(Collectors.toList());
    }

    //    getById
    public Optional<CategoriaResponse> detail(Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        return categoria.map(c -> mapper.map(c, CategoriaResponse.class));
    }

    //    post - preciso salvar essa req na base
    public CategoriaResponse save(CategoriaRequest request) {
        Categoria categoria = mapper.map(request, Categoria.class);
        categoria = categoriaRepository.save(categoria);
        return mapper.map(categoria, CategoriaResponse.class);
    }

    //    put
    @Transactional
    public Optional<CategoriaResponse> update(@Valid CategoriaRequest request, Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        return categoria.map(c -> {
            c.atualizar(request);
        return mapper.map(c, CategoriaResponse.class);
        });
    }
//    delete
    public void delete(Long id) { categoriaRepository.deleteById(id); }
}
