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

    /**
     * Shuffle the deck
     * @param source Random source for the shuffling randomness.
     */
    public void shuffleDeck(RandomSource source) {
        gameDeck.shuffle(source);
    }

    /**
     * Add a player to this game.
     * @param player Player to add
     */
    public void addPlayer(Player player) {
        players.add(player);
    }

    /**
     * Get the associated player of this game.
     * @return List of players
     */
    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    /**
     * Remove players found be the given player matcher
     * @param matcher Player matcher to find the player to remove
     * @return Player to remove
     */
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

    /**
     * Call the given visitor with each card from the shoe
     * @param visitor Visitor to call with each card
     */
    public void visitDeck(DeckVisitor visitor) {

        gameDeck.accept(visitor);
    }

    /**
     * Find a player from it's id.
     * @param playerId Id of the player
     * @return Player found from it's id
     */
    private Player findPlayer(String playerId) {
        for (Player p : players) {
            if (p .getId().equals(playerId)) {
                return p;
            }
        }
        return null;
    }

    /**
     * Deal cards to a give player, found by it's id.
     *
     * @param playerId Player id to deal cards to
     * @param numCards Number of cards to deal to this player
     * @return List of cards dealt
     * @throws PlayerNotFoundException If the player was not found
     */
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
