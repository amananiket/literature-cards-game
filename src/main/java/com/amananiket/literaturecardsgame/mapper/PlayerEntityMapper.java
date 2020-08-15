package com.amananiket.literaturecardsgame.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amananiket.literaturecardsgame.db.entity.PlayerEntity;
import com.amananiket.literaturecardsgame.model.Player;

@Component
public class PlayerEntityMapper {

    @Autowired
    private CardEntityMapper cardEntityMapper;

    public List<Player> mapPlayers(List<PlayerEntity> playerEntities) {
        if (playerEntities == null) {
            return null;
        }

        List<Player> playersList = new ArrayList<>();
        playerEntities.forEach( playerEntity -> {
            playersList.add(mapPlayer(playerEntity));
        });

        return playersList;
    }

    private Player mapPlayer(PlayerEntity playerEntity) {
        if (playerEntity == null) {
            return null;
        }

        return Player.builder()
                .playerAlias(playerEntity.getPlayerAlias())
                .hand(cardEntityMapper.mapCards(playerEntity.getHand())s)
                .teamAlias(playerEntity.getTeamAlias())
                .build();
    }
}
