package com.amananiket.literaturecardsgame.db.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "turns")
public class TurnEntity {

    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    private PlayerEntity callingPlayerEntity;
    @ManyToOne
    private PlayerEntity calledPlayerEntity;
    @ManyToOne
    private CardEntity cardEntity;
    private boolean isSuccessful;
    private Timestamp createdTime;
    private Timestamp updatedTime;

}
