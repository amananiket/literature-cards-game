package com.amananiket.literaturecardsgame.db.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
@Table(name = "games")
public class GameEntity {

    @Id
    @GeneratedValue
    private long id;
    private String gameAlias;
    private boolean isActive;
    @OneToOne
    private PlayerEntity activePlayerEntity;
    private int teamRedScore;
    private int teamBlueScore;
    private Timestamp createdTime;
    private Timestamp updatedTime;
}
