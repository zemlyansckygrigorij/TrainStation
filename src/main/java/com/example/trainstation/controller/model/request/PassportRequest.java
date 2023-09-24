package com.example.trainstation.controller.model.request;

import com.example.trainstation.entities.Passport;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Schema(description = "Данные студента")
@Data
@Getter
@AllArgsConstructor
public class PassportRequest {
    @Schema(description = "Идентификатор студента")
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

    public Passport passportBuider() throws Exception {
        return new Passport(
              number.intValue(), type, boxingWeight, loadCapacity);
    }
}
