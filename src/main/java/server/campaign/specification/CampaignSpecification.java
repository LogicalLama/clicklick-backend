package server.campaign.specification;

import org.springframework.data.jpa.domain.Specification;
import server.campaign.model.Campaign;
import server.campaign.model.CampaignStatus;
import server.campaign.model.CampaignType;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class CampaignSpecification {

    public static Specification<Campaign> hasType(CampaignType type) {
        return (Root<Campaign> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            if (type == null) {
                return builder.conjunction();
            }
            return builder.equal(root.get("type"), type);
        };
    }

    public static Specification<Campaign> hasStatus(CampaignStatus status) {
        return (Root<Campaign> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            if (status == null) {
                return builder.conjunction();
            }
            return builder.equal(root.get("status"), status);
        };
    }

    public static Specification<Campaign> hasBrandId(String brandId) {
        return (Root<Campaign> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            if (brandId == null || brandId.isEmpty()) {
                return builder.conjunction();
            }
            return builder.equal(root.get("brandId"), brandId);
        };
    }

    public static Specification<Campaign> hasBusinessId(String businessId) {
        return (Root<Campaign> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            if (businessId == null || businessId.isEmpty()) {
                return builder.conjunction();
            }
            return builder.equal(root.get("businessId"), businessId);
        };
    }
}
