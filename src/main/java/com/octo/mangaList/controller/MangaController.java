/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.octo.mangaList.controller;

import com.octo.mangaList.entity.MangaEntity;
import com.octo.mangaList.exception.TitleAlreadyExistException;
import com.octo.mangaList.exception.TitleNotFoundByIdException;
import com.octo.mangaList.service.MangaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author vladmir
 */
@RestController
@RequestMapping("/manga")
public class MangaController {

    @Autowired
    MangaService mangaService;

    @PostMapping
    public ResponseEntity addMangaTitle(@RequestBody MangaEntity manga) {
        try {
            mangaService.addMangaTitle(manga);
            return ResponseEntity.ok("Add new Title");
        } catch (TitleAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @GetMapping
    public ResponseEntity getMangaTitleById(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(mangaService.getTitleFindById(id));
        } 
        catch (TitleNotFoundByIdException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    } 

}
