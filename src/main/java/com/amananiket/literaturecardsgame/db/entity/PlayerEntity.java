package com.amananiket.literaturecardsgame.db.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.amananiket.literaturecardsgame.model.TeamAlias;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
@Table(name = "players")
public class PlayerEntity {

    @Id
    @GeneratedValue
    private long id;
    private String playerAlias;
    private TeamAlias teamAlias;
    @OneToMany
    private List<CardEntity> hand;
    @ManyToOne
    private GameEntity gameEntity;
    private Timestamp createdTime;
    private Timestamp updatedTime;

}
