package server.campaign.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import server.campaign.model.CampaignType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CampaignRequest {
    private String name;
    private CampaignType type;
    private String brandId;
    private String businessId;
}
