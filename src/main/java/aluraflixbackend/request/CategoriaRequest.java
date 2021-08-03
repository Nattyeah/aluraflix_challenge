package aluraflixbackend.request;

import aluraflixbackend.model.Categoria;
import aluraflixbackend.model.Video;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CategoriaRequest {

    @NotEmpty(message = "O campo 'titulo' não pode ser vazio.")
    @NotNull(message = "O campo 'titulo' não pode ser vazio.")
    private String titulo;
    @NotEmpty(message = "O campo 'cor' não pode ser vazio.")
    @NotNull(message = "O campo 'cor' não pode ser vazio.")
    private String cor;

    public CategoriaRequest(@NotEmpty @NotNull String titulo, @NotEmpty @NotNull String cor) {
        super();
        this.titulo = titulo;
        this.cor = cor;
    }

    public Categoria converter() {
        return new Categoria(this.getTitulo(), this.getCor());
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
}
