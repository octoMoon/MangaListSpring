/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.octo.mangaList.controller;

import com.octo.mangaList.entity.MangaEntity;
import com.octo.mangaList.repository.MangaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author vladmir
 */
@Controller
public class MangaController {

    @Autowired
    private MangaRepository mangaRepository;

    @GetMapping("/main")
    public String main(Model model) {
        Iterable<MangaEntity> mangas = mangaRepository.findAll();
        model.addAttribute("mangas", mangas);
        return "main";
    }

    @GetMapping("/addtitle")
    public String addtitle(Model model) {
        return "addtitle";
    }

    @PostMapping("/addtitle")
    public String addtitlepost(@RequestParam String title, Model model) {
        MangaEntity manga = new MangaEntity();
        manga.setTitle(title);
        mangaRepository.save(manga);
        return "redirect:/main";
    }

}
