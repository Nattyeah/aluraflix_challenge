package aluraflixbackend.response;

import aluraflixbackend.model.Video;

import java.util.List;
import java.util.stream.Collectors;

public class VideoResponse {

    private Long id;
    private String titulo;
    private String descricao;
    private String url;
    private String categoriaId;

    public VideoResponse(Video video) {
        this.id = video.getId();
        this.titulo = video.getTitulo();
        this.descricao = video.getDescricao();
        this.url = video.getUrl();
        this.categoriaId = video.getCategoriaId();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(String categoriaId) {
        this.categoriaId = categoriaId;
    }

    public Long getId() {
        return id;
    }

    public static List<VideoResponse> converter(List<Video> videos) {
        return videos.stream().map(VideoResponse::new).collect(Collectors.toList());
    }
}
