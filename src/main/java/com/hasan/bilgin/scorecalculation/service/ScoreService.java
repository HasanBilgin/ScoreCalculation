package com.hasan.bilgin.scorecalculation.service;

import com.hasan.bilgin.scorecalculation.model.Person;
import com.hasan.bilgin.scorecalculation.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class ScoreService {
    @Autowired
    private PersonRepository repository;
    @Autowired
    private SmsService smsService;

    // skor = skor segmenti * gelir dilim çarpanı + şehir skoru
    public Person calculateScore(Person person) {
        Person data;
        try {
            person = controlPerson(person);
            if (person.getErrorMessage() != null && !person.getErrorMessage().isEmpty())
                return person;

            final int segment = segment();
            final int cityScore = cityScore();
            if (segment < 0) {
                person.setErrorMessage("Segment Hesaplamada hata");
                return person;
            }
            if (cityScore < 0) {
                person.setErrorMessage("Şehir skoru hesaplamada hata");
                return person;
            }
            final int score = segment * Integer.parseInt(person.getIncomeTranche()) + cityScore;
            data = repository.findById(person.getIdentityNo()).orElse(null);
            if (data == null) {
                data = person;
                data.setId(person.getIdentityNo());
            }
            data.setScore(score);
//            data.setErrorMessage("Hata mesajı test");
            repository.save(data);

            try {
                smsService.sendSms(data);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return data;
        } catch (Exception e) {
            person.setErrorMessage("Skor hesaplama sırasında hata alındı.");
            return person;
        }
    }

    private int segment() {
        return new Random().ints(1, 9).findFirst().orElse(-1);
    }

    private int cityScore() {
        return new Random().ints(0, 20000).findFirst().orElse(-1);
    }

    private Person controlPerson(Person p) {
        if (p.getFullName() == null || p.getFullName().isEmpty()) {
            p.setErrorMessage("Ad Soyad bilgisi zorunludur.");
            return p;
        } else if (p.getIdentityNo() == null || p.getIdentityNo().isEmpty() || p.getIdentityNo().length() < 11) {
            p.setErrorMessage("Kimlik numarası bilgisi zorunludur.");
            return p;
        } else if (p.getPhoneNumber() == null || p.getPhoneNumber().isEmpty() || p.getPhoneNumber().length() < 10) {
            p.setErrorMessage("Telefon numarası bilgisi zorunludur.");
            return p;
        } else if (p.getIncomeTranche() == null || p.getFullName().isEmpty()) {
            p.setErrorMessage("Aylık gelir dilimi seçiniz.");
            return p;
        }
        if (p.getCity() == null || p.getCity().isEmpty()) {
            p.setErrorMessage("İkamet ettiği il seçiniz.");
            return p;
        }
        return p;
    }
}
