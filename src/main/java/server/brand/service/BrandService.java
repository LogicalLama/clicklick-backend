package server.brand.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import server.brand.model.Brand;
import server.brand.model.BrandStatus;
import server.brand.repository.BrandRepository;
import server.brand.specification.BrandSpecification;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    public List<Brand> getBrands(String status, String businessId) {
        Specification<Brand> spec = Specification.where(BrandSpecification.hasStatus(status != null ? BrandStatus.valueOf(status) : null))
                .and(BrandSpecification.hasBusinessId(businessId));
        return brandRepository.findAll(spec);
    }

    public Optional<Brand> getBrandById(String id) {
        return brandRepository.findById(id);
    }

    public Brand createBrand(Brand brand) {
        brand.setId(UUID.randomUUID().toString());
        brand.setStatus(BrandStatus.ACTIVE); // Set the brand status to ACTIVE when creating
        return brandRepository.save(brand);
    }

    public Brand updateBrand(String id, Brand brandDetails) {
        return brandRepository.findById(id).map(brand -> {
            Optional.ofNullable(brandDetails.getName()).ifPresent(brand::setName);
            Optional.ofNullable(brandDetails.getStatus()).ifPresent(brand::setStatus);
            Optional.ofNullable(brandDetails.getWebsite()).ifPresent(brand::setWebsite);
            Optional.ofNullable(brandDetails.getBase64logo()).ifPresent(brand::setBase64logo);
            Optional.ofNullable(brandDetails.getBusinessId()).ifPresent(brand::setBusinessId);
            return brandRepository.save(brand);
        }).orElseThrow(() -> new RuntimeException("Brand not found with id " + id));
    }

    public void deleteBrand(String id) {
        brandRepository.findById(id).map(brand -> {
            brand.setStatus(BrandStatus.DELETED); // Set the brand status to DELETED when deleting
            return brandRepository.save(brand);
        }).orElseThrow(() -> new RuntimeException("Brand not found with id " + id));
    }
}
