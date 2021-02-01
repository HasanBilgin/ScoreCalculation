package com.hasan.bilgin.scorecalculation.repository;

import com.hasan.bilgin.scorecalculation.model.Sms;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SmsRepository extends MongoRepository<Sms,String> {
}
