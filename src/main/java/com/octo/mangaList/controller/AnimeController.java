/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.octo.mangaList.controller;

import com.octo.mangaList.entity.AnimeEntity;
import com.octo.mangaList.entity.EpisodeEntity;
import com.octo.mangaList.service.AnimeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author vladmir
 */
@Controller
public class AnimeController {

    @Autowired
    AnimeService animeService;

    @GetMapping("/anime")
    public String main(Model model) {
        animeService.main(model);
        return "anime";
    }

    @GetMapping("/anime/{id}")
    public String details(@PathVariable(value = "id") Long id, Model model) {
        animeService.details(id, model);
        return "animedetails";
    }

    @PostMapping("/anime")
    public String addTitlePost(@RequestParam String title, @RequestParam int episodes, Model model) {
        animeService.addTitle(title, episodes, model);
        return "redirect:/anime";
    }

    @PostMapping("/anime/{id}")
    public String watching(@PathVariable(value = "id") Long id,
            @RequestParam("episodeList") int[] episodeList,
          
            Model model) {
        animeService.watching(id,episodeList, model);
        return "redirect:/anime";
    }

}
