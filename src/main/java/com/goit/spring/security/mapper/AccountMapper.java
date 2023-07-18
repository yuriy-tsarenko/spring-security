package com.goit.spring.security.mapper;

import com.goit.spring.security.dto.UserAccountDto;
import com.goit.spring.security.entity.UserAccountEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static java.util.Objects.isNull;

@Component
@RequiredArgsConstructor
public class AccountMapper implements Mapper<UserAccountEntity, UserAccountDto> {

    private final CustomerMapper customerMapper;

    @Override
    public UserAccountDto mapEntityToDto(UserAccountEntity source) throws RuntimeException {
        if (isNull(source)) {
            return null;
        }
        UserAccountDto target = new UserAccountDto();
        target.setPassword(source.getPassword());
        target.setUsername(source.getUsername());
        target.setIsAccountNonExpired(source.getIsAccountNonExpired());
        target.setIsAccountNonLocked(source.getIsAccountNonLocked());
        target.setIsCredentialsNonExpired(source.getIsCredentialsNonExpired());
        target.setIsEnabled(source.getIsEnabled());
        target.setAuthorities(source.getAuthorities());
        if (Objects.nonNull(source.getCustomer())) {
            target.setCustomer(customerMapper.mapEntityToDto(source.getCustomer()));
        }
        return target;
    }

    @Override
    public UserAccountEntity mapDtoToEntity(UserAccountDto source) {
        if (isNull(source)) {
            return null;
        }
        UserAccountEntity target = new UserAccountEntity();
        target.setPassword(source.getPassword());
        target.setUsername(source.getUsername());
        target.setIsAccountNonExpired(source.getIsAccountNonExpired());
        target.setIsAccountNonLocked(source.getIsAccountNonLocked());
        target.setIsCredentialsNonExpired(source.getIsCredentialsNonExpired());
        target.setIsEnabled(source.getIsEnabled());
        target.setAuthorities(source.getAuthorities());
        if (Objects.nonNull(source.getCustomer())) {
            target.setCustomer(customerMapper.mapDtoToEntity(source.getCustomer()));
        }
        return target;
    }
}
