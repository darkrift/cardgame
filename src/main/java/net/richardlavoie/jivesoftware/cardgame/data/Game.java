package net.richardlavoie.jivesoftware.cardgame.data;

import net.richardlavoie.jivesoftware.cardgame.data.visitor.DeckVisitor;
import net.richardlavoie.jivesoftware.cardgame.random.RandomSource;
import net.richardlavoie.jivesoftware.cardgame.service.game.exception.PlayerNotFoundException;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * This class is responsible to hook the players and the shoe together
 */
public class Game {

    private final List<Player> players = new LinkedList<>();

    private String id;

    private GameDeck gameDeck = new GameDeck();

    public Game(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void addDeckToShoe(Deck deck) {
        gameDeck.addDeck(deck);
    }

    public void shuffleDeck(RandomSource source) {
        gameDeck.shuffle(source);
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public Player removePlayer(PlayerMatcher matcher) {
        Iterator<Player> iter = players.iterator();
        while (iter.hasNext()) {
            Player p = iter.next();
            if (matcher.test(p)) {
                iter.remove();
                return p;
            }
        }
        return null;
    }

    public void visitDeck(DeckVisitor visitor) {

        gameDeck.accept(visitor);
    }

    private Player findPlayer(String playerId) {
        for (Player p : players) {
            if (p .getId().equals(playerId)) {
                return p;
            }
        }
        return null;
    }


    public List<Card> dealCardsTo(String playerId, int numCards) throws PlayerNotFoundException {
        Player player = findPlayer(playerId);

        if (player == null) {
            throw new PlayerNotFoundException(playerId);
        }

        List<Card> cards = gameDeck.pick(numCards);
        player.addCards(cards);

        return cards;
    }
}
