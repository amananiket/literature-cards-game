package com.amananiket.literaturecardsgame.db.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.amananiket.literaturecardsgame.db.entity.GameEntity;
import com.amananiket.literaturecardsgame.db.entity.PlayerEntity;

public interface PlayerRepository extends CrudRepository<PlayerEntity, Long> {
    PlayerEntity findByPlayerAliasAndGameEntity(String playerAlias, GameEntity gameEntity);

    List<PlayerEntity> findAllByGameEntity(GameEntity gameEntity);
}
