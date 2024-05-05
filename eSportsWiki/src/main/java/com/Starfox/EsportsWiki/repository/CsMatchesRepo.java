package com.Starfox.EsportsWiki.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Starfox.EsportsWiki.model.CsMatches;

@Repository
public interface CsMatchesRepo extends JpaRepository<CsMatches, Integer>{
    CsMatches findFirstById(int id);
}
