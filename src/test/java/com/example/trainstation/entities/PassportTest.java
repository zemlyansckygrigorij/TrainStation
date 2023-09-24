package com.example.trainstation.entities;

import com.example.trainstation.db.repo.PassportRepo;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration
@Transactional
@DisplayName("Unit-тесты на создание обьекта - паспорт вагона.")
class PassportTest {
    @Autowired
    PassportRepo repo;
    @DisplayName("1. Проверка вставки паспорт вагона.")
    @Test
    void savePassport() throws Exception {
        Passport passport = new Passport(
        3,
        "AUTOTRACK",
        2.3,
        3.4);
        Passport passportfromTable =repo
                .findById(
                        repo.save(passport).getId()
                ).orElseThrow(()->new Exception()) ;

        assertEquals(passportfromTable.getNumber(),3);
        assertEquals(passportfromTable.getBoxingWeight(),2.3);
        assertEquals(passportfromTable.getLoadCapacity(),3.4);
        assertEquals(passportfromTable.getType(),Type.AUTOTRACK);
    }

    @DisplayName("2. Проверка вставки паспорта без данных.")
    @Test
    void savePassportWithFailNumber() throws Exception {
        Passport passport = new Passport();
        assertThrows(DataIntegrityViolationException.class, ()-> repo.save(passport));
    }
}
//CargoClassification