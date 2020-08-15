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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "teams")
public class TeamEntity {
    @Id
    @GeneratedValue
    private long id;
    @OneToMany
    private List<PlayerEntity> players;
    private TeamAlias teamAlias;
    @ManyToOne
    private GameEntity gameEntity;
    private Timestamp createdTime;
    private Timestamp updatedTime;
}
