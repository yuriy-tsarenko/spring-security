package com.goit.spring.security.repository;

import com.goit.spring.security.entity.UserAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccountEntity, Long> {

    UserAccountEntity findUserAccountEntityByUsername(String username);

    void deleteByUsername(String username);
}
