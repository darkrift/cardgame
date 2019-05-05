package net.richardlavoie.jivesoftware.cardgame.service.game;

import net.richardlavoie.jivesoftware.cardgame.data.Card;
import net.richardlavoie.jivesoftware.cardgame.data.Game;
import net.richardlavoie.jivesoftware.cardgame.data.IdPlayerMatcher;
import net.richardlavoie.jivesoftware.cardgame.data.Player;
import net.richardlavoie.jivesoftware.cardgame.service.game.exception.GameDeletionErrorException;
import net.richardlavoie.jivesoftware.cardgame.service.game.exception.GameNotFoundException;
import net.richardlavoie.jivesoftware.cardgame.service.game.exception.GameServiceException;
import net.richardlavoie.jivesoftware.cardgame.service.game.exception.TooManyGamesFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class GameService {

    // List of games available in the system
    private List<Game> games = new ArrayList<>();

    public synchronized Game createGame() {
        // TODO think of a way to import existing games if we restart the system
        Game game = new Game(UUID.randomUUID().toString());
        games.add(game);
        return game;
    }

    public synchronized List<Game> getGames() {
        return Collections.unmodifiableList(games);
    }

    private Game findGame(String id) throws GameServiceException {
        List<Game> matchingGames = games.stream().filter(g -> g.getId().equals(id)).collect(Collectors.toList());
        if (matchingGames.size() == 0) {
            throw new GameNotFoundException(id);
        } else if (matchingGames.size() > 1) {
            // TODO this should never happen since UUID.randomUUID() should return something unique...
            throw new TooManyGamesFoundException(id);
        }

        return matchingGames.get(0);
    }

    public synchronized Game deleteGame(String id) throws GameServiceException {
        Game deletedGame = findGame(id);
        if (!games.remove(deletedGame)) {
            throw new GameDeletionErrorException(id);
        }
        // TODO what do we do with decks associated to the carddeck of this game...
        // Should we return non-dirty/non-used card decks to the available repository so we can use them to other games ?
        return deletedGame;
    }

    public synchronized Player addPlayer(String gameId, Player player) throws GameServiceException {
        findGame(gameId).addPlayer(player);
        return player;
    }

    public synchronized Player removePlayer(String gameId, String playerId) throws GameServiceException {
        return findGame(gameId).removePlayer(new IdPlayerMatcher(playerId));
    }

    private Player findPlayerFrom(Game game, String playerId) {
        return null;
    }

    public Game getGame(String gameId) throws GameServiceException {
        return findGame(gameId);
    }

    public synchronized List<Card> getCards(String gameId, String playerId) throws GameServiceException {
        Game game = findGame(gameId);
        Player player = findPlayerFrom(game, playerId);

        return player.getCards();
    }

    public List<Card> dealCards(String gameId, String playerId, int numCards) throws GameServiceException {
        Game game = findGame(gameId);
        Player player = findPlayerFrom(game, playerId);

        List<Card> cards = game.takeCards(numCards);
        player.addCards(cards);

        return cards;
    }

    public List<Player> getPlayers(String gameId) throws GameServiceException {
        return findGame(gameId).getPlayers();
    }

}
