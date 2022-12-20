package com.example.demo.controller;

import com.example.demo.dto.PeopleItem;
import com.example.demo.model.People;
import com.example.demo.repository.PeopleRepository;
import com.example.demo.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class PeopleController {

    @Autowired
    PeopleService peopleService;
    @Autowired
    PeopleRepository peopleRepository;

    @GetMapping("/people/{id}")
    public ResponseEntity<PeopleItem> getPeopleById(@PathVariable("id") long id) {
      return peopleService.findById(id);
    }

}
