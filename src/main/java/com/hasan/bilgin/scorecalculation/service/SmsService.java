package com.hasan.bilgin.scorecalculation.service;

import com.hasan.bilgin.scorecalculation.model.Person;
import com.hasan.bilgin.scorecalculation.model.Sms;
import com.hasan.bilgin.scorecalculation.repository.SmsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SmsService {
@Autowired
private SmsRepository repository;
    protected boolean sendSms(Person p)    {
        try {
            Sms sms = new Sms();
            sms.setIdentityNo(p.getIdentityNo());
            sms.setPhoneNumber(p.getPhoneNumber());
            if (p.getErrorMessage() != null && !p.getErrorMessage().isEmpty())
                sms.setMessageText("Skor hesaplama sırasında hata alındı.");
            else
                sms.setMessageText("Skorunuz : " + p.getScore());
            repository.save(sms);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
