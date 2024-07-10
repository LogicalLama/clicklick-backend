package server.campaign.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.campaign.request.CampaignRequest;
import server.campaign.response.CampaignResponse;
import server.campaign.model.Campaign;
import server.campaign.model.CampaignStatus;
import server.campaign.model.CampaignType;
import server.campaign.service.CampaignService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/campaign")
public class CampaignController {

    @Autowired
    private CampaignService campaignService;

    @GetMapping
    public ResponseEntity<List<CampaignResponse>> getCampaigns(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String brandId,
            @RequestParam(required = false) String businessId
    ) {
        List<Campaign> campaigns = campaignService.getCampaigns(type, status, brandId, businessId);
        List<CampaignResponse> response = campaigns.stream().map(this::convertToDto).collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CampaignResponse> getCampaignById(@PathVariable String id) {
        return campaignService.getCampaignById(id)
                .map(campaign -> ResponseEntity.ok(convertToDto(campaign)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CampaignResponse> createCampaign(@RequestBody CampaignRequest campaignRequest) {
        Campaign campaign = convertToEntity(campaignRequest);
        Campaign createdCampaign = campaignService.createCampaign(campaign);
        return ResponseEntity.ok(convertToDto(createdCampaign));
    }

    @PostMapping("/{id}/update")
    public ResponseEntity<CampaignResponse> updateCampaign(@PathVariable String id, @RequestBody CampaignRequest campaignRequest) {
        Campaign campaignDetails = convertToEntity(campaignRequest);
        return ResponseEntity.ok(convertToDto(campaignService.updateCampaign(id, campaignDetails)));
    }

    @GetMapping("/{id}/delete")
    public ResponseEntity<Void> deleteCampaign(@PathVariable String id) {
        campaignService.deleteCampaign(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/pause")
    public ResponseEntity<CampaignResponse> pauseCampaign(@PathVariable String id) {
        return ResponseEntity.ok(convertToDto(campaignService.pauseCampaign(id)));
    }

    @GetMapping("/{id}/unpause")
    public ResponseEntity<CampaignResponse> unpauseCampaign(@PathVariable String id) {
        return ResponseEntity.ok(convertToDto(campaignService.unpauseCampaign(id)));
    }

    private Campaign convertToEntity(CampaignRequest campaignRequest) {
        return Campaign.builder()
                .name(campaignRequest.getName())
                .type(campaignRequest.getType())
                .brandId(campaignRequest.getBrandId())
                .businessId(campaignRequest.getBusinessId())
                .status(CampaignStatus.ACTIVE)
                .build();
    }

    private CampaignResponse convertToDto(Campaign campaign) {
        return CampaignResponse.builder()
                .id(campaign.getId())
                .name(campaign.getName())
                .type(campaign.getType())
                .status(campaign.getStatus())
                .brandId(campaign.getBrandId())
                .businessId(campaign.getBusinessId())
                .build();
    }
}
