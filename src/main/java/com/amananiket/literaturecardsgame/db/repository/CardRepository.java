package com.amananiket.literaturecardsgame.db.repository;

import org.springframework.data.repository.CrudRepository;

import com.amananiket.literaturecardsgame.db.entity.CardEntity;

public interface CardRepository extends CrudRepository<CardEntity, Long> {
}
