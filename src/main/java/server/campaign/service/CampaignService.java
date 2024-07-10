package server.campaign.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import server.campaign.model.Campaign;
import server.campaign.model.CampaignStatus;
import server.campaign.model.CampaignType;
import server.campaign.repository.CampaignRepository;
import server.campaign.specification.CampaignSpecification;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CampaignService {

    @Autowired
    private CampaignRepository campaignRepository;

    public List<Campaign> getCampaigns(String type, String status, String brandId, String businessId) {
        Specification<Campaign> spec = Specification.where(CampaignSpecification.hasType(type != null ? CampaignType.valueOf(type) : null))
                .and(CampaignSpecification.hasStatus(status != null ? CampaignStatus.valueOf(status) : null))
                .and(CampaignSpecification.hasBrandId(brandId))
                .and(CampaignSpecification.hasBusinessId(businessId));
        return campaignRepository.findAll(spec);
    }

    public Optional<Campaign> getCampaignById(String id) {
        return campaignRepository.findById(id);
    }

    public Campaign createCampaign(Campaign campaign) {
        campaign.setId(UUID.randomUUID().toString());
        campaign.setStatus(CampaignStatus.ACTIVE); // Set the campaign status to ACTIVE when creating
        return campaignRepository.save(campaign);
    }

    public Campaign updateCampaign(String id, Campaign campaignDetails) {
        return campaignRepository.findById(id).map(campaign -> {
            Optional.ofNullable(campaignDetails.getName()).ifPresent(campaign::setName);
            Optional.ofNullable(campaignDetails.getType()).ifPresent(campaign::setType);
            Optional.ofNullable(campaignDetails.getStatus()).ifPresent(campaign::setStatus);
            Optional.ofNullable(campaignDetails.getBrandId()).ifPresent(campaign::setBrandId);
//            Optional.ofNullable(campaignDetails.getBusinessId()).ifPresent(campaign::setBusinessId);
            return campaignRepository.save(campaign);
        }).orElseThrow(() -> new RuntimeException("Campaign not found with id " + id));
    }

    public void deleteCampaign(String id) {
        campaignRepository.findById(id).map(campaign -> {
            campaign.setStatus(CampaignStatus.DELETED); // Set the campaign status to DELETED when deleting
            return campaignRepository.save(campaign);
        }).orElseThrow(() -> new RuntimeException("Campaign not found with id " + id));
    }

    public Campaign pauseCampaign(String id) {
        return campaignRepository.findById(id).map(campaign -> {
            campaign.setStatus(CampaignStatus.PAUSED); // Set the campaign status to PAUSED when pausing
            return campaignRepository.save(campaign);
        }).orElseThrow(() -> new RuntimeException("Campaign not found with id " + id));
    }

    public Campaign unpauseCampaign(String id) {
        return campaignRepository.findById(id).map(campaign -> {
            campaign.setStatus(CampaignStatus.ACTIVE); // Set the campaign status to ACTIVE when unpausing
            return campaignRepository.save(campaign);
        }).orElseThrow(() -> new RuntimeException("Campaign not found with id " + id));
    }
}
