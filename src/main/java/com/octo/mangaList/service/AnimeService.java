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

    public String watching(@PathVariable(value = "id")Long id,@RequestParam int episodes, Model model) {
        AnimeEntity anime = animeRepository.findById(id).orElseThrow();
        model.addAttribute("id", anime.getId());
        anime.setWatchedEpisodes(anime.getWatchedEpisodes() + episodes);
        animeRepository.save(anime);
        return "animedetails";
    }

    public String details(@PathVariable(value = "id") Long id, Model model) {
        AnimeEntity anime = animeRepository.findById(id).orElseThrow();
        Episode[] animes = new Episode[anime.getAllEpisodes()];
        for (int i = 0; i < anime.getAllEpisodes(); i++) {
            if (i < anime.getWatchedEpisodes()) {
                animes[i] = new Episode(++i, true, anime.getId());
                --i;
            } else {
                animes[i] = new Episode(++i, false, anime.getId());
                --i;
            }
        }
        model.addAttribute("animes", animes);
        return "animedetails";
    }

    class Episode {
        
        Long id;
        int number;
        boolean watched;

        public Episode(int number, boolean watched, Long id) {
            this.id = id;
            this.number = number;
            this.watched = watched;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public boolean isWatched() {
            return watched;
        }

        public void setWatched(boolean watched) {
            this.watched = watched;
        }

    }

}
