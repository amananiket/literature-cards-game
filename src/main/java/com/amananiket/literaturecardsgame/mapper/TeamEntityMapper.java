package com.amananiket.literaturecardsgame.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amananiket.literaturecardsgame.db.entity.TeamEntity;
import com.amananiket.literaturecardsgame.model.Team;

@Component
public class TeamEntityMapper {

    @Autowired
    private PlayerEntityMapper playerEntityMapper;

    public Team mapTeam(TeamEntity teamEntity) {
        if (teamEntity == null) {
            return null;
        }

        return Team.builder()
                .players(playerEntityMapper.mapPlayers(teamEntity.getPlayers()))
                .teamAlias(teamEntity.getTeamAlias())
                .build();
    }
}
