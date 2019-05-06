package net.richardlavoie.jivesoftware.cardgame.rest.controller;

import net.richardlavoie.jivesoftware.cardgame.data.Card;
import net.richardlavoie.jivesoftware.cardgame.data.Deck;
import net.richardlavoie.jivesoftware.cardgame.data.Game;
import net.richardlavoie.jivesoftware.cardgame.data.Player;
import net.richardlavoie.jivesoftware.cardgame.random.JavaRandomSource;
import net.richardlavoie.jivesoftware.cardgame.rest.controller.game.GameData;
import net.richardlavoie.jivesoftware.cardgame.rest.controller.game.PlayerData;
import net.richardlavoie.jivesoftware.cardgame.rest.controller.game.PlayerSummaryData;
import net.richardlavoie.jivesoftware.cardgame.rest.visitor.CountUndealtCardsPerSuitVisitor;
import net.richardlavoie.jivesoftware.cardgame.rest.visitor.UndealtCardsValuePerSuit;
import net.richardlavoie.jivesoftware.cardgame.service.deck.DeckService;
import net.richardlavoie.jivesoftware.cardgame.service.deck.exception.DeckServiceException;
import net.richardlavoie.jivesoftware.cardgame.service.game.GameService;
import net.richardlavoie.jivesoftware.cardgame.service.game.exception.GameServiceException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class GameController {

    private final GameService gameService;

    private final DeckService deckService;


    public GameController(GameService service, DeckService deckService) {
        this.gameService = service;
        this.deckService = deckService;
    }

    @PostMapping("/games")
    public String createGame() {
        return gameService.createGame().getId();
    }

    @GetMapping("/games")
    public List<Game> listGames() {
        return gameService.getGames();
    }

    @GetMapping("/games/{id}")
    public GameData getGame(@PathVariable String id) throws GameServiceException {
        Game game = gameService.getGame(id);
        GameData gd = new GameData(game.getId());
        return gd;
    }

    @DeleteMapping("/games/{id}")
    public String deleteGame(@PathVariable String id) throws GameServiceException {
        gameService.deleteGame(id);
        return id;
    }

    @GetMapping("/games/{gameId}/players")
    public List<PlayerSummaryData> listOfPlayers(@PathVariable("gameId") String gameId) throws GameServiceException {
        List<Player> players = gameService.getPlayers(gameId);
        List<PlayerSummaryData> summary = new ArrayList<>();
        for (Player p : players) {
            int totalValue = p.getCards().stream().map(card -> card.getValue()).reduce(0, (sum, value) -> sum + value);
            summary.add(new PlayerSummaryData(p.getId(), p.getName(), totalValue));
        }

        summary.sort(
                (PlayerSummaryData o1, PlayerSummaryData o2) -> Integer.compare(o2.value, o1.value)
        );

        return summary;
    }

    @PostMapping("/games/{gameId}/players")
    public Player addPlayer(@PathVariable("gameId") String gameId, @RequestBody PlayerData data) throws GameServiceException {
        // TODO Should we deal cards to this newly added player ?
        return gameService.addPlayer(gameId, new Player(UUID.randomUUID().toString(), data.name));
    }

    @DeleteMapping("/games/{gameId}/players/{playerId}")
    public Player removePlayer(@PathVariable("gameId") String gameId, @PathVariable("playerId") String playerId) throws GameServiceException {
        return gameService.removePlayer(gameId, playerId);
    }

    @GetMapping("/games/{gameId}/players/{playerId}/cards")
    public List<Card> listCardsOfPlayer(@PathVariable("gameId") String gameId, @PathVariable("playerId") String playerId) throws GameServiceException {
        return gameService.getCards(gameId, playerId);
    }

    @PostMapping("/games/{gameId}/players/{playerId}/dealCards")
    public List<Card> dealCards(
            @PathVariable("gameId") String gameId,
            @PathVariable("playerId") String playerId,
            @RequestParam(value = "numCards", defaultValue = "1") int numCards
    ) throws GameServiceException
    {
        return gameService.dealCards(gameId, playerId, numCards);
    }

    @PostMapping("/games/{gameId}/shoe")
    public Deck addDeckToGame(@PathVariable("gameId") String gameId, @RequestParam("deckId") String deckId) throws GameServiceException, DeckServiceException {
        Game game = gameService.getGame(gameId);
        Deck deck = deckService.pickDeck(deckId);

        game.addDeckToShoe(deck);

        return deck;
    }

    @PostMapping("/games/{gameId}/shuffle")
    public void shuffle(@PathVariable("gameId") String gameId) throws GameServiceException, DeckServiceException {
        Game game = gameService.getGame(gameId);

        game.shuffleDeck(new JavaRandomSource());
    }


    @GetMapping("/games/{gameId}/undealtCardsPerSuit")
    public List<CountUndealtCardsPerSuitVisitor.SuitCardStatistic> getCardsLeftPerSuit(@PathVariable("gameId") String gameId) throws GameServiceException {
        CountUndealtCardsPerSuitVisitor visitor = new CountUndealtCardsPerSuitVisitor();
        gameService.getGame(gameId).visitDeck(visitor);
        return visitor.computeStats();
    }

    @GetMapping("/games/{gameId}/undealtCardsValuePerSuit")
    public List<UndealtCardsValuePerSuit.CardsPerSuitData> getCardsLeftPerSuitAndValue(@PathVariable("gameId") String gameId) throws GameServiceException {
        UndealtCardsValuePerSuit visitor = new UndealtCardsValuePerSuit();
        gameService.getGame(gameId).visitDeck(visitor);
        return visitor.computeStats();
    }
}
