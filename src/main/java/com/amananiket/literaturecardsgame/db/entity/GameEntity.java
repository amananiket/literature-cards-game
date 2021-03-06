package com.amananiket.literaturecardsgame.db.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "games")
public class GameEntity {

    @Id
    @GeneratedValue
    private long id;
    private String gameAlias;
    private boolean isActive;
    @OneToOne
    private PlayerEntity activePlayerEntity;
    @OneToMany
    private List<TeamEntity> teams;
    private int teamRedScore;
    private int teamBlueScore;
    private Timestamp createdTime;
    private Timestamp updatedTime;
}
