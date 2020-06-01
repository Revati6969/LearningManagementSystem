package com.bl.lms.controller;

import com.bl.lms.configuration.ApplicationConfig;
import com.bl.lms.dto.CandidateBankDetailsDTO;
import com.bl.lms.dto.CandidateQualificationDTO;
import com.bl.lms.dto.FellowshipCandidateDTO;
import com.bl.lms.dto.Response;
import com.bl.lms.model.CandidateBankDetails;
import com.bl.lms.model.CandidateQualification;
import com.bl.lms.model.FellowshipCandidate;
import com.bl.lms.service.ICandidateDocumentService;
import com.bl.lms.service.IFellowshipCandidateService;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/fellowshipdetails")
public class FellowshipCandidateController {

    @Autowired
    private IFellowshipCandidateService fellowshipCandidateService;

    @Autowired
    private ICandidateDocumentService candidateDocumentService;

    @PostMapping("/joincandidate")
    public ResponseEntity<Response> joinCandidate(@RequestParam(value = "id") long id) throws MessagingException {
        FellowshipCandidate fellowshipCandidate = fellowshipCandidateService.joinCandidateToFellowship(id);
        fellowshipCandidateService.sendMail(fellowshipCandidate);
        return new ResponseEntity<>(new Response(fellowshipCandidate, 200, ApplicationConfig.getMessageAccessor().getMessage("111")), HttpStatus.OK);
    }

    @GetMapping("/getcount")
    public ResponseEntity<Response> getCandidateCount() {
        int candidateCount = fellowshipCandidateService.getCandidateCount();
        return new ResponseEntity<>(new Response(candidateCount, 200, ApplicationConfig.getMessageAccessor().getMessage("112")), HttpStatus.OK);
    }

    @PutMapping("/updateinformation")
    public ResponseEntity<Response> updatePersonalInformation(@Valid @RequestBody FellowshipCandidateDTO fellowshipCandidateDto) throws JsonMappingException {
        FellowshipCandidate fellowshipCandidateModel = fellowshipCandidateService.updateInformation(fellowshipCandidateDto);
        return new ResponseEntity<>(new Response(fellowshipCandidateModel, 200, ApplicationConfig.getMessageAccessor().getMessage("111")), HttpStatus.OK);
    }

    @PostMapping("/updatebankdetails")
    public ResponseEntity<Response> updateBankDetails(@Valid @RequestBody CandidateBankDetails bankDetailsDto) {
        CandidateBankDetails updateDetails = fellowshipCandidateService.updateBankDetails(bankDetailsDto);
        return new ResponseEntity<>(new Response(updateDetails, 200, ApplicationConfig.getMessageAccessor().getMessage("110")), HttpStatus.OK);
    }

    @PostMapping("/updatequalificationdetails")
    public ResponseEntity<Response> updateQualificationDetails(@Valid @RequestBody CandidateQualificationDTO candidateQualificationDto) {
        CandidateQualification updateDetails = fellowshipCandidateService.updateQualificationDetails(candidateQualificationDto);
        return new ResponseEntity<>(new Response(updateDetails, 200, ApplicationConfig.getMessageAccessor().getMessage("110")), HttpStatus.OK);
    }

    @PostMapping("/upload/{id}")
    public ResponseEntity<Response> uploadDocuments(@PathVariable long id, @RequestParam("file") MultipartFile file,
                                                    @RequestParam("type") String type) throws IOException {
        return ResponseEntity.ok(candidateDocumentService.uploadFile(file, id, type));
    }

}
