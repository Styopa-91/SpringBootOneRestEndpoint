package com.example.demo.controller;

import com.example.demo.DemoApplication;
import com.example.demo.service.PeopleService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;



@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = DemoApplication.class)
@AutoConfigureMockMvc
class PeopleControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private PeopleService service;

    @Test
    void getPeopleById() throws Exception {
        mvc.perform(get("/api/people/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("firstName", is("Petro")))
                .andExpect(jsonPath("lastName", is("Ivanov")))
                .andExpect(jsonPath("age", is(32)));

        mvc.perform(get("/api/people/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("firstName", is("Volodymyr")))
                .andExpect(jsonPath("lastName", is("Ivanov")))
                .andExpect(jsonPath("age", is(31)));

        mvc.perform(get("/api/people/3")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("firstName", is("Vasyl")))
                .andExpect(jsonPath("lastName", is("Ivanov")))
                .andExpect(jsonPath("age", is(30)));
    }
}