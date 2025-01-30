package com.banquito.core.cardaccount.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.banquito.core.cardaccount.exception.BranchNotFoundException;
import com.banquito.core.cardaccount.model.Branch;
import com.banquito.core.cardaccount.model.BranchHoliday;
import com.banquito.core.cardaccount.repository.BranchRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BranchService {
    private static final Logger log = LoggerFactory.getLogger(BranchService.class);
    
    private final BranchRepository branchRepository;

    public List<Branch> findAll() {
        log.info("Iniciando búsqueda de todas las sucursales");
        try {
            List<Branch> branches = branchRepository.findAll();
            log.info("Se encontraron {} sucursales", branches.size());
            return branches;
        } catch (Exception e) {
            log.error("Error al obtener las sucursales", e);
            throw e;
        }
    }

    public Branch findById(String id) {
        log.info("Buscando sucursal con ID: {}", id);
        try {
            return branchRepository.findById(id)
                    .orElseThrow(() -> {
                        log.error("No se encontró la sucursal con ID: {}", id);
                        return new BranchNotFoundException("No se encontró la sucursal con ID: " + id);
                    });
        } catch (BranchNotFoundException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al buscar la sucursal con ID: {}", id, e);
            throw e;
        }
    }

    public Branch create(Branch branch) {
        log.info("Iniciando creación de nueva sucursal: {}", branch);
        try {
            if (branch.getBranchHolidays() == null) {
                branch.setBranchHolidays(new ArrayList<>());
            }
            branch.setCreationDate(LocalDateTime.now());
            branch.setLastModifiedDate(LocalDateTime.now());
            Branch savedBranch = branchRepository.save(branch);
            log.info("Sucursal creada exitosamente con ID: {}", savedBranch.getId());
            return savedBranch;
        } catch (Exception e) {
            log.error("Error al crear la sucursal: {}", e.getMessage(), e);
            throw e;
        }
    }

    public Branch updatePhoneNumber(String id, String phoneNumber) {
        Branch branch = findById(id);
        branch.setPhoneNumber(phoneNumber);
        branch.setLastModifiedDate(LocalDateTime.now());
        return branchRepository.save(branch);
    }

    public Branch addHoliday(String id, BranchHoliday holiday) {
        Branch branch = findById(id);
        if (branch.getBranchHolidays() == null) {
            branch.setBranchHolidays(new ArrayList<>());
        }
        branch.getBranchHolidays().add(holiday);
        return branchRepository.save(branch);
    }

    public Branch removeHoliday(String id, LocalDate date) {
        Branch branch = findById(id);
        branch.getBranchHolidays().removeIf(holiday -> holiday.getDate().equals(date));
        return branchRepository.save(branch);
    }

    public List<BranchHoliday> getHolidays(String id) {
        Branch branch = findById(id);
        return branch.getBranchHolidays();
    }

    public boolean isHoliday(String id, LocalDate date) {
        Branch branch = findById(id);
        return branch.getBranchHolidays().stream()
                .anyMatch(holiday -> holiday.getDate().equals(date));
    }
} 