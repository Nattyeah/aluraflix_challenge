package aluraflixbackend.repository;

import aluraflixbackend.model.Categoria;
import aluraflixbackend.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
