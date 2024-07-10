package server.campaign.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import server.campaign.model.Campaign;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, String>, JpaSpecificationExecutor<Campaign> {
}
