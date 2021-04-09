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
import javaMusic.sovelluslogiikka.Note;
import javaMusic.sovelluslogiikka.Trie;

/**
 * Luokka lukee esiformatoidun .csv-tiedoston ja muuttaa sen käyttökelpoiseksi
 * informaatioksi.
 *
 *  * @author Leo Niemi
 */
public class NoteReader {

    private int[] notes = new int[127];
    private boolean[] notesInUse = new boolean[127];
    ArrayDeque<Note> pino;
    private int nuottisarjanPituus = 5; // trieen tallennettavien sarjojen pituus
    private Trie trie;
    
    /**
     * TODO: Eriytä lue csv-toiminnallisuus omaan metodiin. 
     * 
     * @param filename MidiCSV-ohjelman tuottaman .csv-tiedoston nimi ilman tiedostopäätettä
     * @param trie trie-tietorakenne
     * @throws URISyntaxException
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public NoteReader(String filename, Trie trie) throws URISyntaxException, FileNotFoundException, IOException {
        this.pino = new ArrayDeque<>();
        this.trie = trie;
        URL res = getClass().getClassLoader().getResource(filename + ".csv");
        File file = Paths.get(res.toURI()).toFile();

        String absolutePath = file.getAbsolutePath();
        System.out.println(absolutePath);
        FileReader f = new FileReader(file);
        BufferedReader bufferedreader = new BufferedReader(f);
        CSVReader r = new CSVReader(bufferedreader);
        String[] record;

        while ((record = r.readNext()) != null) {
            int track = Integer.valueOf(record[0]);
            if (record[2].contains("Note_")) {
                int timestamp = Integer.parseInt(record[1].trim());
                String command = record[2];
                int note = Integer.valueOf(record[4].trim());
                if (!notesInUse[note]) {
                    notes[note] = timestamp;
                    notesInUse[note] = true;
                } else {
                    Note lisattava = new Note(note, notes[note], timestamp);
                    lisaaNuottiPinoon(lisattava);
                    notesInUse[note] = false;
                }
            }
        }
    }
/**
 * Metodi pilkkoo nuottien sarjan trieen tallennettavan sanan mittaisiksi osiksi.
 * 
 * @param note Note-olio.
 */
    private void lisaaNuottiPinoon(Note note) {
        // pino on pienempi kuin sanan pituus
        if (pino.size() < nuottisarjanPituus) {
            pino.add(note);
        } else {
        //  sana valmis, lisää trieen.
            Note[] array = pino.toArray(new Note[pino.size()]);
            System.out.println(Arrays.toString(array));
            trie.add(array);
            pino.removeFirst();
            pino.add(note);
        }
    }

}
