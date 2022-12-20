package com.example.demo.service;

import com.example.demo.dto.PeopleItem;
import com.example.demo.model.People;
import com.example.demo.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Calendar;
import java.util.Optional;

@Service
public class PeopleService {

    @Autowired
    PeopleRepository peopleRepository;

    public ResponseEntity<PeopleItem> findById(Long id)
    {
        Optional<People> peopleData = peopleRepository.findById(id);

        if (peopleData.isPresent()) {
            Date birthDate = peopleData.get().getBirth();
            Calendar dOb = Calendar.getInstance();
            Calendar today = Calendar.getInstance();
            dOb.setTime(birthDate);
            dOb.add(Calendar.DAY_OF_MONTH, -1);

            int age = today.get(Calendar.YEAR) - dOb.get(Calendar.YEAR);

            if (today.get(Calendar.DAY_OF_YEAR) <= dOb.get(Calendar.DAY_OF_YEAR)) age--;

            PeopleItem p = new PeopleItem();
            p.setFirstName(peopleData.get().getFirstName());
            p.setLastName(peopleData.get().getLastName());
            p.setAge(age);

            return new  ResponseEntity<>(p, HttpStatus.OK);
        } else {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
