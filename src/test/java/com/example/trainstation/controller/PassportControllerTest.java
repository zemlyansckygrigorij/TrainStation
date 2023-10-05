package com.example.trainstation.controller;

import com.example.trainstation.TrainStationApplication;
import com.example.trainstation.db.services.PassportComponent;
import com.example.trainstation.entities.Passport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import org.springframework.http.MediaType;
import java.net.MalformedURLException;
import java.time.Duration;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@DisplayName("тесты на web-part паспорта вагона.")
public class PassportControllerTest {
    @Autowired
    PassportComponent passportComponent;
    @LocalServerPort
    private int port;
    @Autowired
    private MockMvc mockMvc;
    private final String passportBody = """
            {
                "serial_number": 519,
                "type": "AUTOTRACK",
                "boxing_weight": 519.3,
                "load_capacity": 619.4
            }
            """;
    private String passportByIdJsonStart = """
            {"id":
            """.trim();
    private String passportByIdJsonEnd = """
            ,"serial_number":2,"type":"AUTOTRACK","boxing_weight":2.3,"load_capacity":3.4}
            """.trim();
    private String passportBodyUpdate = """
            {
                "serial_number": 5119,
                "type": "BOXCAR",
                "boxing_weight": 5119.3,
                "load_capacity": 6119.4
            }
            """;


    @DisplayName("1. Проверка получения json паспорта вагона по идентификатору.")
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
    @DisplayName("2.Общий тест на получение вставку обновление и удаление.")
    @Test
    void commonTest() throws Exception {

        int count = passportComponent.getAll().size();
        mockMvc.perform(get("http://localhost:" + port + "/passports"))
                .andExpect(jsonPath("$", hasSize(count)));

        MvcResult result =mockMvc.perform(MockMvcRequestBuilders
                        .post("/passports")
                        .content(passportBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.serial_number", is(519)))
                .andExpect(jsonPath("$.type", is("AUTOTRACK")))
                .andExpect(jsonPath("$.boxing_weight", is(519.3)))
                .andExpect(jsonPath("$.load_capacity", is(619.4)))
                .andReturn();

        int idResult = Integer.parseInt(result
                .getResponse()
                .getContentAsString()
                .split(",")[0].split(":")[1]);
        this.mockMvc.perform(get("/passports/" + idResult))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(idResult)))
                .andExpect(jsonPath("$.serial_number", is(519)))
                .andExpect(jsonPath("$.type", is("AUTOTRACK")))
                .andExpect(jsonPath("$.boxing_weight", is(519.3)))
                .andExpect(jsonPath("$.load_capacity", is(619.4)));

        Passport passport =  passportComponent.findByIdOrDie(Long.valueOf(idResult));

        assertNotNull(passport);
        assertEquals("AUTOTRACK", passport.getType().toString());
        assertEquals(519.3,passport.getBoxingWeight());
        assertEquals(519, passport.getNumber());
        assertEquals(619.4, passport.getLoadCapacity());
        assertEquals(idResult, passport.getId());

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/passports/"+idResult)
                        .content(passportBodyUpdate)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Passport passportUpdate =  passportComponent.findByIdOrDie(Long.valueOf(idResult));

        assertNotNull(passportUpdate);
        assertEquals("BOXCAR",  passportUpdate.getType().toString());
        assertEquals(5119.3, passportUpdate.getBoxingWeight());
        assertEquals(5119,  passportUpdate.getNumber());
        assertEquals(6119.4,  passportUpdate.getLoadCapacity());
        assertEquals(idResult,  passportUpdate.getId());

        assertEquals(passportComponent.getAll().size(),count+1);
        passportComponent.deleteById((long) idResult);
        assertEquals(passportComponent.getAll().size(),count);
    }
    @DisplayName("3.Тест Selenium на тело ответа.")
    @Test
    void seleniumTest() throws MalformedURLException {
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\cnk-1\\IdeaProjects\\TrainStation\\src\\main\\resources\\chromedriver-win64\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--start-maximized");

        WebDriver driver =  new ChromeDriver(options);
        String[] args = {"",""};
        TrainStationApplication.main(args);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));//неявное ожидание
        long id = passportComponent.getAll()
                .stream()
                .mapToLong(p -> p.getId())
                .min()
                .orElseThrow();
        driver.get("http://127.0.0.1:8080/passports/"+id);
        WebElement pre = driver.findElement(By.tagName("pre"));
        assertEquals(pre.getText(),passportByIdJsonStart+id+passportByIdJsonEnd);
        driver.quit();
    }
}