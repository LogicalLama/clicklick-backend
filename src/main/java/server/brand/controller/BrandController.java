package server.brand.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.brand.request.BrandRequest;
import server.brand.response.BrandResponse;
import server.brand.model.Brand;
import server.brand.service.BrandService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping
    public ResponseEntity<List<BrandResponse>> getBrands(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String businessId
    ) {
        List<Brand> brands = brandService.getBrands(status, businessId);
        List<BrandResponse> response = brands.stream().map(this::convertToDto).collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandResponse> getBrandById(@PathVariable String id) {
        return brandService.getBrandById(id)
                .map(brand -> ResponseEntity.ok(convertToDto(brand)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BrandResponse> createBrand(@RequestBody BrandRequest brandRequest) {
        Brand brand = convertToEntity(brandRequest);
        Brand createdBrand = brandService.createBrand(brand);
        return ResponseEntity.ok(convertToDto(createdBrand));
    }

    @PostMapping("/{id}/update")
    public ResponseEntity<BrandResponse> updateBrand(@PathVariable String id, @RequestBody BrandRequest brandRequest) {
        Brand brandDetails = convertToEntity(brandRequest);
        return ResponseEntity.ok(convertToDto(brandService.updateBrand(id, brandDetails)));
    }

    @GetMapping("/{id}/delete")
    public ResponseEntity<Void> deleteBrand(@PathVariable String id) {
        brandService.deleteBrand(id);
        return ResponseEntity.ok().build();
    }

    private Brand convertToEntity(BrandRequest brandRequest) {
        return Brand.builder()
                .name(brandRequest.getName())
                .status(brandRequest.getStatus())
                .website(brandRequest.getWebsite())
                .base64logo(brandRequest.getBase64logo())
                .businessId(brandRequest.getBusinessId())
                .build();
    }

    private BrandResponse convertToDto(Brand brand) {
        return BrandResponse.builder()
                .id(brand.getId())
                .name(brand.getName())
                .status(brand.getStatus())
                .website(brand.getWebsite())
                .base64logo(brand.getBase64logo())
                .businessId(brand.getBusinessId())
                .build();
    }
}
