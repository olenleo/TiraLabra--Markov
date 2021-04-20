package noteReader;

import com.opencsv.CSVReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.Arrays;
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

    /**
     * TODO: Eriytä lue csv-toiminnallisuus omaan metodiin.
     *
     * @param filename MidiCSV-ohjelman tuottaman .csv-tiedoston nimi ilman
     * tiedostopäätettä
     *
     * @param trie trie-tietorakenne
     * @throws URISyntaxException
     * @throws FileNotFoundException
     * @throws IOException
     */
    public NoteReader(String filename, Trie trie) throws URISyntaxException, FileNotFoundException, IOException {

        URL res = getClass().getClassLoader().getResource(filename + ".csv");
        File file = Paths.get(res.toURI()).toFile();
        String absolutePath = file.getAbsolutePath();
        System.out.println(absolutePath);
        FileReader f = new FileReader(file);
        BufferedReader bufferedreader = new BufferedReader(f);
        CSVReader r = new CSVReader(bufferedreader);
        String[] record;
        ArrayDeque<Integer> pino = new ArrayDeque<>();

        while ((record = r.readNext()) != null) {
            if (record[2].contains("Note_")) {
                int note = Integer.valueOf(record[4].trim());
                if (!notesInUse[note]) {
                    notesInUse[note] = true;
                } else {
                    if (pino.size() < 12) {
                        pino.addLast(note);
                    } else {
                        int[] arr = ArrayUtils.toPrimitive(pino.toArray(new Integer[pino.size()]));
                        System.out.println(Arrays.toString(arr));
                        trie.insert(arr);
                        pino.removeFirst();
                    }
                    notesInUse[note] = false;
                }
            }
        }
    }
}
