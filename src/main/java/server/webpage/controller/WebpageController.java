package server.webpage.controller;

import io.micrometer.core.annotation.Timed;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.business.model.Business;
import server.business.repository.BusinessRepository;
import server.business.request.CreateBusinessRequest;
import server.business.request.UpdateBusinessRequest;
import server.business.service.BusinessService;
import server.webpage.response.WebpageContent;

import java.util.Arrays;

@RestController
@RequestMapping("/webpage")
@Slf4j
@Timed
public class WebpageController {

    @Autowired
    private BusinessRepository businessRepository;

    @Autowired
    private BusinessService businessService;

    @RequestMapping("/content")
    public ResponseEntity<WebpageContent> getContent(@RequestParam String campaignId) {
        Business business = businessService.getBusinessDetails(campaignId);
        WebpageContent webpage = WebpageContent.builder()
                .brandName(business.getName())
                .brandLogo(business.getBase64logo())
                .displayImage(business.getBase64logo())
                .heading("Clicklick is where you boost your business")
                .subheading("Clicklick allows you to host custom web pages where your users can enjoy gamified rewards.")
                .forms(Arrays.asList("21b3b904-f7e6-4f4e-9b94-bcdc5f72ad39"))
                .build();
        return ResponseEntity.ok(webpage);
    }

}
