package net.richardlavoie.jivesoftware.cardgame.random;

/**
 * Simple interface to decouple ranom from it's native implementation so it's easier to test.
 */
public interface RandomSource {

    /**
     * Get the next random int from the defined bound, exclusive.
     *
     * @param bound Bound in which to find the next int
     * @return Random int
     */
    int nextInt(int bound);

}
