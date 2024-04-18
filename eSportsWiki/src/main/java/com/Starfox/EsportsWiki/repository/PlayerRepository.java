package com.Starfox.EsportsWiki.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Starfox.EsportsWiki.model.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {

    // integrationAPI.Player findFirstById(Long id);
 
    // @Query("select p from integrationAPI.Player p where p.playerId = ?1")
    // integrationAPI.Player find(Long id);
 
}