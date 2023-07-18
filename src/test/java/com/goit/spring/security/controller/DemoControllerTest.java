package com.goit.spring.security.controller;

import com.goit.spring.security.configuration.UserAuthority;
import com.goit.spring.security.dto.UserAccountDto;
import com.goit.spring.security.role.RoleTypes;
import com.goit.spring.security.service.UserAccountService;
import com.goit.spring.security.test.utils.TestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.test.context.TestSecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.goit.spring.security.role.RoleTypes.ADMIN;
import static com.goit.spring.security.test.utils.TestUtils.OBJECT_MAPPER;
import static com.goit.spring.security.test.utils.TestUtils.TEST_PASSWORD;
import static com.goit.spring.security.test.utils.TestUtils.TEST_USERNAME;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@EnableMethodSecurity(securedEnabled = true)
@WebMvcTest(value = DemoController.class)
class DemoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserAccountService userAccountService;

    @ParameterizedTest
    @EnumSource(RoleTypes.class)
    @DisplayName("check is authorization works correct")
    void loadAll(RoleTypes roleType) throws Exception {
        doAuthenticationWithRoles(roleType);
        //GIVEN
        UserAccountDto accountDto = TestUtils.createTestAccountDto(TEST_USERNAME, TEST_PASSWORD, roleType.name());
        List<UserAccountDto> testAccountDtoList = List.of(accountDto);
        //WHEN
        when(userAccountService.all())
                .thenReturn(testAccountDtoList);
        when(userAccountService.loadUserByUsername(notNull()))
                .thenReturn(accountDto);
        //THEN
        if (roleType.equals(ADMIN)) {
            mockMvc.perform(get(DemoController.BASE_PATH))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().json(OBJECT_MAPPER.writeValueAsString(testAccountDtoList)))
                    .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
        } else {
            mockMvc.perform(get(DemoController.BASE_PATH))
                    .andExpect(MockMvcResultMatchers.status().isForbidden());
        }
    }

    private static void doAuthenticationWithRoles(RoleTypes... roleTypes) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                TEST_USERNAME,
                TEST_PASSWORD,
                Arrays.stream(roleTypes)
                        .map(RoleTypes::name)
                        .map(UserAuthority::new)
                        .collect(Collectors.toList())
        );
        TestSecurityContextHolder.setAuthentication(authentication);
    }
}
