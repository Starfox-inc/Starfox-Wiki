package com.Starfox.EsportsWiki.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Starfox.EsportsWiki.model.CodTeams;

@Repository
public interface CodTeamRepository extends JpaRepository<CodTeams,Integer>{

    CodTeams findFirstById(int id);
}
