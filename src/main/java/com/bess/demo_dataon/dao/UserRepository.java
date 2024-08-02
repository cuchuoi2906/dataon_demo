package com.bess.demo_dataon.dao;

import com.bess.demo_dataon.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(path = "user")
public interface UserRepository extends JpaRepository<Users,Integer> {
    public Users findByUserName(String userName);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
}
