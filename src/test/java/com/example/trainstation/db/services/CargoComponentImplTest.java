package com.example.trainstation.db.services;

import com.example.trainstation.entities.Cargo;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration
@NoArgsConstructor
@Transactional
@DisplayName("Unit-тесты на CRUD номенклатуры грузов.")
class CargoComponentImplTest {
    @Autowired
    CargoComponent cargoComponent;
    @DisplayName("1. Проверка вставки номенклатуры груза.")
    @Test
    void save() throws Exception {
        Cargo cargo = new Cargo("011005","Пшеница");
        Cargo cargoFromTable = cargoComponent
                .findById(cargoComponent.save(cargo).getId()) ;
        assertEquals(cargoComponent.getAll().size(),9);
        assertEquals(cargoFromTable.getCode(),"011005");
        assertEquals(cargoFromTable.getNameCargo(),"Пшеница");
        cargoComponent.deleteById(cargoFromTable.getId());
    }
    @DisplayName("2. Проверка получения всех номенклатур грузов.")
    @Test
    void getAll() {
        assertEquals(cargoComponent.getAll().size(),8);
    }
    @DisplayName("3. Проверка получения номенклатуры груза по идентификатору.")
    @Test
    void findById() throws Exception {
        long id = cargoComponent.getAll()
                .stream()
                .mapToLong(c-> c.getId())
                .min()
                .orElseThrow();
        Cargo cargo = cargoComponent.findById(id);
        assertEquals(cargo.getId(),id);
        assertEquals(cargo.getCode(),"011005");
        assertEquals(cargo.getNameCargo(), "ПШЕНИЦА");
    }
    @DisplayName("4. Проверка обновления номенклатуры груза.")
    @Test
    @Transactional(propagation = Propagation.SUPPORTS)
    void update() throws Exception {
        Cargo cargo = new Cargo("011005","Пшеница");
        Cargo cargoFromTable = cargoComponent
                .findById(cargoComponent.save(cargo).getId()) ;
        assertEquals(cargoComponent.getAll().size(),9);
        assertEquals(cargoFromTable.getCode(),"011005");
        assertEquals(cargoFromTable.getNameCargo(),"Пшеница");

        Cargo cargoUpdate =  new Cargo("018131","ЧЕЧЕВИЦА");
        long id = cargoFromTable.getId();
        cargoComponent.update(cargoUpdate,id);
        Cargo cargoFromTableUpdate = cargoComponent.findById(id) ;
        assertEquals(cargoFromTableUpdate.getCode(),"018131");
        assertEquals(cargoFromTableUpdate.getNameCargo(),"ЧЕЧЕВИЦА");
        cargoComponent.deleteById(id);
    }
    @DisplayName("5. Проверка удаления номенклатуры груза.")
    @Test
    void deleteById() throws Exception {
        int count = cargoComponent.getAll().size();
        long id = cargoComponent.getAll()
                .stream()
                .mapToLong(c -> c.getId())
                .min()
                .orElseThrow();
        cargoComponent.deleteById(id);
        assertEquals(cargoComponent.getAll().size(),count-1);
    }
}