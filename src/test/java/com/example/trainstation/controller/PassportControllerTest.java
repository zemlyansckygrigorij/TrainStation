package com.example.trainstation.controller;

import com.example.trainstation.db.services.PassportComponent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import org.springframework.http.MediaType;


@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PassportControllerTest {
    @Autowired
    PassportComponent passportComponent;
    @LocalServerPort
    private int port;
    @Autowired
    private MockMvc mockMvc;
    private String passportBody = """
            {
                "serial_number": 51,
                "type": "AUTOTRACK",
                "boxing_weight": 51.3,
                "load_capacity": 61.4
            }
            """;

    @Test
    void getAll() throws Exception {
        mockMvc.perform(get("http://localhost:" + port + "/passports"))
                .andExpect(jsonPath("$", hasSize(20)));
    }

    @Test
    void findById() throws Exception {
        long id = passportComponent.getAll()
                .stream()
                .mapToLong(p -> p.getId())
                .min()
                .orElseThrow();
        this.mockMvc.perform(get("/passports/"+id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is((int)id)))
                .andExpect(jsonPath("$.serial_number", is(2)))
                .andExpect(jsonPath("$.type", is("AUTOTRACK")))
                .andExpect(jsonPath("$.boxing_weight", is(2.3)))
                .andExpect(jsonPath("$.load_capacity", is(3.4)));
    }
    @Test
    void createWithoutAuthentication() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/passports")
                        .content(passportBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }
}