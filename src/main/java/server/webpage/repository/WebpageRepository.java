package server.webpage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.webpage.model.Webpage;

import java.util.Optional;

@Repository
public interface WebpageRepository extends JpaRepository<Webpage, String> {
    Optional<Webpage> findByCampaignId(String campaignId);
}
