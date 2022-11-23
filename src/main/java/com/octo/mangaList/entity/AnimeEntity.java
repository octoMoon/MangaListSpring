/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.octo.mangaList.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author vladmir
 */
@Entity
public class AnimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private int allEpisodes;
    private int watchedEpisodes;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "anime")
    private List<EpisodeEntity> episodes;

    public AnimeEntity() {
    }

    public List<EpisodeEntity> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<EpisodeEntity> episodes) {
        this.episodes = episodes;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAllEpisodes() {
        return allEpisodes;
    }

    public void setAllEpisodes(int allEpisodes) {
        this.allEpisodes = allEpisodes;
    }

    public int getWatchedEpisodes() {
        return watchedEpisodes;
    }

    public void setWatchedEpisodes(int watchedEpisodes) {
        this.watchedEpisodes = watchedEpisodes;
    }

}
