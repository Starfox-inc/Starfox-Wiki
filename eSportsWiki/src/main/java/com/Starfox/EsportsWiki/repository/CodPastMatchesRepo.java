package com.Starfox.EsportsWiki.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Starfox.EsportsWiki.model.CodPastMatches;

@Repository
public interface CodPastMatchesRepo extends JpaRepository<CodPastMatches, Integer>{
    CodPastMatches findFirstById(int id);
    
}
