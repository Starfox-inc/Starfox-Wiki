package com.Starfox.EsportsWiki.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Starfox.EsportsWiki.model.ValTeams;

@Repository
public interface ValTeamRepository extends JpaRepository<ValTeams, Integer>{

    ValTeams findFirstById(int id);
}
