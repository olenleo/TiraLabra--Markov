package noteReader;

import com.opencsv.CSVReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaMusic.sovelluslogiikka.Note;
import javaMusic.sovelluslogiikka.Trie;
import org.apache.commons.lang3.ArrayUtils;

/**
 * Luokka lukee esiformatoidun .csv-tiedoston ja muuttaa sen käyttökelpoiseksi
 * informaatioksi.
 *
 *  * @author Leo Niemi
 */
public class NoteReader {

    private int[] notesInUse = new int[127];
    private String filename;
    private Trie trie;
    private int len;
    private int division;

    /**
     * Midi-tiedoston otsakkeen division-arvo vastaa aikayksikköjen lukumäärää
     * yhtä neljäsosanuottia kohti. Tätä tarvitaan temmon käsittelyssä.
     *
     * @return positiivinen kokonaisluku.
     */
    public int getDivision() {
        System.out.println("ACc getDivision ret " + this.division );
        return this.division;
    }

    /**
     * Luokka hallinnoi formatoidun csv-tiedoston sisältämän MIDI-informaation
     * tallennuksen trieen.
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
     * Metodi lukee formatoidun csv-tiedoston ja lisää nuottisarjoja trieen.
     */
    public void read() {
        try {
            BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/" + filename + ".csv")));
            CSVReader r = new CSVReader(bufferedreader);
            String[] record;
            ArrayDeque<Note> pino = new ArrayDeque<>();
            int lastNoteEndedAt = -1;
            while ((record = r.readNext()) != null) {
                String command = record[2];
                if (command.contains("Header")) {
                    this.division = Integer.valueOf(record[5].trim());
                            }
                if (command.contains("Note_")) {

                    int absoluteTime = Integer.valueOf(record[1].trim()) + 1;
                    int notePitch = Integer.valueOf(record[4].trim());

                    if (notesInUse[notePitch] == 0) {
                        notesInUse[notePitch] = absoluteTime;
//                        System.out.println("Note " + notePitch + " start at " + (absoluteTime - 1));
                        if (lastNoteEndedAt > 0 && lastNoteEndedAt < absoluteTime - 1) {
//                            System.out.println("There was a rest for " + (absoluteTime - lastNoteEndedAt - 1) + " units");
                            Note note = new Note(absoluteTime - notesInUse[notePitch] - 1, true);
                        }
                    } else {
//                         System.out.println("Note " + notePitch + " end at " + (absoluteTime - 1));
                        Note note = new Note(notePitch, absoluteTime - notesInUse[notePitch] - 1, false);

                        lastNoteEndedAt = absoluteTime - 1;
                        if (pino.size() < len) {
                            pino.addLast(note);
                        } else {
                            ArrayList<Note> noteList = new ArrayList<>();
                            pino.forEach(n -> {
                                noteList.add(n);
                            });
                            Note[] array = noteList.toArray(new Note[0]);
                            trie.insert(array);
                            pino.removeFirst();
                        }

                        notesInUse[notePitch] = 0;
                    }

                }
            }
            
        } catch (IOException ex) {
            Logger.getLogger(NoteReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
