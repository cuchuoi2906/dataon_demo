package com.bess.demo_dataon.service;

import com.bess.demo_dataon.dao.RoleRepository;
import com.bess.demo_dataon.dao.UserRepository;
import com.bess.demo_dataon.entity.Notification;
import com.bess.demo_dataon.entity.Role;
import com.bess.demo_dataon.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Users findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public ResponseEntity<?> userRegistry(Users users) {
        // Kiểm tra email
        if(userRepository.existsByEmail(users.getEmail())){
            return ResponseEntity.badRequest().body(new Notification("Tên đăng nhập đã tồn tại."));
        }
        if(userRepository.existsByPhone(users.getPhone())){
            return ResponseEntity.badRequest().body(new Notification("Email đã tồn tại."));
        }
        try {
            String passwordEncode = passwordEncoder.encode(users.getPassWord());
            users.setPassWord(passwordEncode);
            Users savedUser = userRepository.save(users);
            // Kiểm tra sự tồn tại của user hoặc các thuộc tính
            return ResponseEntity.ok("User saved successfully!");
        } catch (Exception e) {
            return ResponseEntity.ok("Đăng ký không thành công"+ e.getMessage());
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = findByUserName(username);
        if(users == null){
            throw new UsernameNotFoundException("");
        }
        return new User(users.getUserName(), users.getPassWord(), rolesToAuthorities(users.getRoleList()));
    }
    // Trả về danh sách quyền dạng list
    private Collection<? extends GrantedAuthority> rolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
