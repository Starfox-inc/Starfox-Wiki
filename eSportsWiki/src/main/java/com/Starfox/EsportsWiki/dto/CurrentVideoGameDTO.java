package com.Starfox.EsportsWiki.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CurrentVideoGameDTO{
    int id;
    String name;
    String slug;
}