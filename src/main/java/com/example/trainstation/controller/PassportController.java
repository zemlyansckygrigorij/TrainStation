package com.example.trainstation.controller;

import com.example.trainstation.controller.model.request.PassportRequest;
import com.example.trainstation.controller.model.response.PassportResponse;
import com.example.trainstation.db.services.PassportComponent;
import com.example.trainstation.entities.Passport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class  PassportController
 * для работы с web сайтом /passports
 */
@RestController
@Validated
@Tag(
        name = "API работы с паспортами грузов",
        description = "Api work with passports"
)
@RequestMapping("/passports")
@RequiredArgsConstructor
public class PassportController {
    private final PassportComponent passportComponent;

    @Operation(
            description = "Получение всех паспортов",
            summary = "Retrieve all passports",
            hidden = false
    )
    @ApiResponse(responseCode = "200", description = "list of PassportResponse")
    @GetMapping
    public List<PassportResponse> getAll(){
        return passportComponent
                .getAll()
                .stream()
                .map((p)->new PassportResponse(p))
                .collect(Collectors.toList());
    }

    @Operation(
            description = "Получение паспорта по идентификатору ",
            hidden = false
    )
    @ApiResponse(responseCode = "200", description = "PassportResponse")
    @GetMapping("/id")
    public PassportResponse findById(@PathVariable(name = "id") final long id) throws Exception{
        return new PassportResponse(passportComponent.findByIdOrDie(id));
    }

    @Operation(
            description = "Вставка паспорта в таблицу",
            summary = "insert passport",
            hidden = false
    )
    @ApiResponse(responseCode = "200", description = "PassportResponse")
    @PostMapping
    public PassportResponse create(@RequestBody PassportRequest passportRequest) throws Exception{
        return new PassportResponse(passportComponent.save(passportRequest.passportBuider()));
    }

    @Operation(
            description = "Удаление паспорта из таблицы",
            summary = "delete passport",
            hidden = false
    )
    @ApiResponse(responseCode = "200", description = "PassportResponse")
    @DeleteMapping("/{id}")
    public PassportResponse deleteById(
            @PathVariable(name = "id") final long id
    ) throws Exception {
        return new PassportResponse(passportComponent.deleteById(id));
    }

    @Operation(
            description = "Обновление паспорта",
            summary = "update passport",
            hidden = false
    )
    @ApiResponse(responseCode = "200", description = "list of OwnerResponse")
    @PutMapping("/{id}")
    public void update(@RequestBody PassportRequest passportRequest,
                       @PathVariable(name = "id") String id) throws Exception {
        if(!isNumeric(id)) return;

        passportComponent.update(passportRequest.passportBuider(), Long.getLong(id));
    }

    public boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Long d = Long.getLong(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}


















