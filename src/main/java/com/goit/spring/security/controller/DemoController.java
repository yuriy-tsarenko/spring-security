package com.goit.spring.security.controller;

import jakarta.servlet.ServletRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class DemoController {

    @GetMapping("/users")
    @Secured({"ADMIN"})
    public String users(ServletRequest request, Authentication authentication) {

        return "admin@email.com";
    }

    @GetMapping("/users/super-admin")
    @Secured({"USER", "SUPER_ADMIN"})
    public String usersSuperAdmin(ServletRequest request, Authentication authentication) {

        return "super_admin@email.com";
    }
}
