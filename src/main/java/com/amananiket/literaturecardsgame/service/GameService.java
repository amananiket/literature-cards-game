package com.amananiket.literaturecardsgame.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.amananiket.literaturecardsgame.model.Card;
import com.amananiket.literaturecardsgame.model.Denomination;
import com.amananiket.literaturecardsgame.model.Game;
import com.amananiket.literaturecardsgame.model.Player;
import com.amananiket.literaturecardsgame.model.Suit;

@Service
public class GameService {

    public String getGameId(String suggestion) {
        //TODO: Check for duplicates among games being currently played and return suggestion if possible; Create the game here.
        return suggestion;
    }

    public String createGame(Game requestedGame) {

        validateGameConstraints(requestedGame);

        //TODO : Add players to the game created in getGameId

        List<Player> playerList = new ArrayList<>();
        playerList.addAll(requestedGame.getTeams().get(0).getPlayers());
        playerList.addAll(requestedGame.getTeams().get(1).getPlayers());

        List<Card> packOfCards = new ArrayList<>();

        for (Suit suit : Suit.values()) {
            for (Denomination denomination : Denomination.values()) {
                Card card = new Card(denomination, suit);
                packOfCards.add(card);
            }
        }

        int sizeOfPack = packOfCards.size();

        Random random = new Random();

        for (int i = sizeOfPack - 1; i > 0; i--) {
            int j = random.nextInt(i+1);
            Collections.swap(packOfCards, i, j);
        }

        int cardIndex = 0;

        for (Player player : playerList) {
            List<Card> hand = new ArrayList<>();

            int cardsAdded = 0;

            while (cardsAdded < (sizeOfPack/playerList.size())) {
                hand.add(packOfCards.get(cardIndex));
                cardIndex++;
                cardsAdded++;
            }
            player.setHand(hand);
        }

        //TODO: Save each player's hand to DB and stream it to players.

        return playerList.get(random.nextInt(playerList.size())).getPlayerAlias();
    }

    private void validateGameConstraints(Game requestedGame) {
        requestedGame.getTeams().forEach( team -> {
            if (team.getPlayers().size() != 3 && team.getPlayers().size() != 4) {
                throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Number of players not valid");
            }
        });

        if (requestedGame.getTeams().size() != 2) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Team size not 2");
        }

        if (requestedGame.getTeams().get(0).getPlayers().size() != requestedGame.getTeams().get(1).getPlayers().size()) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Teams not balanced");
        }
    }
}
