/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package noteReader;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.sound.midi.InvalidMidiDataException;

import org.jfugue.midi.MidiFileManager;
import org.jfugue.midi.MidiParserListener;
import org.jfugue.parser.ParserListenerAdapter;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;
import org.jfugue.theory.Note;

/**
 *
 * @author Leo Niemi
 */
public class NoteReader extends ParserListenerAdapter {

    private List<Note> notes;

    public NoteReader() throws IOException, InvalidMidiDataException {
        Player player = new Player();
        notes = new ArrayList<>();
        URL midURL = getClass().getResource("/ABC.mid");
        String fileName = midURL.toString().substring(6);
        Pattern pattern = MidiFileManager.loadPatternFromMidi(new File(fileName));

        try {
            pattern = MidiFileManager
                    .loadPatternFromMidi(new File(fileName));
        } catch (IOException ex) {
        } catch (InvalidMidiDataException ex) {
        }
        System.out.println("\nPattern: " + pattern
                + "\nT100: Tempo 100 BPM"
                + "\nV0: MIDI channel 1"
                + "\nC6qa71: C6 quarter-note at 71 velocity"
                + "\nD6a80: D6 quarter-note at 80 velocity"
                + "\nE6ha90: D6 half-note at 90 velocity"
                + "\nF6ha101: F6 half-note at 101 velocity");

        player.play(pattern);
    }


    public void noteEvent(Note note) {
        if (!notes.contains(note)) {
            notes.add(note);
        }
    }

    
}
