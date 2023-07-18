package com.goit.spring.security.controller;

import com.goit.spring.security.dto.UserAccountDto;
import com.goit.spring.security.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(DemoController.BASE_PATH)
@RequiredArgsConstructor
public class DemoController {

    public static final String BASE_PATH = "/v1/users";

    private final UserAccountService userAccountService;

    @GetMapping
    @Secured({"ADMIN"})
    public List<UserAccountDto> loadAll() {
        log.info("/users invocation");
        return userAccountService.all();
    }

    @PostMapping
    @Secured({"ADMIN", "SUPER_ADMIN"})
    public UserAccountDto create(@RequestBody UserAccountDto dto) {
        return userAccountService.createUser(dto);
    }
}
