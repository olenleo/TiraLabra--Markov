package noteReader;

import com.opencsv.CSVReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.ArrayDeque;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaMusic.sovelluslogiikka.Trie;
import org.apache.commons.lang3.ArrayUtils;

/**
 * Luokka lukee esiformatoidun .csv-tiedoston ja muuttaa sen käyttökelpoiseksi
 * informaatioksi.
 *
 *  * @author Leo Niemi
 */
public class NoteReader {

    private boolean[] notesInUse = new boolean[127];
    private String filename;
    private Trie trie;
    private int len;

    /**
     * TODO: Eriytä lue csv-toiminnallisuus omaan metodiin.
     *
     * @param filename MidiCSV-ohjelman tuottaman .csv-tiedoston nimi ilman
     * tiedostopäätettä
     *
     * @param trie Käytettävä trie-tietorakenne
     * @param len Arvottujen nuottisarjojen pituus
     * @throws URISyntaxException
     * @throws FileNotFoundException
     * @throws IOException
     */
    public NoteReader(String filename, Trie trie, int len) throws URISyntaxException, FileNotFoundException, IOException {
        this.filename = filename;
        this.trie = trie;
        this.len = len;
    }
    /**
     * Metodi lukee esiformatoidun csv-tiedoston ja lisää nuottisarjoja trieen.
     */
    public void read() {
        try {
            BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/" + filename + ".csv")));
            CSVReader r = new CSVReader(bufferedreader);
            String[] record;
            ArrayDeque<Integer> pino = new ArrayDeque<>();

            while ((record = r.readNext()) != null) {
                if (record[2].contains("Note_")) {
                    int note = Integer.valueOf(record[4].trim());
                    if (!notesInUse[note]) {
                        notesInUse[note] = true;
                    } else {
                        if (pino.size() < len) {
                            pino.addLast(note);
                        } else {
                            int[] arr = ArrayUtils.toPrimitive(pino.toArray(new Integer[pino.size()]));
                            trie.insert(arr);
                            pino.removeFirst();
                        }
                        notesInUse[note] = false;
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(NoteReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
