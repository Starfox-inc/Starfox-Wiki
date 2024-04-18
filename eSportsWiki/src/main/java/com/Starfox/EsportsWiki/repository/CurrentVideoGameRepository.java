package com.Starfox.EsportsWiki.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Starfox.EsportsWiki.model.CurrentVideoGame;

@Repository
public interface CurrentVideoGameRepository extends JpaRepository<CurrentVideoGame, Integer>{
    
    CurrentVideoGame findFirstById(int id);
}
