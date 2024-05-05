package com.Starfox.EsportsWiki.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Starfox.EsportsWiki.model.LolPastMatches;

@Repository
public interface LolPastMatchesRepo extends JpaRepository<LolPastMatches, Integer> {
    LolPastMatches findFirstById(int id);    
}
