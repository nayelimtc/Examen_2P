package com.banquito.core.cardaccount.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.banquito.core.cardaccount.model.Branch;
import com.banquito.core.cardaccount.model.BranchHoliday;
import com.banquito.core.cardaccount.service.BranchService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/branches")
@RequiredArgsConstructor
@Tag(name = "Branch API", description = "API para gestionar sucursales bancarias")
public class BranchController {

    private final BranchService branchService;

    // 1.@GetMapping
    @Operation(summary = "Obtener todas las sucursales", description = "Retorna un listado de todas las sucursales registradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de sucursales obtenida exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<Branch>> getAllBranches() {
        return ResponseEntity.ok(branchService.findAll());
    }

    // 2.
    @GetMapping("/{id}")
    @Operation(summary = "Obtener sucursal por ID", description = "Busca y retorna una sucursal según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucursal encontrada"),
            @ApiResponse(responseCode = "404", description = "Sucursal no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<Branch> getBranchById(
            @Parameter(description = "ID de la sucursal", required = true) @PathVariable String id) {
        return ResponseEntity.ok(branchService.findById(id));
    }

    // 3.
    @PostMapping
    @Operation(summary = "Crear nueva sucursal", description = "Crea una nueva sucursal sin feriados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucursal creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de sucursal inválidos"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<Branch> createBranch(
            @Parameter(description = "Datos de la sucursal", required = true) @RequestBody Branch branch) {
        return ResponseEntity.ok(branchService.create(branch));
    }

    // 4.
    @PatchMapping("/{id}/phone")
    @Operation(summary = "Actualizar número de teléfono de una sucursal")
    public ResponseEntity<Branch> updatePhoneNumber(@PathVariable String id, @RequestBody String phoneNumber) {
        return ResponseEntity.ok(branchService.updatePhoneNumber(id, phoneNumber));
    }

    // 5.
    @PostMapping("/{id}/holidays")
    @Operation(summary = "Agregar feriado a una sucursal")
    public ResponseEntity<Branch> addHoliday(@PathVariable String id, @RequestBody BranchHoliday holiday) {
        return ResponseEntity.ok(branchService.addHoliday(id, holiday));
    }

    // 6.
    @DeleteMapping("/{id}/holidays")
    @Operation(summary = "Eliminar feriado de una sucursal")
    public ResponseEntity<Branch> removeHoliday(@PathVariable String id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(branchService.removeHoliday(id, date));
    }

    // 7.
    @GetMapping("/{id}/holidays")
    @Operation(summary = "Obtener todos los feriados de una sucursal")
    public ResponseEntity<List<BranchHoliday>> getHolidays(@PathVariable String id) {
        return ResponseEntity.ok(branchService.getHolidays(id));
    }

    // 8.
    @GetMapping("/{id}/holidays/check")
    @Operation(summary = "Verificar si una fecha es feriado en una sucursal")
    public ResponseEntity<Boolean> isHoliday(@PathVariable String id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(branchService.isHoliday(id, date));
    }
}