package com.Starfox.EsportsWiki.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Starfox.EsportsWiki.model.TeamInfo;

@Repository
public interface TeamInfoRepository extends JpaRepository<TeamInfo, Integer>{
    TeamInfo findFirstById(int id);
}

