package musicgenerator.sovelluslogiikka;

/**
 * Tämä RNG palauttaa aina saman numeron.
 *
 * @author Leo Niemi
 */
public class RandomNumberGeneratorForTesting implements RNG {

    public RandomNumberGeneratorForTesting() {
    }

    public double nextDouble() {
        return 0.156;
    }
}
