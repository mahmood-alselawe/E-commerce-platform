package com.takarub.ecommerce.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rbaca")
public class RBAICController {

    @GetMapping("/merge-role")
    @PreAuthorize("hasRole('USER')")
    public String test(){
        return "test";
    }
}
