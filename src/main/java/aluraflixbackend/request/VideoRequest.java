package aluraflixbackend.request;

import aluraflixbackend.model.Video;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class VideoRequest {

    @NotEmpty(message = "O campo 'titulo' não pode ser vazio.")
    @NotNull(message = "O campo 'titulo' não pode ser vazio.")
    private String titulo;
    @NotEmpty(message = "O campo 'descricao' não pode ser vazio.")
    @NotNull(message = "O campo 'descricao' não pode ser vazio.")
    private String descricao;
    @NotEmpty(message = "O campo 'url' não pode ser vazio.")
    @NotNull(message = "O campo 'url' não pode ser vazio.")
    private String url;

    private String categoriaId;

    public VideoRequest(@NotEmpty @NotNull String titulo, @NotEmpty @NotNull String descricao, @NotEmpty @NotNull String url, String categoriaId) {
        super();
        this.titulo = titulo;
        this.descricao = descricao;
        this.url = url;
        this.categoriaId = categoriaId;
    }

    public Video converter() {
        return new Video(this.getTitulo(), this.getDescricao(), this.getUrl(), this.getCategoriaId());
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
}
