package com.example.trainstation.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PassportControllerTest {

    @Test
    void getAll() throws Exception {

    }

    @Test
    void findById() {
    }

    @Test
    void create() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void update() {
    }

    @Test
    void isNumeric() {
    }
}