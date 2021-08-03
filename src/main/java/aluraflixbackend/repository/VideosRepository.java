package aluraflixbackend.repository;

import aluraflixbackend.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideosRepository extends JpaRepository<Video, Long> {
}
