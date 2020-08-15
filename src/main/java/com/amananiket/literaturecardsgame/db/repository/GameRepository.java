package com.amananiket.literaturecardsgame.db.repository;

import org.springframework.data.repository.CrudRepository;

import com.amananiket.literaturecardsgame.db.entity.GameEntity;

public interface GameRepository extends CrudRepository<GameEntity, Long> {
    GameEntity findByGameAliasAndActiveIsTrue(String gameAlias);
}
