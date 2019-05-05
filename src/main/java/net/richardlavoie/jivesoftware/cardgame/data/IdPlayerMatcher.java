package net.richardlavoie.jivesoftware.cardgame.data;

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
