package com.example.trainstation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TrainStationApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrainStationApplication.class, args);
    }

}
/*
* В системе должны быть реализованы следующие справочники:
•	Паспорт вагонов Passport carriage (Номер Number, Тип Type, Вес тары boxing weight, Грузоподъемность load capacity)
•	Станционная модель station model(Станции Station, Пути станций Station tracks)
•	Натурный лист для приема вагонов wagon list (Список вагонов с атрибутами: Порядковый номер, Номер вагона, Номенклатура груза, Вес груза в вагоне, Вес вагона)
•	Справочник номенклатур грузов cargo classification(Код груза, Наименование груза)

Для каждой сущности должен быть реализован Rest Controller содержащий все CRUD операции.

* */