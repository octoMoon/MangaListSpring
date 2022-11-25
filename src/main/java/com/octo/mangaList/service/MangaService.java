/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.octo.mangaList.service;

import com.octo.mangaList.entity.MangaEntity;
import com.octo.mangaList.entity.UserEntity;
import com.octo.mangaList.repository.MangaRepository;
import com.octo.mangaList.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

/**
 *
 * @author vladmir
 */
@Service
public class MangaService {
    
    @Autowired
    private MangaRepository mangaRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    public String main(Model model) {
        Iterable<MangaEntity> mangas = mangaRepository.findAll();
        model.addAttribute("mangas", mangas);
        return "manga";}
    
    public String addTitlePost(String title, Model model) {          
        MangaEntity manga = new MangaEntity();
        manga.setTitle(title);
        mangaRepository.save(manga);
        return "redirect:/manga";
    }
    
    public String addEpisodes(Long id, int episodes, Model model) {
        MangaEntity manga = mangaRepository.findById(id).orElseThrow();
        manga.setEpisodes(manga.getEpisodes() + episodes);
        mangaRepository.save(manga);
        return "redirect:/manga";
    }
    
}
