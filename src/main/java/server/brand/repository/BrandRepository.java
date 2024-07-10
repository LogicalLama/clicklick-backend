package server.brand.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import server.brand.model.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, String>, JpaSpecificationExecutor<Brand> {
}
