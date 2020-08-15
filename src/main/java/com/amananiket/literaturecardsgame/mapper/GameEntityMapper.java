package com.amananiket.literaturecardsgame.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amananiket.literaturecardsgame.db.entity.GameEntity;
import com.amananiket.literaturecardsgame.db.entity.PlayerEntity;
import com.amananiket.literaturecardsgame.model.Game;
import com.amananiket.literaturecardsgame.model.Team;
import com.amananiket.literaturecardsgame.model.TeamAlias;

@Component
public class GameEntityMapper {

    @Autowired
    private TeamEntityMapper teamEntityMapper;

    @Autowired
    private PlayerEntityMapper playerEntityMapper;

    public Game mapGame(GameEntity gameEntity) {
        if (gameEntity == null) {
            return null;
        }

        Team teamRed;
        Team teamBlue;

        if (gameEntity.getTeams().get(0).getTeamAlias().name().equals(TeamAlias.RED.name())) {
            teamRed = teamEntityMapper.mapTeam(gameEntity.getTeams().get(0));
            teamBlue = teamEntityMapper.mapTeam(gameEntity.getTeams().get(1));
        } else {
            teamBlue = teamEntityMapper.mapTeam(gameEntity.getTeams().get(0));
            teamRed = teamEntityMapper.mapTeam(gameEntity.getTeams().get(1));
        }
        return Game.builder()
                .teamRed(teamRed)
                .teamBlue(teamBlue)
                .activePlayer(playerEntityMapper.mapPlayer(gameEntity.getActivePlayerEntity()))
                .gameAlias(gameEntity.getGameAlias())
                .build();
    }
}
