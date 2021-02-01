package com.hasan.bilgin.scorecalculation;

import com.hasan.bilgin.scorecalculation.model.Person;
import com.hasan.bilgin.scorecalculation.service.ScoreService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
class ScorecalculationApplicationTests {
    @Autowired
    private ScoreService scoreService;

    @Test
    void fullNameControl() {
        Person p = new Person();
        p = scoreService.calculateScore(p);
        Assert.assertNotEquals("Ad Soyad bilgisi zorunludur.", p.getFullName());
    }

    @Test
    void identityNoControl() {
        Person p = new Person();
        p.setFullName("Hasan Bilgin");
        p = scoreService.calculateScore(p);
        Assert.assertNotEquals("Kimlik numarası bilgisi zorunludur.", p.getIdentityNo());
    }

    @Test
    void identityNoControl2() {
        Person p = new Person();
        p.setFullName("Hasan Bilgin");
        p.setIdentityNo("1234567890");
        p = scoreService.calculateScore(p);
        Assert.assertNotEquals("Kimlik numarası bilgisi zorunludur.", p.getIdentityNo());
    }

    @Test
    void phoneNumberControl() {
        Person p = new Person();
        p.setFullName("Hasan Bilgin");
        p.setIdentityNo("12345678901");
        p = scoreService.calculateScore(p);
        Assert.assertNotEquals("Telefon numarası bilgisi zorunludur.", p.getPhoneNumber());
    }

    @Test
    void phoneNumberControl2() {
        Person p = new Person();
        p.setFullName("Hasan Bilgin");
        p.setIdentityNo("123456789");
        p.setPhoneNumber("123456789");
        p = scoreService.calculateScore(p);
        Assert.assertNotEquals("Telefon numarası bilgisi zorunludur.", p.getPhoneNumber());
    }

    @Test
    void incomeTrancheControl() {
        Person p = new Person();
        p.setFullName("Hasan Bilgin");
        p.setIdentityNo("12345678901");
        p.setPhoneNumber("1234567890");
        p = scoreService.calculateScore(p);
        Assert.assertNotEquals("Aylık gelir dilimi seçiniz.", p.getFullName());
    }

    @Test
    void incomeTrancheControl2() {
        Person p = new Person();
        p.setFullName("Hasan Bilgin");
        p.setIdentityNo("12345678901");
        p.setPhoneNumber("1234567890");
        p = scoreService.calculateScore(p);
        Assert.assertNotEquals("Aylık gelir dilimi seçiniz.", p.getFullName());
    }

    @Test
    void cityControl() {
        Person p = new Person();
        p.setFullName("Hasan Bilgin");
        p.setIdentityNo("12345678901");
        p.setPhoneNumber("1234567890");
        p.setIncomeTranche("800");
        p = scoreService.calculateScore(p);
        Assert.assertNotEquals("İkamet ettiği il seçiniz.", p.getFullName());
    }
}
