package com.bess.demo_dataon.service;

import com.bess.demo_dataon.entity.Users;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    public Users findByUserName(String userName);
    public ResponseEntity<?> userRegistry(Users user);
}
