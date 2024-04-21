package com.Starfox.EsportsWiki.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Starfox.EsportsWiki.model.RunningMatchList;

@Repository
public interface RunningMatchRepo extends JpaRepository<RunningMatchList, Integer> {
    RunningMatchList findFirstById(int id);
}
