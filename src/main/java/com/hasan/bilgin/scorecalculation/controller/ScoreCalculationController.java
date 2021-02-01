package com.hasan.bilgin.scorecalculation.controller;

import com.hasan.bilgin.scorecalculation.model.Person;
import com.hasan.bilgin.scorecalculation.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScoreCalculationController {
    @Autowired
    private ScoreService service;

    @CrossOrigin
    @PostMapping(value = "/insert-score", produces = "application/json")
    public Person search(@RequestBody Person person) {
        return service.calculateScore(person);
    }

}
