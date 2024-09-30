package com.takarub.ecommerce.controller;

import com.takarub.ecommerce.service.RoleServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleServices services;

    @PutMapping("/assign/users/{userId}")
    public ResponseEntity<?> AssignRole(@PathVariable String userId,
                                        @RequestParam String roleName ) {
        services.assignRole(userId, roleName);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/remove/users/{userId}")
    public ResponseEntity<?> unAssignRole(@PathVariable String userId,
                                          @RequestParam String roleName ) {
        services.deleteRoleFromUser(userId, roleName);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
