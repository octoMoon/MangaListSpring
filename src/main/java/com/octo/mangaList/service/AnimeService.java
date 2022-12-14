/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.octo.mangaList.service;

import com.octo.mangaList.entity.AnimeEntity;
import com.octo.mangaList.entity.EpisodeEntity;
import com.octo.mangaList.repository.AnimeRepository;
import com.octo.mangaList.repository.EpisodeRepository;
import java.util.List;
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
    
    @Autowired
    EpisodeRepository episodeRepository;

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
        for(int i =1; i<=episodes; i++){
            EpisodeEntity episode = new EpisodeEntity();
            episode.setAnime(anime);
            episode.setEpisodeNumber(i);
            episode.setWatched(0);
            episodeRepository.save(episode);
        }
        return "redirect:/anime";
    }

    public String watching( @PathVariable(value = "id") Long id,
            @RequestParam("episodeList") String[] episodeList, 
       Model model) {
        AnimeEntity anime = animeRepository.findById(id).orElseThrow();
        List<EpisodeEntity> episodes = anime.getEpisodes();
        anime.setWatchedEpisodes(0);
        for(EpisodeEntity episode : episodes){
        episode.setWatched(0);
        }
        for (String episode : episodeList) {
            System.out.println(episode);
            String [] values = episode.split("watched");
            episodes.get(Integer.parseInt(values[0])-1).setWatched(1);
            anime.setWatchedEpisodes(anime.getWatchedEpisodes()+1);
        }      
       anime.setEpisodes(episodes);
       animeRepository.save(anime);
        return "anime";
    }

    public String details(@PathVariable(value = "id") Long id, Model model) {
        AnimeEntity anime = animeRepository.findById(id).orElseThrow();
        List<EpisodeEntity> episodes = anime.getEpisodes();
        model.addAttribute("episodes", episodes);
        return "animedetails";
    }

   

}
