/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import musicgenerator.note.reader.NoteReader;
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
 * @author 35850
 */
public class NoteReaderTest {

    private Trie trie;
    private NoteReader noteReader;
    private int len = 6;

    public NoteReaderTest() {
        trie = new Trie(len);

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
            noteReader = new NoteReader("repeats", trie, 6, 1);
            noteReader.read();
        } catch (URISyntaxException | IOException ex) {
            Logger.getLogger(NoteReaderTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @After
    public void tearDown() {
    }

    @Test
    public void correctFrequencyFoundInTrie() {
        TrieNode root = trie.getRoot();
        assertTrue(root.getChildren()[36].getFreq() == 7);
    }
    
}
