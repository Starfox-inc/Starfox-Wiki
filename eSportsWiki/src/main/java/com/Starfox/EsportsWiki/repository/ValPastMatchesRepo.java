package com.Starfox.EsportsWiki.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Starfox.EsportsWiki.model.ValPastMatches;

@Repository
public interface ValPastMatchesRepo extends JpaRepository<ValPastMatches, Integer>{
    ValPastMatches findFirstById(int id);    
}
