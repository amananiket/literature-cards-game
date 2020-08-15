package com.amananiket.literaturecardsgame.db.repository;

import org.springframework.data.repository.CrudRepository;

import com.amananiket.literaturecardsgame.db.entity.TurnEntity;

public interface TurnRepository extends CrudRepository<TurnEntity, Long> {
}
