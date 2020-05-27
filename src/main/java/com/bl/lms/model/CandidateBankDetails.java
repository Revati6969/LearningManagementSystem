package com.bl.lms.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Table
@Entity(name = "candidate_bank_details")
public class CandidateBankDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long candidateId;
    private String name;
    private long accountNumber;
    private String isAccountNumberVerified;
    private String ifscCode;
    private String isIfscCodeVerified;
    private String panNumber;
    private String isPanNumberVerified;
    private long addhaarNumber;
    private String isAdhaarNumVerified;
    private Date creatorStamp;
    private String creatorUser;
}