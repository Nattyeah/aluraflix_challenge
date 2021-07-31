package aluraflixbackend.repository;

import aluraflixbackend.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AluraflixRepository extends JpaRepository<Video, Long> {
}
