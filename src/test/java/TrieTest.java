
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaMusic.sovelluslogiikka.Note;
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
    private Note[] list1;
    private Note[] olematonList;

    public TrieTest() throws IOException {
        this.emptyTrie = new Trie();
        this.trie = new Trie();
        this.list1 = new Note[]{new Note(67, 221640, 221760), new Note(65, 221760, 222720), new Note(82, 222720, 223200), new Note(79, 223200, 223440), new Note(80, 223440, 223560)};
        this.olematonList = new Note[]{new Note(51, 480, 960), new Note(52, 960, 1440), new Note(53, 1440, 1920), new Note(54, 1920, 2400), new Note(55, 2400, 2880)};

        try {
            NoteReader notereader = new NoteReader("fbts", trie);
        } catch (URISyntaxException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
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
    public void OlematonHakusanaPalauttaaNull() {
        String vastaus = "null";
        trie.search(olematonList);
        assertEquals("null", vastaus);

    }

    @Test
    public void HakuTyhjastaTriestaPalauttaaNull() {
        String vastaus = "null";
        emptyTrie.search(list1);
        assertEquals("null", vastaus);
    }

    @Test
    public void HakuLoytaaSananTriesta() {
        String vastaus = "Contains key: true";
        trie.search(list1);
        assertEquals("Contains key: true", vastaus);
    }

}
