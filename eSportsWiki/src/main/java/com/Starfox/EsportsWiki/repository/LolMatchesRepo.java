package com.Starfox.EsportsWiki.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Starfox.EsportsWiki.model.LolMatches;

@Repository
public interface LolMatchesRepo extends JpaRepository<LolMatches, Integer>{
    LolMatches findFirstById(int id);
    
}
