package com.goit.spring.security.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "customers")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_name")
    private String name;

    @Column(name = "contact_name")
    private String contactName;

    @Column(name = "country")
    private String country;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private UserAccountEntity account;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<ProductEntity> products;
}
