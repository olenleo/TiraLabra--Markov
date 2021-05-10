/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import musicgenerator.note.reader.NoteReader;
import musicgenerator.sovelluslogiikka.MarkovGenerator;
import musicgenerator.sovelluslogiikka.RandomNumberGeneratorForTesting;
import musicgenerator.sovelluslogiikka.Trie;
import musicgenerator.sovelluslogiikka.TrieNode;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Leo Niemi
 */
public class MarkovGeneratorTest {

    private Trie trie;
    private NoteReader noteReader;
    private RandomNumberGeneratorForTesting rng;
    private MarkovGenerator markovGenerator;
    private int len = 12;
    private int amount = 3;
    private String[] freqArray;
    private String[][] data;
    private TrieNode root;

    public MarkovGeneratorTest() {
        trie = new Trie(len);
        rng = new RandomNumberGeneratorForTesting();
        freqArray = new String[len];
        data = new String[amount][len];
        root = trie.getRoot();
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        try {
            noteReader = new NoteReader("solobach", trie, len, 1);
            noteReader.read();
            markovGenerator = new MarkovGenerator(noteReader.getDivision(), rng);

        } catch (URISyntaxException | IOException ex) {
            Logger.getLogger(NoteReaderTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @After
    public void tearDown() {
    }

    @Test
    public void correctSequencesAreGenerated() {
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < amount; j++) {
                String[] s = markovGenerator.generateSequence(root, freqArray, 0);
                data[j][i] = s[i];
            }
        }

        assertTrue(data[0][0].equals("[47,0.25,0.25]"));
        assertTrue(data[0][1].equals("[43,0.25,0.25]"));
        assertTrue(data[0][2].equals("[40,1.0,1.0]"));
        assertTrue(data[0][3].equals("[38,1.0,2.0]"));

    }

}
