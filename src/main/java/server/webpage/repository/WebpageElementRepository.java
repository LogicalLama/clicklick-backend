package server.webpage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.webpage.model.WebpageElement;
import server.webpage.model.WebpageElementId;

import java.util.List;

@Repository
public interface WebpageElementRepository extends JpaRepository<WebpageElement, WebpageElementId> {
    List<WebpageElement> findByWebpageId(String webpageId);
}
