package javaMusic.noteReader;

import com.opencsv.CSVReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaMusic.sovelluslogiikka.Note;
import javaMusic.sovelluslogiikka.Trie;

/**
 * Luokka lukee esiformatoidun .csv-tiedoston ja muuttaa sen käyttökelpoiseksi
 * informaatioksi.
 *
 * @author Leo Niemi
 */
public class NoteReader {

    private final int[] noteStartTimes = new int[127];
    private final String filename;
    private final Trie trie;
    private final int len;
    private int track;
    private int division;
    private int firstNoteOffset;
    private int lastSeenNoteStart;
    private int lastSeenNoteEnd;
    private ArrayDeque<Note> noteStack;
    private boolean offsetHasBeenSet;

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
    public NoteReader(String filename, Trie trie, int len, int track) throws URISyntaxException, FileNotFoundException, IOException {

        this.offsetHasBeenSet = false;
        this.filename = filename;
        this.trie = trie;
        this.len = len;
        this.firstNoteOffset = 0;
        this.track = track;
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
            int previous = 0;

            while ((record = r.readNext()) != null) {
                String command = record[2];
                if (command.contains("Header")) {
                    this.division = Integer.valueOf(record[5].trim());
                }
                if (command.contains("Note_") && Integer.valueOf(record[0]) == track) {
                    if (!offsetHasBeenSet) {
                        setOffset(record);
                        lastSeenNoteStart = 1;
                        noteStartTimes[Integer.valueOf(record[4].trim())] = 1; // ensimmäinen nuotti alkaa aina ajasta 1.
                        offsetHasBeenSet = true;
                    } else {
                        // Offset on löydetty, joten käsitellään apumetodin avulla nuotit yksitellen.
                        previous = noteMethod(record, previous);
                    }
                }
                if (command.contains("End_track")) {
                    lastSeenNoteEnd = Integer.valueOf(record[1].trim());

                }
            }
            System.out.println("****************************\n\nFile read succesfully\n\n****************************");
        } catch (IOException ex) {
            Logger.getLogger(NoteReader.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private int noteMethod(String[] record, int previousNoteEnded) {
        Note note;
        int absoluteTime = Integer.valueOf(record[1].trim()) - firstNoteOffset + 1; // + 1 koska nuotti 1 voi alkaa ajassa 0.
        int notePitch = Integer.valueOf(record[4].trim());

        if (noteOperationIsEnd(record)) {
            if (lastSeenNoteEnd == absoluteTime) {
                note = new Note(notePitch, absoluteTime - noteStartTimes[notePitch]);
                insertToStack(note);
            } else {
                note = new Note(notePitch, absoluteTime - noteStartTimes[notePitch], absoluteTime - previousNoteEnded);
                noteStartTimes[notePitch] = 0;
                insertToStack(note);
                lastSeenNoteEnd = absoluteTime;
            }
        } else if (noteOperationIsStart(record)) {
            noteStartTimes[notePitch] = absoluteTime;
            lastSeenNoteStart = absoluteTime;
        }
        return lastSeenNoteEnd;

    }

    /**
     * Metodi tallettaa muistiin alkupisteen offset-muuttujaan nuottikestojen
     * siistimistä varten ja asettaa nuotin käyttöön.
     *
     * @param record
     */
    private void setOffset(String[] record) {
        int absoluteTime = Integer.valueOf(record[1].trim());
        if (noteOperationIsStart(record)) {
            this.firstNoteOffset = absoluteTime;
            System.out.println("First note offset: " + this.firstNoteOffset);

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
        return noteOp.equals("Note_on_c") && velocity > 0;
    }

    private boolean noteOperationIsEnd(String[] record) {
        String noteOp = record[2].trim();
        int velocity = Integer.valueOf(record[5].trim());
        return noteOp.equals("Note_off_c") || (noteOp.equals("Note_on_c") && velocity == 0);
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
