package com.amananiket.literaturecardsgame.db.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.amananiket.literaturecardsgame.model.Denomination;
import com.amananiket.literaturecardsgame.model.Suit;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
@Table(name = "cards")
public class CardEntity {
    @Id
    @GeneratedValue
    private long id;
    @OneToOne
    private GameEntity gameEntity;
    @OneToOne
    private PlayerEntity playerEntityInPossession;
    @Enumerated
    private Suit suit;
    @Enumerated
    private Denomination denomination;
    private boolean inPlay;
    private Timestamp createdTime;
    private Timestamp updatedTime;

}
