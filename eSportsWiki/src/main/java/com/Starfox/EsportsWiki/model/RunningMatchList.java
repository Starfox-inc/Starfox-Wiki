package com.Starfox.EsportsWiki.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name = "runningmatchlist")
public class RunningMatchList extends Matches{
}
