package net.richardlavoie.jivesoftware.cardgame.data;

/**
 * Player matcher implementation which finds a player from it's id
 */
public class IdPlayerMatcher implements PlayerMatcher {

    private final String id;

    public IdPlayerMatcher(String id) {
        this.id = id;
    }

    @Override
    public boolean test(Player player) {
        return player.getId().equals(id);
    }
}
