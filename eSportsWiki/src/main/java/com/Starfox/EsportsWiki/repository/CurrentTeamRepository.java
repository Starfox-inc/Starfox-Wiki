package com.Starfox.EsportsWiki.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Starfox.EsportsWiki.model.CurrentTeam;

public interface CurrentTeamRepository extends JpaRepository<CurrentTeam, Integer>{
    CurrentTeam findFirstById(int id);
}
