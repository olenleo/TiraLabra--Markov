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

/**
 * Luokka lukee esiformatoidun .csv-tiedoston ja muuttaa sen käyttökelpoiseksi
 * informaatioksi.
 *
 *  * @author Leo Niemi
 */
public class NoteReader {

    private int[] noteStartTimes = new int[127];
    private String filename;
    private Trie trie;
    private int len;
    private int division;
    private int firstNoteOffset;
    private int lastNoteEndedAt;
    private ArrayDeque<Note> noteStack;
    private boolean firstNote;

    /**
     * Midi-tiedoston otsakkeen division-arvo vastaa aikayksikköjen lukumäärää
     * yhtä neljäsosanuottia kohti. Tätä tarvitaan temmon käsittelyssä.
     *
     * @return positiivinen kokonaisluku.
     */
    public int getDivision() {
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
        this.firstNote = true;
        this.filename = filename;
        this.trie = trie;
        this.len = len;
        this.firstNoteOffset = 0;
    }

    /**
     * Metodi lukee formatoidun csv-tiedoston ja lisää nuottisarjoja trieen.
     */
    public void read() {
        try {
            BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/" + filename + ".csv")));
            CSVReader r = new CSVReader(bufferedreader);
            String[] record;
            noteStack = new ArrayDeque<>();

            while ((record = r.readNext()) != null) {
                String command = record[2];
                if (command.contains("Header")) {
                    this.division = Integer.valueOf(record[5].trim());
                }
                if (command.contains("Note_")) {
                    if (firstNote) {
                        firstNoteMethod(record);
                    } else {
                        noteMethod(record);
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(NoteReader.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Apumetodi tarkistaa onko midi-event nuotin alku vai loppu.
     *
     * @param record MidiCSV-tiedostosta luettu rivi
     */
    private void noteMethod(String[] record) {
        int absoluteTime = Integer.valueOf(record[1].trim()) - firstNoteOffset; // + 1 koska nuotti 1 voi alkaa ajassa 0.
        int notePitch = Integer.valueOf(record[4].trim());
        if (noteOperationIsStart(record)) {
            noteStartTimes[notePitch] = absoluteTime;

            if (absoluteTime - lastNoteEndedAt > 0) {
                Note restNote = new Note(absoluteTime - lastNoteEndedAt, true);
                insertToStack(restNote);
                lastNoteEndedAt = absoluteTime;
            }
        } else {
            int noteLength = absoluteTime - noteStartTimes[notePitch];
            noteStartTimes[notePitch] = 0;
            Note noteToAdd = new Note(notePitch, noteLength, false);
            lastNoteEndedAt = absoluteTime;
            insertToStack(noteToAdd);
        }
    }

    /**
     * Metodi etsii ensimmäisen nuotin alun, lopun ja tallettaa muistiin
     * alkupisteen offset-muuttujaan nuottikestojen siistimistä varten.
     *
     * @param record
     */
    private void firstNoteMethod(String[] record) {
        System.out.println(Arrays.toString(record));
        int absoluteTime = Integer.valueOf(record[1].trim());
        int notePitch = Integer.valueOf(record[4].trim());

        if (noteOperationIsStart(record)) {

            this.firstNoteOffset = absoluteTime;
            noteStartTimes[notePitch] = 0;
            System.out.println("Set offset to " + this.firstNoteOffset);
        } else {
            System.out.println("..?");
            Note first = new Note(notePitch, absoluteTime, false);
            insertToStack(first);
            lastNoteEndedAt = absoluteTime - firstNoteOffset;
            System.out.println("end @ " + lastNoteEndedAt);
            this.firstNote = false;
        }
    }

    /**
     * Apumetodi palauttaa true jos midi-komento on uuden nuotin alku.
     *
     * @param record
     * @return midi-komento on uuden nuotin alkupiste.
     */
    private boolean noteOperationIsStart(String[] record) {
        String noteOp = record[2].trim();
        int velocity = Integer.valueOf(record[5].trim());
        System.out.println("noteOperationIsStart\nnoteOp: " + noteOp + " vel: " + velocity);
        if (noteOp.equals("Note_off_c")) {
            return false;
        }
        return !(noteOp.equals("Note_on_c") && velocity == 0); // velocity == 0 ilmaisee nuotim päättymistä
    }

    /**
     * Metodi lisää nuotin pinoon. Kun pinon koko saavuttaa tietyn määreen pino
     * käännetään taulukoksi ja lisätään trieen.
     *
     * @param note Lisättävä nuotti
     */
    private void insertToStack(Note note) {
        if (noteStack.size() < len) {
            noteStack.addLast(note);
        } else {
            ArrayList<Note> noteList = new ArrayList<>();
            noteStack.forEach(n -> {
                noteList.add(n);
            });
            Note[] array = noteList.toArray(new Note[len]);
            trie.insert(array);
            noteStack.removeFirst();
        }
    }

}
