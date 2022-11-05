/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.octo.mangaList.service;

import com.octo.mangaList.entity.AnimeEntity;
import com.octo.mangaList.repository.AnimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author vladmir
 */
@Service
public class AnimeService {

    @Autowired
    AnimeRepository animeRepository;

    public String main(Model model) {
        Iterable<AnimeEntity> animes = animeRepository.findAll();
        model.addAttribute("animes", animes);
        return "main";
    }
    
    public String addTitle(String title, int episodes, Model model) {
        AnimeEntity anime = new AnimeEntity();
        anime.setTitle(title);
        anime.setAllEpisodes(episodes);
        animeRepository.save(anime);
        return "redirect:/anime";
    }

    public String details(@PathVariable(value = "id") Long id, Model model) {
        AnimeEntity anime = animeRepository.findById(id).orElseThrow();
        String[] animes = new String[anime.getAllEpisodes()];
        for (int i = 0; i < anime.getAllEpisodes(); i++) {
            animes[i] = "Эпизод " + ++i;
            --i;
        }
        model.addAttribute("animes", animes);
        return "animedetails";
    }

}