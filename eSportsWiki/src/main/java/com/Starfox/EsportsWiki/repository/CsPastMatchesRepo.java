package com.Starfox.EsportsWiki.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Starfox.EsportsWiki.model.CsPastMatches;

@Repository
public interface CsPastMatchesRepo extends JpaRepository<CsPastMatches, Integer>{
    CsPastMatches findFirstById(int id);
}
