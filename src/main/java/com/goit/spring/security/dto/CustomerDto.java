package com.goit.spring.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class CustomerDto {

    private Long id;
    private String name;
    private String contactName;
    private String country;
    private List<ProductDto> products;
}
