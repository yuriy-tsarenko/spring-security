package com.goit.spring.security.service;

import com.goit.spring.security.configuration.UserAuthority;
import com.goit.spring.security.dto.UserAccountDto;
import com.goit.spring.security.entity.UserAccountEntity;
import com.goit.spring.security.mapper.AccountMapper;
import com.goit.spring.security.repository.UserAccountRepository;
import com.goit.spring.security.role.RoleTypes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

import static com.goit.spring.security.ulils.Constants.ADMIN_PASSWORD;
import static com.goit.spring.security.ulils.Constants.ADMIN_USERNAME;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserAccountService implements UserDetailsService {

    private final UserAccountRepository userAccountRepository;
    private final AccountMapper accountMapper;
    private final PasswordEncoder passwordEncoder;

    @Value(value = "${" + ADMIN_USERNAME + "}")
    private String username;
    @Value(value = "${" + ADMIN_PASSWORD + "}")
    private String password;

    @PostConstruct
    private void setup() {
        log.info("started service:{} setup", UserAccountService.class.getSimpleName());
        UserAccountDto accountDto = UserAccountDto.of(
                username,
                passwordEncoder.encode(password),
                null,
                true,
                true,
                true,
                true,
                Arrays.stream(RoleTypes.values())
                        .map(RoleTypes::name)
                        .map(UserAuthority::new)
                        .toList());
        userAccountRepository.save(accountMapper.mapDtoToEntity(accountDto));
        log.info("service:{} setup finished", UserAccountService.class.getSimpleName());

    }

    @Override
    public UserAccountDto loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccountEntity userAccountEntityByUsername = userAccountRepository.findUserAccountEntityByUsername(username);
        return accountMapper.mapEntityToDto(userAccountEntityByUsername);
    }

    public UserAccountDto createUser(UserDetails user) {
        UserAccountEntity userAccountEntity = accountMapper.mapDtoToEntity((UserAccountDto) user);
        encodePassword(userAccountEntity);
        UserAccountEntity saved = userAccountRepository.save(userAccountEntity);
        return accountMapper.mapEntityToDto(saved);
    }

    public void updateUser(UserDetails user) {
        UserAccountEntity userAccountEntityByUsername = userAccountRepository.findUserAccountEntityByUsername(user.getUsername());
        UserAccountEntity userAccountEntity = accountMapper.mapDtoToEntity((UserAccountDto) user);
        encodePassword(userAccountEntity);
        BeanUtils.copyProperties(userAccountEntity, userAccountEntityByUsername);
        userAccountRepository.save(userAccountEntityByUsername);
    }

    public void deleteUser(String username) {
        userAccountRepository.deleteByUsername(username);
    }

    private void encodePassword(UserAccountEntity userAccountEntity) {
        userAccountEntity.setPassword(passwordEncoder.encode(userAccountEntity.getPassword()));
    }

    public List<UserAccountDto> all() {
        return accountMapper.mapEntityToDto(userAccountRepository.findAll());
    }
}
