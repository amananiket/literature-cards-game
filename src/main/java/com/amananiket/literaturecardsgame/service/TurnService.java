package com.amananiket.literaturecardsgame.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.amananiket.literaturecardsgame.model.Card;
import com.amananiket.literaturecardsgame.model.Declaration;
import com.amananiket.literaturecardsgame.model.Player;
import com.amananiket.literaturecardsgame.model.Suit;
import com.amananiket.literaturecardsgame.model.Turn;

@Service
public class TurnService {

    @Autowired
    private GameService gameService;

    public boolean createTurn(Turn requestedTurn) {

        //TODO: Check if it's the turn of the player who called the turn.

        if (requestedTurn.getCalledPlayer().getTeamAlias().equals(requestedTurn.getCallingPlayer().getTeamAlias())) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Can't call on a player of the same team");

        }

        Suit calledSuit = requestedTurn.getCalledCard().getSuit();
        boolean higherSet = requestedTurn.getCalledCard().getDenomination().isHigherSet();

        boolean setAvailable = false;
        boolean denominationAvailable = false;

        for (Card card : requestedTurn.getCallingPlayer().getHand()) {
            if (card.getSuit().name().equals(calledSuit.name())) {
                if ((higherSet && card.getDenomination().isHigherSet()) || (!higherSet && !card.getDenomination().isHigherSet())) {
                    setAvailable = true;

                    if (card.getDenomination().name().equals(requestedTurn.getCalledCard().getDenomination().name())) {
                        denominationAvailable = true;
                    }
                }
            }
        }

        if (!(setAvailable && !denominationAvailable)) {
            // Invalid turn.
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Bad turn. Can't call the card you have or the set you don't have");
        }

        for (Card card : requestedTurn.getCalledPlayer().getHand()) {
            if (card.getDenomination().name().
                    equals(requestedTurn.getCalledCard().getDenomination().name()) &&
                    card.getSuit().name().
                            equals(requestedTurn.getCalledCard().getSuit().name())) {

                //TODO: Change ownership of hands in DB and stream it to players.
                return true;
            }
        }

        // TODO: Register unsuccessful turn in DB
        return false;
    }

    public boolean createDeclaration(Declaration requestedDeclaration) {

        //TODO: Check if it's the turn of the player/team who called the turn.

        List<Player> playersList = gameService.getPlayersList(requestedDeclaration.getGameId());

        boolean wrongDeclaration = false;

        for (Map.Entry<Player, List<Card>> entry : requestedDeclaration.getDeclaration().entrySet()) {

            for (Player targetPlayer : playersList) {
                if (targetPlayer.getPlayerAlias().equals(entry.getKey().getPlayerAlias())) {
                    List<Card> targetPlayerHand = targetPlayer.getHand();

                    for (Card card : entry.getValue()) {

                        if (! (card.getSuit().name().equals(requestedDeclaration.getDeclaredSuit().name())
                                && card.getDenomination().isHigherSet().equals(requestedDeclaration.isHigherSuit()))) {
                            // Card selected is from the same declared set.
                            wrongDeclaration = true;
                            break;
                        }

                        boolean cardMatch = false;

                        for (Card targetCard : targetPlayerHand) {
                            if (card.equals(targetCard)) {
                                cardMatch = true;
                                break;
                            }
                        }

                        if (!cardMatch) {
                            wrongDeclaration = true;
                            break;
                        }
                    }
                }

                if (wrongDeclaration) {
                    break;
                }
            }

            if (wrongDeclaration) {
                break;
            }
        }

        // TODO: Discard all cards from the declared set.

        return !wrongDeclaration;
    }
}
