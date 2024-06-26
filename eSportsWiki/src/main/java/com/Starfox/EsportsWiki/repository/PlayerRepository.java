package com.Starfox.EsportsWiki.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Starfox.EsportsWiki.model.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {

    Player findFirstByPlayerId(int id);
 
 
}