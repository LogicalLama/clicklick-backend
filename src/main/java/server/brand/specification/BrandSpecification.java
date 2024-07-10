package server.brand.specification;

import org.springframework.data.jpa.domain.Specification;
import server.brand.model.Brand;
import server.brand.model.BrandStatus;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class BrandSpecification {

    public static Specification<Brand> hasStatus(BrandStatus status) {
        return (Root<Brand> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            if (status == null) {
                return builder.conjunction();
            }
            return builder.equal(root.get("status"), status);
        };
    }

    public static Specification<Brand> hasBusinessId(String businessId) {
        return (Root<Brand> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            if (businessId == null || businessId.isEmpty()) {
                return builder.conjunction();
            }
            return builder.equal(root.get("businessId"), businessId);
        };
    }
}
