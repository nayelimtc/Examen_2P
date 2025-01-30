package com.banquito.core.branch.controller;

import com.banquito.core.branch.model.Branch;
import com.banquito.core.branch.model.BranchHoliday;
import com.banquito.core.branch.service.BranchService;
// ... resto de imports ...

@RestController
@RequestMapping("/api/v1/branches")
@RequiredArgsConstructor
@Tag(name = "Branch API", description = "API para gestionar sucursales bancarias")
public class BranchController {
    // ... resto del código se mantiene igual ...
} 