package com.amananiket.literaturecardsgame.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.amananiket.literaturecardsgame.db.entity.CardEntity;
import com.amananiket.literaturecardsgame.db.entity.GameEntity;
import com.amananiket.literaturecardsgame.db.entity.PlayerEntity;
import com.amananiket.literaturecardsgame.db.entity.TeamEntity;
import com.amananiket.literaturecardsgame.db.repository.CardRepository;
import com.amananiket.literaturecardsgame.db.repository.GameRepository;
import com.amananiket.literaturecardsgame.db.repository.PlayerRepository;
import com.amananiket.literaturecardsgame.db.repository.TeamRepository;
import com.amananiket.literaturecardsgame.mapper.GameEntityMapper;
import com.amananiket.literaturecardsgame.mapper.PlayerEntityMapper;
import com.amananiket.literaturecardsgame.model.Card;
import com.amananiket.literaturecardsgame.model.Denomination;
import com.amananiket.literaturecardsgame.model.Game;
import com.amananiket.literaturecardsgame.model.Player;
import com.amananiket.literaturecardsgame.model.Suit;
import com.amananiket.literaturecardsgame.model.Team;
import com.amananiket.literaturecardsgame.model.TeamAlias;
import org.apache.commons.lang3.RandomStringUtils;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private PlayerEntityMapper playerEntityMapper;

    @Autowired
    private GameEntityMapper gameEntityMapper;

    public String getGameId(String suggestion) {
        if (gameRepository.findByGameAliasAndActiveIsTrue(suggestion) != null) {
            return RandomStringUtils.random(7, true, true);
        }
        return suggestion;
    }

    public Game createGame(Game requestedGame) {

        validateGameConstraints(requestedGame);

        //Create a Game in DB

        Timestamp now =  new Timestamp(System.currentTimeMillis());

        GameEntity gameEntity = GameEntity.builder()
                .gameAlias(requestedGame.getGameAlias())
                .isActive(true)
                .teamBlueScore(0)
                .teamRedScore(0)
                .createdTime(now)
                .updatedTime(now)
                .build();

        GameEntity savedGame = gameRepository.save(gameEntity);

        TeamEntity teamRedEntity = TeamEntity.builder()
                .gameEntity(savedGame)
                .teamAlias(TeamAlias.RED)
                .createdTime(now)
                .updatedTime(now)
                .build();

        TeamEntity savedTeamRed = teamRepository.save(teamRedEntity);

        TeamEntity teamBlueEntity = TeamEntity.builder()
                .gameEntity(savedGame)
                .teamAlias(TeamAlias.BLUE)
                .createdTime(now)
                .updatedTime(now)
                .build();

        TeamEntity savedTeamBlue = teamRepository.save(teamBlueEntity);


        List<Player> playerList = new ArrayList<>();
        playerList.addAll(requestedGame.getTeamRed().getPlayers());
        playerList.addAll(requestedGame.getTeamBlue().getPlayers());

        for (Player player : playerList) {
            PlayerEntity playerEntity = PlayerEntity.builder()
                    .gameEntity(savedGame)
                    .playerAlias(player.getPlayerAlias())
                    .createdTime(now)
                    .updatedTime(now)
                    .build();

            if (player.getTeamAlias().name().equals(TeamAlias.RED.name())) {
                playerEntity.setTeam(savedTeamRed);
            } else {
                playerEntity.setTeam(savedTeamBlue);
            }

            playerRepository.save(playerEntity);
        }

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

                CardEntity cardEntity = CardEntity.builder()
                        .gameEntity(savedGame)
                        .playerEntityInPossession(playerRepository.
                                findByPlayerAliasAndGameEntity(player.getPlayerAlias(), savedGame))
                        .denomination(packOfCards.get(cardIndex).getDenomination())
                        .suit(packOfCards.get(cardIndex).getSuit())
                        .inPlay(true)
                        .createdTime(now)
                        .updatedTime(now)
                        .build();

                cardRepository.save(cardEntity);

                cardIndex++;
                cardsAdded++;
            }
            player.setHand(hand);
        }

        Player activePlayer = playerList.get(random.nextInt(playerList.size()));
        PlayerEntity activePlayerEntity = playerRepository.findByPlayerAliasAndGameEntity(activePlayer.getPlayerAlias(), savedGame);
        savedGame.setActivePlayerEntity(activePlayerEntity);

        return gameEntityMapper.mapGame(gameRepository.save(savedGame));
    }

    private void validateGameConstraints(Game requestedGame) {

        if (!isValidTeamSize(requestedGame.getTeamBlue()) || !isValidTeamSize(requestedGame.getTeamRed())) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Number of players not valid");
        }

        if (requestedGame.getTeamBlue().getPlayers().size() != requestedGame.getTeamRed().getPlayers().size()) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Teams not balanced");
        }
    }

    private boolean isValidTeamSize(Team team) {
        return team.getPlayers().size() == 3 || team.getPlayers().size() == 4;
    }

    public List<Player> getPlayersList(String gameAlias) {
        GameEntity gameEntity = gameRepository.findByGameAliasAndActiveIsTrue(gameAlias);
        return playerEntityMapper.mapPlayers(playerRepository.findAllByGameEntity(gameEntity));
    }
}
