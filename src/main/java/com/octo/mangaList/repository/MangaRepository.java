/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.octo.mangaList.repository;

import com.octo.mangaList.entity.MangaEntity;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author vladmir
 */
public interface MangaRepository extends CrudRepository<MangaEntity, Long>{
    MangaEntity findByTitle(String title);
}
