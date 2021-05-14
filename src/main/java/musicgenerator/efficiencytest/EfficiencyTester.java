package musicgenerator.efficiencytest;

import java.io.IOException;
import java.net.URISyntaxException;
import musicgenerator.note.reader.NoteReader;
import musicgenerator.sovelluslogiikka.MarkovGenerator;
import musicgenerator.sovelluslogiikka.Trie;

/**
 * Luokka tulostaa nuottisarjojen generoinnin keskivertoaikoja millisekunneissa.
 *
 * @author Leo Niemi
 */
public class EfficiencyTester {

    Trie trieWithLengthOf10;
    Trie trieWithLengthOf50;
    Trie trieWithLengthOf100;
    MarkovGenerator generator;
    private final int n = 1000000;
    private long[] times = new long[n];
    private long t;

    public EfficiencyTester() {
        this.generator = new MarkovGenerator(960);
    }

    public void run() {
        try {
            testTrieWithLength10();
            testTrieWithLength50();
            testTrieWithLength100();
            System.out.println("");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void testTrieWithLength10() throws URISyntaxException, IOException {
        trieWithLengthOf10 = new Trie(10);
        times = new long[n];
        NoteReader reader = new NoteReader("cello_suite_1007", trieWithLengthOf10, 10, 2);
        reader.read();
        for (int i = 0; i < n; i++) {

            t = System.nanoTime();
            generator.generateSequence(trieWithLengthOf10.getRoot(), new String[10], 0);
            t = System.nanoTime() - t;
            times[i] = t;
        }
        System.out.print(getAverage(times));

    }

    public void testTrieWithLength50() throws URISyntaxException, IOException {
        trieWithLengthOf50 = new Trie(50);
        times = new long[n];

        NoteReader reader = new NoteReader("cello_suite_1007", trieWithLengthOf50, 50, 2);
        reader.read();
        for (int i = 0; i < n; i++) {
            t = System.nanoTime();
            generator.generateSequence(trieWithLengthOf50.getRoot(), new String[50], 0);
            t = System.nanoTime() - t;
            times[i] = t;
        }
        System.out.print("\t" + getAverage(times));

    }

    public void testTrieWithLength100() throws URISyntaxException, IOException {
        times = new long[n];
        trieWithLengthOf100 = new Trie(100);
        NoteReader reader = new NoteReader("cello_suite_1007", trieWithLengthOf100, 100, 2);
        reader.read();
        for (int i = 0; i < n; i++) {
            t = System.nanoTime();
            generator.generateSequence(trieWithLengthOf100.getRoot(), new String[100], 0);
            t = System.nanoTime() - t;
            times[i] = t;
        }
        System.out.print("\t" + getAverage(times));
    }

    private double getAverage(long[] times) {
        double s = 0;
        for (long time : times) {
            s += time;
        }
        return s / times.length;
    }

}
