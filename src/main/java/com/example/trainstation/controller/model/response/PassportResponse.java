package com.example.trainstation.controller.model.response;
import com.example.trainstation.entities.Passport;
import com.example.trainstation.entities.Type;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;


/**
 * Данные полученные с контроллера о студенте.
 */
@Schema(description = "Данные студента")
@Data
@Getter
@AllArgsConstructor
public class PassportResponse {
    @Schema(description = "Идентификатор груза")
    @JsonProperty("id")
    private Long id;

    @Schema(description = "номер")
    @JsonProperty("serial_number")
    private Long number;

    @Schema(description = "Имя студента")
    @JsonProperty("type")
    private String type;

    @Schema(description = "Имя студента")
    @JsonProperty("boxing_weight")
    private double boxingWeight;

    @Schema(description = "Имя студента")
    @JsonProperty("load_capacity")
    private double loadCapacity;

    public PassportResponse(Passport passport) {
        this.id = passport.getId();
        this.number = (long) passport.getNumber();
        this.type= passport.getType().toString();
        this.loadCapacity = passport.getLoadCapacity();
        this.boxingWeight = passport.getBoxingWeight();
    }
}



