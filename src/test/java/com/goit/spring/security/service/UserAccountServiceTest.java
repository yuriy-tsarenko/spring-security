package com.goit.spring.security.service;

import com.goit.spring.security.dto.UserAccountDto;
import com.goit.spring.security.entity.UserAccountEntity;
import com.goit.spring.security.mapper.AccountMapper;
import com.goit.spring.security.repository.UserAccountRepository;
import com.goit.spring.security.test.utils.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.goit.spring.security.test.utils.TestUtils.PASSWORD_ENCODER;
import static com.goit.spring.security.test.utils.TestUtils.TEST_PASSWORD;
import static com.goit.spring.security.test.utils.TestUtils.TEST_USERNAME;
import static com.goit.spring.security.test.utils.TestUtils.createTestAccount;
import static com.goit.spring.security.test.utils.TestUtils.createTestAccountDto;
import static org.mockito.ArgumentMatchers.notNull;

@ExtendWith(MockitoExtension.class)
class UserAccountServiceTest {


    @Mock
    private UserAccountRepository userAccountRepository;

    private final AccountMapper accountMapper = Mockito.spy(new AccountMapper(null));
    private final PasswordEncoder passwordEncoder = Mockito.spy(TestUtils.PASSWORD_ENCODER);

    @InjectMocks
    private UserAccountService userAccountService;

    @Test
    void loadUserByUsername() {
        //GIVEN
        UserAccountEntity accountEntity = createTestAccount(TEST_USERNAME, TEST_PASSWORD);
        UserAccountDto expectedAccount = createTestAccountDto(accountEntity);
        //WHEN
        Mockito.when(userAccountRepository.findUserAccountEntityByUsername(TEST_USERNAME))
                .thenReturn(accountEntity);
        //THEN
        UserAccountDto actualAccount = userAccountService.loadUserByUsername(TEST_USERNAME);

        Assertions.assertEquals(expectedAccount.getUsername(), actualAccount.getUsername());
    }

    @Test
    void createUser() {
        //GIVEN
        UserAccountDto expectedAccountDto = createTestAccountDto(TEST_USERNAME, TEST_PASSWORD, "ADMIN");
        UserAccountEntity testAccount = createTestAccount(TEST_USERNAME, TEST_PASSWORD);
        //WHEN
        Mockito.when(userAccountRepository.save(notNull()))
                .thenReturn(testAccount);
        //THEN
        UserAccountDto actualAccountDto = userAccountService.createUser(expectedAccountDto);

        Assertions.assertEquals(expectedAccountDto.getUsername(), actualAccountDto.getUsername());
        Assertions.assertNotEquals(expectedAccountDto.getPassword(), actualAccountDto.getPassword());
        Assertions.assertTrue(PASSWORD_ENCODER.matches(TEST_PASSWORD, actualAccountDto.getPassword()));

        Mockito.verify(userAccountRepository).save(notNull());
    }

    @ParameterizedTest
    @CsvSource({"value1", "value2"})
    void test(String param) {
        System.out.println(param);
    }
}
