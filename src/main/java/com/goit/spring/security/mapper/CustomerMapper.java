package com.goit.spring.security.mapper;

import com.goit.spring.security.dto.CustomerDto;
import com.goit.spring.security.entity.CustomerEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
@RequiredArgsConstructor
public class CustomerMapper implements Mapper<CustomerEntity, CustomerDto> {

    @Override
    public CustomerDto mapEntityToDto(CustomerEntity source) throws RuntimeException {
        if (isNull(source)) {
            return null;
        }
        CustomerDto target = new CustomerDto();
        target.setId(source.getId());
        target.setName(source.getName());
        target.setContactName(source.getContactName());
        target.setCountry(source.getCountry());
        return target;
    }

    @Override
    public CustomerEntity mapDtoToEntity(CustomerDto source) {
        if (isNull(source)) {
            return null;
        }
        CustomerEntity target = new CustomerEntity();
        target.setId(source.getId());
        target.setName(source.getName());
        target.setContactName(source.getContactName());
        target.setCountry(source.getCountry());
        return target;
    }
}
