package com.example.trainstation.db.services;

import com.example.trainstation.entities.Passport;
import com.example.trainstation.entities.Type;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration
@NoArgsConstructor
@Transactional
@DisplayName("Unit-тесты на CRUD паспорта вагона.")
public class PassportComponentImplTest {
    @Autowired
    PassportComponent passportComponent;

    @DisplayName("1. Проверка вставки паспорт вагона.")
    @Test
    public void save() throws Exception {
        Passport passport = new Passport(
                3,
                "AUTOTRACK",
                2.3,
                3.4);
        Passport passportfromTable =passportComponent
                .findByIdOrDie(passportComponent.save(passport).getId()) ;
        assertEquals(passportComponent.getAll().size(),21);
        assertEquals(passportfromTable.getNumber(),3);
        assertEquals(passportfromTable.getBoxingWeight(),2.3);
        assertEquals(passportfromTable.getLoadCapacity(),3.4);
        assertEquals(passportfromTable.getType(),Type.AUTOTRACK);
        passportComponent.deleteById( passportfromTable.getId());
    }

    @DisplayName("2. Проверка получения всех паспортов вагонов.")
    @Test
    public void getAll() {
        List<Passport> passports = passportComponent.getAll();
        assertEquals(passports.size(),20);
    }

    @DisplayName("3. Проверка получения паспорта вагона по идентификатору.")
    @Test
    public void findById() throws Exception {
        long id = passportComponent.getAll()
                .stream()
                .mapToLong(p -> p.getId())
                .min()
                .orElseThrow();
        Passport passport = passportComponent.findByIdOrDie(id);
        assertEquals(passport.getId(),id);
        assertEquals(passport.getNumber(),2);
        assertEquals(passport.getType(), Type.AUTOTRACK);
        assertEquals(passport.getBoxingWeight(),2.3);
        assertEquals(passport.getLoadCapacity(),3.4);
    }

    @DisplayName("4. Проверка обновления паспорт вагона.")
    @Test
    @Transactional(propagation = Propagation.SUPPORTS)
    public void update() throws Exception {
        Passport passportSave = new Passport(
                323,
                "AUTOTRACK",
                212.3,
                13.4);
        Passport passportGet =passportComponent.findByIdOrDie(passportComponent.save(passportSave).getId());
        assertEquals(passportGet.getNumber(),323);
        assertEquals(passportGet.getType(), Type.AUTOTRACK);
        assertEquals(passportGet.getBoxingWeight(),212.3);
        assertEquals(passportGet.getLoadCapacity(),13.4);

        Passport passport = new Passport(
                123,
                "TANKCAR",
                123.3,
                312.4);
        long id = passportGet.getId();
        passportComponent.update(passport, id);
        Passport passportFromTable = passportComponent.findByIdOrDie(id);
        assertEquals(passportFromTable.getNumber(),123);
        assertEquals(passportFromTable.getType(), Type.TANKCAR);
        assertEquals(passportFromTable.getBoxingWeight(),123.3);
        assertEquals(passportFromTable.getLoadCapacity(),312.4);
        passportComponent.deleteById(id);
    }

    @DisplayName("5. Проверка удаления паспорт вагона.")
    @Test
    public void deleteById() throws Exception {
        int count = passportComponent.getAll().size();
        long id = passportComponent.getAll()
                .stream()
                .mapToLong(p -> p.getId())
                .min()
                .orElseThrow();
        passportComponent.deleteById(id);
        assertEquals(passportComponent.getAll().size(),count-1);
    }
}