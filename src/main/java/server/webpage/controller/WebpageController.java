package server.webpage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.brand.model.Brand;
import server.brand.service.BrandService;
import server.campaign.model.Campaign;
import server.campaign.service.CampaignService;
import server.webpage.model.ElementType;
import server.webpage.response.WebpageContent;
import server.webpage.model.Webpage;
import server.webpage.model.WebpageElement;
import server.webpage.model.WebpageElementId;
import server.webpage.service.WebpageService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/webpage")
public class WebpageController {

    @Autowired
    private WebpageService webpageService;

    @Autowired
    private CampaignService campaignService;

    @Autowired
    private BrandService brandService;

    @GetMapping
    public ResponseEntity<List<Webpage>> getAllWebpages() {
        return ResponseEntity.ok(webpageService.getAllWebpages());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Webpage> getWebpageById(@PathVariable String id) {
        Optional<Webpage> webpageOptional = webpageService.getWebpageById(id);
        if (!webpageOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(webpageOptional.get());
    }

    @PostMapping
    public ResponseEntity<Webpage> createWebpage(@RequestBody Webpage webpage) {
        webpage.setId(UUID.randomUUID().toString());
        return ResponseEntity.ok(webpageService.createWebpage(webpage));
    }

    @PostMapping("/{id}/update")
    public ResponseEntity<Webpage> updateWebpage(@PathVariable String id, @RequestBody Webpage webpageDetails) {
        return ResponseEntity.ok(webpageService.updateWebpage(id, webpageDetails));
    }

    @RequestMapping("/{id}/delete")
    public ResponseEntity<Void> deleteWebpage(@PathVariable String id) {
        webpageService.deleteWebpage(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/element")
    public ResponseEntity<WebpageElement> addElementToWebpage(@RequestBody WebpageElement webpageElement) {
        return ResponseEntity.ok(webpageService.addElementToWebpage(webpageElement));
    }

    @DeleteMapping("/element")
    public ResponseEntity<Void> removeElementFromWebpage(@RequestBody WebpageElementId webpageElementId) {
        webpageService.removeElementFromWebpage(webpageElementId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/content")
    public ResponseEntity<WebpageContent> getWebpageContent(@RequestParam String campaignId) {
        Optional<Campaign> campaignOptional = campaignService.getCampaignById(campaignId);
        if (!campaignOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Campaign campaign = campaignOptional.get();
        Optional<Brand> brandOptional = brandService.getBrandById(campaign.getBrandId());
        if (!brandOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Brand brand = brandOptional.get();
        Optional<Webpage> webpageOptional = webpageService.getWebpageByCampaignId(campaignId);
        if (!webpageOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Webpage webpage = webpageOptional.get();
        List<WebpageElement> webpageElements = webpageService.getWebpageElementsByWebpageId(webpage.getId());
        List<String> forms = webpageElements.stream()
                .filter(element -> element.getType() == ElementType.FORM)
                .map(WebpageElement::getElementId)
                .collect(Collectors.toList());

        WebpageContent webpageContent = WebpageContent.builder()
                .brandName(brand.getName())
                .brandLogo(brand.getBase64logo())
                .heading(webpage.getHeading())
                .subheading(webpage.getSubheading())
                .displayImage(webpage.getDisplayImage())
                .forms(forms)
                .build();

        return ResponseEntity.ok(webpageContent);
    }
}

//package server.webpage.controller;
//
//import io.micrometer.core.annotation.Timed;
//import jakarta.validation.Valid;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import server.business.model.Business;
//import server.business.repository.BusinessRepository;
//import server.business.request.CreateBusinessRequest;
//import server.business.request.UpdateBusinessRequest;
//import server.business.service.BusinessService;
//import server.webpage.response.WebpageContent;
//
//import java.util.Arrays;
//
//@RestController
//@RequestMapping("/webpage")
//@Slf4j
//@Timed
//public class WebpageController {
//
//    @Autowired
//    private BusinessRepository businessRepository;
//
//    @Autowired
//    private BusinessService businessService;
//
//    @RequestMapping("/content")
//    public ResponseEntity<WebpageContent> getContent(@RequestParam String campaignId) {
//        Business business = businessService.getBusinessDetails(campaignId);
//        WebpageContent webpage = WebpageContent.builder()
//                .brandName(business.getName())
//                .brandLogo(business.getBase64logo())
//                .displayImage(business.getBase64logo())
//                .heading("Clicklick is where you boost your business")
//                .subheading("Clicklick allows you to host custom web pages where your users can enjoy gamified rewards.")
//                .forms(Arrays.asList("21b3b904-f7e6-4f4e-9b94-bcdc5f72ad39"))
//                .build();
//        return ResponseEntity.ok(webpage);
//    }
//
//}
