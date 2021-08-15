package aluraflixbackend.repository;

import aluraflixbackend.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideosRepository extends JpaRepository<Video, Long> {

    List<Video> findByTitle(String title);
    List<Video> findByCategoryId(Long id);
}
