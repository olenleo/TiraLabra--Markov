
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaMusic.sovelluslogiikka.Trie;
import noteReader.NoteReader;
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
public class TrieTest {

    private Trie trie;
    private Trie emptyTrie;
    private int[] list1, olematonList;

    private static NoteReader notereader;

    public TrieTest() throws IOException {
        this.list1 = new int[]{36, 36, 36, 36, 36, 36, 36, 36, 38, 38, 38, 38};
        this.olematonList = new int[]{1, 2, 3, 4, 5};
        this.emptyTrie = new Trie(12);
        this.trie = new Trie(12);
        try {
            notereader = new NoteReader("repeats", trie,12);
            notereader.read();
        } catch (URISyntaxException | FileNotFoundException ex) {
            Logger.getLogger(TrieTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void olematonHakusanaPalauttaaNull() {

        assertFalse(trie.search(olematonList));

    }

    @Test
    public void hakuTyhjastaTriestaPalauttaaNull() {
        assertFalse(emptyTrie.search(list1));
    }

    @Test
    public void hakuLoytaaSananTriesta() {
        assertTrue(trie.search(list1));
    }

    @Test
    public void prefixHakuLoytaaPrefixin() {
        int[] correctPrefix = {36, 36};
        assertTrue(trie.startsWith(correctPrefix));
    }

    @Test
    public void prefixHakuEiPalutaVirheellistaPrefixia() {
        int[] falsePrefix = {1, 1};
        assertFalse(trie.startsWith(falsePrefix));
    }

}
