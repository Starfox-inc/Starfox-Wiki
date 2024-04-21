package com.Starfox.EsportsWiki.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Starfox.EsportsWiki.model.LoLTeams;

@Repository
public interface LoLTeamRepository extends JpaRepository<LoLTeams, Integer>{
    LoLTeams findFirstById(int id);
}
