package com.hasan.bilgin.scorecalculation.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "person")
@ToString
public class Person {
    @Id
    private String id;
    private String identityNo;
    private String fullName;
    private String phoneNumber;
    private String incomeTranche;
    private String city;
    private String errorMessage;
    private int score;
}
