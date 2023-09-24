package com.example.trainstation.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * Паспорт вагонов (Номер, Тип, Вес тары, Грузоподъемность)
 */
@Getter
@Entity
@EqualsAndHashCode
@Table(name = "Passport", schema = "public")
@NoArgsConstructor()
public class Passport {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   @Column(name = "serial_number", nullable = false)
   private int number;
   @Column(name = "type", nullable = false)
   @Enumerated(EnumType.STRING)
   private Type type;
   @Column(name = "boxing_weight", nullable = false)
   private double boxingWeight;
   @Column(name = "load_capacity", nullable = false)
   private double loadCapacity;

   public Passport(
           int number,
           String type,
           double boxingWeight,
           double loadCapacity) throws Exception {
      this.number = number;
      this.type =  Type.fromText(type).orElseThrow(() -> new Exception());
      this.boxingWeight = boxingWeight;
      this.loadCapacity = loadCapacity;
   }

}

/*
   CREATE TABLE public.passport
        (
        id serial NOT NULL,
        serial_number integer NOT NULL,
        type character varying(25) COLLATE pg_catalog."default" NOT NULL,
        boxing_weight double precision NOT NULL,
        load_capacity double precision NOT NULL,
        CONSTRAINT passport_pkey PRIMARY KEY (id)
        )

        TABLESPACE pg_default;

        ALTER TABLE public.passport
        OWNER to postgres;
*/