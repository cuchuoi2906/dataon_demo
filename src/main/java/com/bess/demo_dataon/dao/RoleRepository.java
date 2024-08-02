package com.bess.demo_dataon.dao;

import com.bess.demo_dataon.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {
}
