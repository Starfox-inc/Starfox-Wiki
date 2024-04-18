package com.Starfox.EsportsWiki.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Starfox.EsportsWiki.model.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {

    // Player findFirstById(Long id);
 
    // @Query("select p from Player p where p.playerId = ?1")
    // Player find(Long id);
 
}