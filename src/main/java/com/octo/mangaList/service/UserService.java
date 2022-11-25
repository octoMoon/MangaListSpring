/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.octo.mangaList.service;

import com.octo.mangaList.entity.RoleEntity;
import com.octo.mangaList.entity.UserEntity;
import com.octo.mangaList.repository.RoleRepository;
import com.octo.mangaList.repository.UserRepository;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author vladmir
 */
@Service
public class UserService implements UserDetailsService {

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }
    
    public UserEntity findUserId(Long userId){
    Optional<UserEntity> userFromDb = userRepository.findById(userId);
    return userFromDb.orElse(new UserEntity());
    }
    
    public List<UserEntity> allUsers(){
    return (List<UserEntity>) userRepository.findAll();
    }
    
    public boolean saveUser(UserEntity user){
    UserEntity userFromDb = userRepository.findByUsername(user.getUsername());
    if (userFromDb != null){
    return false;
    }
    user.setRoles(Collections.singleton(new RoleEntity(1L, "ROLE_USER")));
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    userRepository.save(user);
    return true;
    }
    
    public boolean deleteUser(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }
    
     public List<UserEntity> usergtList(Long idMin) {
        return entityManager.createQuery("SELECT u FROM User u WHERE u.id > :paramId", UserEntity.class)
                .setParameter("paramId", idMin).getResultList();
    }
    
    

}
