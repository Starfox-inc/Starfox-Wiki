package com.Starfox.EsportsWiki.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Starfox.EsportsWiki.model.CsTeams;

@Repository
public interface CsTeamRepository extends JpaRepository<CsTeams, Integer>{
    CsTeams findFirstById(int id);
}

