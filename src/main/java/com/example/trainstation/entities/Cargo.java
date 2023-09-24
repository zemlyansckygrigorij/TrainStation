package com.example.trainstation.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor()
@Table(name = "cargo", schema = "public")
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "code", nullable = false)
    private String code;
    @Column(name = "name_cargo", nullable = false)
    private String nameCargo;
    public Cargo(String code, String nameCargo){
        this.code = code;
        this.nameCargo = nameCargo;
    }
}
