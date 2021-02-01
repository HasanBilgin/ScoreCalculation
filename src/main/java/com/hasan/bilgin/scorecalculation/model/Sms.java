package com.hasan.bilgin.scorecalculation.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "sms")
public class Sms {
    @Id
    private String id;
    private String phoneNumber;
    private String identityNo;
    private String messageText;
}
