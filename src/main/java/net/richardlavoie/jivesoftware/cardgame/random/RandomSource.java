package net.richardlavoie.jivesoftware.cardgame.random;

/**
 * Simple interface to decouple ranom from it's native implementation so it's easier to test.
 */
public interface RandomSource {

    int nextInt(int bound);

}
