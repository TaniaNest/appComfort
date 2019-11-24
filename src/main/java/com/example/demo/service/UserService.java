package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.exception.UserUpdateException;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repositoty.RoleRepository;
import com.example.demo.repositoty.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;


@Service("userService")
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User getUserById(int id) {
        return userRepository.findUserById(id);
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User saveUser(UserDTO userDTO) {
        userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        userDTO.setActive(1);
        Role userRole = roleRepository.findByRole("ADMIN");
        userDTO.setRoles(new HashSet<>(Collections.singletonList(userRole)));
        User user = new User(userDTO.getEmail(), userDTO.getPassword(), userDTO.getName(), userDTO.getLastName(), userDTO.getActive(), userDTO.getRoles());
        return userRepository.save(user);
    }

    public User update(UserDTO userDTO) throws Exception {
        if (userRepository.findById(userDTO.getId()).isPresent()) {
            User user = userRepository.findUserById(userDTO.getId());
            user.setName(userDTO.getName());
            user.setLastName(userDTO.getLastName());
            user.setEmail(userDTO.getEmail());
            user.setPassword(userDTO.getPassword());
            user.setActive(userDTO.getActive());
            user.setRoles(userDTO.getRoles());
            return userRepository.save(user);
        }
        log.warn("Can not update user - " + userDTO.toString());
        throw new UserUpdateException(
                "Can not update user - " + userDTO.toString());
    }
}