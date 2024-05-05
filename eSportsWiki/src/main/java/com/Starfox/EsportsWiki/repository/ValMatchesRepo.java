package com.Starfox.EsportsWiki.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Starfox.EsportsWiki.model.ValMatches;

@Repository
public interface ValMatchesRepo extends JpaRepository<ValMatches, Integer>{
    ValMatches findFirstById(int id);
    
}
