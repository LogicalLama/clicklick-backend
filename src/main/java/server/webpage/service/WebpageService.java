package server.webpage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.webpage.model.Webpage;
import server.webpage.model.WebpageElement;
import server.webpage.model.WebpageElementId;
import server.webpage.repository.WebpageElementRepository;
import server.webpage.repository.WebpageRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WebpageService {

    @Autowired
    private WebpageRepository webpageRepository;

    @Autowired
    private WebpageElementRepository webpageElementRepository;

    public List<Webpage> getAllWebpages() {
        return webpageRepository.findAll();
    }

    public Optional<Webpage> getWebpageById(String id) {
        return webpageRepository.findById(id);
    }

    public Webpage createWebpage(Webpage webpage) {
        return webpageRepository.save(webpage);
    }

    public Webpage updateWebpage(String id, Webpage webpageDetails) {
        return webpageRepository.findById(id).map(webpage -> {
            webpage.setHeading(webpageDetails.getHeading());
            webpage.setSubheading(webpageDetails.getSubheading());
            webpage.setDisplayImage(webpageDetails.getDisplayImage());
            webpage.setCampaignId(webpageDetails.getCampaignId());
            return webpageRepository.save(webpage);
        }).orElseThrow(() -> new RuntimeException("Webpage not found with id " + id));
    }

    public void deleteWebpage(String id) {
        webpageRepository.deleteById(id);
    }

    public WebpageElement addElementToWebpage(WebpageElement webpageElement) {
        return webpageElementRepository.save(webpageElement);
    }

    public void removeElementFromWebpage(WebpageElementId webpageElementId) {
        webpageElementRepository.deleteById(webpageElementId);
    }

    public Optional<Webpage> getWebpageByCampaignId(String campaignId) {
        return webpageRepository.findByCampaignId(campaignId);
    }

    public List<WebpageElement> getWebpageElementsByWebpageId(String webpageId) {
        return webpageElementRepository.findByWebpageId(webpageId);
    }
}
