package com.goit.spring.security.service;

import com.goit.spring.security.dto.UserAccountDto;
import com.goit.spring.security.entity.UserAccountEntity;
import com.goit.spring.security.mapper.AccountMapper;
import com.goit.spring.security.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserAccountService implements UserDetailsService {

    private final UserAccountRepository userAccountRepository;
    private final AccountMapper accountMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserAccountDto loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccountEntity userAccountEntityByUsername = userAccountRepository.findUserAccountEntityByUsername(username);
        return accountMapper.mapEntityToDto(userAccountEntityByUsername);
    }

    public void createUser(UserDetails user) {
        UserAccountEntity userAccountEntity = accountMapper.mapDtoToEntity((UserAccountDto) user);
        encodePassword(userAccountEntity);
        userAccountRepository.save(userAccountEntity);
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
}
