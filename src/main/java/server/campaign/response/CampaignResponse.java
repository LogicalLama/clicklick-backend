package server.campaign.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import server.campaign.model.CampaignStatus;
import server.campaign.model.CampaignType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CampaignResponse {
    private String id;
    private String name;
    private CampaignType type;
    private CampaignStatus status;
    private String brandId;
    private String businessId;
}
