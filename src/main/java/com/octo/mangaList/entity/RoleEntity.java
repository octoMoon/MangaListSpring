/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.octo.mangaList.entity;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author vladmir
 */
@Data
@Entity
public class RoleEntity implements GrantedAuthority{

    
    
    @Id
    private Long id;
    private String name;
    
    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<UserEntity> users;
    
    public RoleEntity() {
    }

    public RoleEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public RoleEntity(Long id) {
        this.id = id;
    }

   

    @Override
    public String getAuthority() {
return getName();    }
    
    
    
}
