/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.octo.mangaList.repository;

import com.octo.mangaList.entity.AnimeEntity;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author vladmir
 */
public interface AnimeRepository extends CrudRepository<AnimeEntity, Long>{
    
}
