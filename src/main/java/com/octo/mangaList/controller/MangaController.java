/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.octo.mangaList.controller;

import com.octo.mangaList.service.MangaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author vladmir
 */
@Controller
public class MangaController {

    @Autowired
    private MangaService mangaService;

    @GetMapping("/manga")
    public String main(Model model) {
        mangaService.main(model);
        return "manga";
    }

    @GetMapping("/addtitle")
    public String addTitle(Model model) {
        return "addtitle";
    }

    @PostMapping("/manga")
    public String addTitlePost(@RequestParam String title, Model model) {
        mangaService.addTitlePost(title, model);
        return "redirect:/manga";
    }

    @PostMapping("/addepisodes/{id}")
    public String addEpisodes(@PathVariable(value = "id") Long id, @RequestParam int episodes, Model model) {
        mangaService.addEpisodes(id, episodes, model);
        return "redirect:/manga";
    }

}