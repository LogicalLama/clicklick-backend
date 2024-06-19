package server.business.controller;

import io.micrometer.core.annotation.Timed;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.business.model.Business;
import server.business.repository.BusinessRepository;
import server.business.request.CreateBusinessRequest;
import server.business.request.UpdateBusinessRequest;
import server.business.service.BusinessService;

@RestController
@RequestMapping("/business")
@Slf4j
@Timed
public class BusinessController {

    @Autowired
    private BusinessRepository businessRepository;

    @Autowired
    private BusinessService businessService;



    @PostMapping("/setup")
    public ResponseEntity<Business> signup(@RequestBody @Valid CreateBusinessRequest setupRequest) {
        return ResponseEntity.ok(businessService.setup(setupRequest));
    }

    @PostMapping("/update")
    public ResponseEntity<Business> update(@RequestBody @Valid UpdateBusinessRequest updateRequest) {
        return ResponseEntity.ok(businessService.update(updateRequest));
    }
//
//    @PreAuthorize("hasAnyAuthority('ORG_ADMIN', 'ORG_USER')")
//    @RequestMapping("/details")
//    public Organization getOrganizationDetails(Principal principal) {
//        WhyralUser whyralUser = getWhyralUser(principal);
//        return organizationService.getOrganizationDetails(whyralUser.getOrgID());
//    }
//
//    @PreAuthorize("hasAuthority('ORG_ADMIN')")
//    @PostMapping("/fundingAccount/create")
//    public FundingAccount createFundingAccount(@RequestBody CreateFundingAccountReq createFundingAccountReq,
//                                               Principal principal) {
//        WhyralUser whyralUser = getWhyralUser(principal);
//        return organizationService.createFundingAccount(whyralUser.getOrgID(),
//                createFundingAccountReq.getName(), createFundingAccountReq.getCurrencyName(),
//                createFundingAccountReq.getMinimumBalance());
//    }
//
//    @PreAuthorize("hasAuthority('ORG_ADMIN')")
//    @PostMapping("/fundingAccount/update")
//    public FundingAccount updateFundingAccount(@RequestBody UpdateFundingAccountReq updateFundingAccountReq,
//                                               Principal principal) {
//        WhyralUser whyralUser = getWhyralUser(principal);
//        return organizationService.updateFundingAccount(
//                whyralUser.getOrgID(),
//                updateFundingAccountReq.getId(),
//                updateFundingAccountReq.getName(),
//                updateFundingAccountReq.getMinimumBalance());
//    }
//
//    @PreAuthorize("hasAuthority('ORG_ADMIN')")
//    @PostMapping("/addFundRequest")
//    public AddFundRequest addFund(@Valid @RequestBody AddFundRequest addFundRequest, Principal principal) {
//        WhyralUser whyralUser = getWhyralUser(principal);
//        return organizationService.addFundRequest(whyralUser.getOrgID(),
//                addFundRequest.getAmount(),
//                addFundRequest.getFundingAccountID(),
//                addFundRequest.getTransactionID(),
//                addFundRequest.getRemarks());
//    }
//
//    @PreAuthorize("hasAuthority('ORG_ADMIN')")
//    @RequestMapping("/getFundRequest")
//    public List<AddFundRequest> getFundRequests(@RequestParam String fundingAccountID, Principal principal) {
//        WhyralUser whyralUser = getWhyralUser(principal);
//        return organizationService.getFundRequest(whyralUser.getOrgID(), fundingAccountID);
//    }
//
//    @PreAuthorize("hasAnyAuthority('ORG_ADMIN', 'ORG_USER')")
//    @RequestMapping("/getFundingAccount")
//    public List<GetFundingAccountsResponse> getFundingAccount(Principal principal) {
//        WhyralUser whyralUser = getWhyralUser(principal);
//        return organizationService.getFundingAccounts(whyralUser.getOrgID());
//    }
//
//    @PreAuthorize("hasAuthority('ADMIN')")
//    @RequestMapping("/orgInternal/create")
//    public Organization createInternalLedger(Principal principal) {
//        WhyralUser whyralUser = getWhyralUser(principal);
//        Currency currency = currencyService.findByName("SYSTEM", "INR");
//        Organization organization = organizationRepository
//                .findById(whyralUser.getOrgID()).orElseThrow(() -> new WhyralException("organization not found"));
//        if (organization.getInternalLedger() == null) {
//            LedgerMaster internalLedger =
//                    ledgerService.createLedger(organization.getId(), LedgerType.LIABILITY, currency.getId());
//            organization.setInternalLedger(internalLedger.getId());
//            return organizationRepository.save(organization);
//        }
//        throw new WhyralException("internal Ledger already present");
//    }
//
//    @PreAuthorize("hasAnyAuthority('ORG_ADMIN', 'ORG_USER')")
//    @RequestMapping("/update")
//    public Organization updateOrgDetails(@RequestBody UpdateOrgRequest req, Principal principal) {
//        WhyralUser whyralUser = getWhyralUser(principal);
//        Organization organization = organizationRepository
//                .findById(whyralUser.getOrgID()).orElseThrow(() -> new WhyralException("organization not found"));
//        if (req.getHost() != null)
//            organization.setHost(req.getHost());
//
//        if (req.getBase64Logo() != null)
//            organization.setBase64logo(req.getBase64Logo());
//        return organizationRepository.save(organization);
//    }


}
