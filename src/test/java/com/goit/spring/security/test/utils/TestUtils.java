package com.goit.spring.security.test.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goit.spring.security.configuration.UserAuthority;
import com.goit.spring.security.dto.UserAccountDto;
import com.goit.spring.security.entity.UserAccountEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;


public final class TestUtils {

    public static final String TEST_USERNAME = "test@gmail.com";
    public static final String TEST_PASSWORD = "12345";

    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder(10);
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();


    public static UserAccountDto createTestAccountDto(UserAccountEntity accountEntity) {
        UserAccountDto dto = new UserAccountDto();
        dto.setUsername(accountEntity.getUsername());
        return dto;
    }

    public static UserAccountDto createTestAccountDto(String testUsername, String password, String roleName) {
        UserAccountDto dto = new UserAccountDto();
        dto.setUsername(testUsername);
        dto.setPassword(password);
        dto.setIsAccountNonExpired(true);
        dto.setIsAccountNonLocked(true);
        dto.setIsCredentialsNonExpired(true);
        dto.setIsEnabled(true);
        dto.setAuthorities(List.of(new UserAuthority(roleName)));
        return dto;
    }

    public static UserAccountEntity createTestAccount(String testUsername, String password) {
        UserAccountEntity accountEntity = new UserAccountEntity();
        accountEntity.setId(1L);
        accountEntity.setUsername(testUsername);
        accountEntity.setPassword(PASSWORD_ENCODER.encode(password));
        return accountEntity;
    }
}
