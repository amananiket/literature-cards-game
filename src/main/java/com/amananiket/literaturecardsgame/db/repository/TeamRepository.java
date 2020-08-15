package com.amananiket.literaturecardsgame.db.repository;

import org.springframework.data.repository.CrudRepository;

import com.amananiket.literaturecardsgame.db.entity.TeamEntity;

public interface TeamRepository extends CrudRepository<TeamEntity, Long> {
}
