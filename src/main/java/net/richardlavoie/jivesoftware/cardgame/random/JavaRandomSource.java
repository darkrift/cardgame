package net.richardlavoie.jivesoftware.cardgame.random;

import java.util.Random;

/**
 * Random source which uses a java.util.Random object as it's backend.
 */
public class JavaRandomSource implements RandomSource {

    private Random random = new Random();

    public int nextInt(int bound) {
        return random.nextInt(bound);
    }
}
