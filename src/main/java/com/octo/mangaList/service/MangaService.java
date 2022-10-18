/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.octo.mangaList.service;

import com.octo.mangaList.entity.MangaEntity;
import com.octo.mangaList.exception.TitleAlreadyExistException;
import com.octo.mangaList.exception.TitleNotFoundByIdException;
import com.octo.mangaList.repository.MangaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author vladmir
 */
@Service
public class MangaService {

    @Autowired
    private MangaRepository mangaRepository;

    public MangaEntity getTitleFindById(Long id) throws TitleNotFoundByIdException {
        MangaEntity manga = mangaRepository.findById(id).get();
        if (manga == null) {
            throw new TitleNotFoundByIdException("Not found by id");
        }
        return manga;
    }

    public MangaEntity addMangaTitle(MangaEntity manga) throws TitleAlreadyExistException {
        if (mangaRepository.findByTitle(manga.getTitle()) != null) {
            throw new TitleAlreadyExistException("Title Already Exist");
        }
        return mangaRepository.save(manga);

    }

}
