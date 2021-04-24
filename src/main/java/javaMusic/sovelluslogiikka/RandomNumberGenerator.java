package javaMusic.sovelluslogiikka;

/**
 * Luokka arpoo satunnaisia liukulukuja (Käsittääkseni linear congruential generator).
 *
 * @author Leo Niemi
 */
public class RandomNumberGenerator {

    private int max;
    private double last;

    public RandomNumberGenerator() {
        this.max = 1000;
        this.last = System.currentTimeMillis() % max;
    }

    /**
     * 
     * Kiinnostava artikkeli aiheesta: https://arxiv.org/pdf/2001.05304.pdf.
     * 
     * @return Liukuluku välillä 0-1 kolmen desimaalin tarkkuudella.
     */
    public double nextDouble() {
        last = (last * 0xfb85) % 4096;
        return (last % max) / 1000;

    }
}
