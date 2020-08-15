package com.amananiket.literaturecardsgame.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.amananiket.literaturecardsgame.db.entity.CardEntity;
import com.amananiket.literaturecardsgame.db.entity.PlayerEntity;
import com.amananiket.literaturecardsgame.model.Card;
import com.amananiket.literaturecardsgame.model.Player;

@Component
public class CardEntityMapper {
    public List<Card> mapCards(List<CardEntity> cardEntities) {
        if (cardEntities == null) {
            return null;
        }

        List<Card> hand = new ArrayList<>();
        cardEntities.forEach( cardEntity -> {
            hand.add(mapCard(cardEntity));
        });

        return hand;
    }

    private Card mapCard(CardEntity cardEntity) {
        if (cardEntity == null) {
            return null;
        }

        return Card.builder()
                .denomination(cardEntity.getDenomination())
                .suit(cardEntity.getSuit())
                .build();
    }
}
