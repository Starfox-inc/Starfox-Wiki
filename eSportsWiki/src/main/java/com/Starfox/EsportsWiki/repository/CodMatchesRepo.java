package com.Starfox.EsportsWiki.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Starfox.EsportsWiki.model.CodMatches;

@Repository
public interface CodMatchesRepo extends JpaRepository<CodMatches, Integer>{
    CodMatches findFirstById(int id);
    
}
