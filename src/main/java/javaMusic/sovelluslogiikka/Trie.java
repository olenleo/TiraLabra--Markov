package javaMusic.sovelluslogiikka;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Luokka toteuttaa trie-puun lisäys- ja hakutoiminnot. Hyvin alustava toteutus,
 * lue:
 * https://github.com/olenleo/TiraLabra--Markov/blob/main/Viikkoraportti2.md
 * Tulen jatkossa siis kirjoittamaan tämän luokan puhtaaksi ilman
 * muistiinpanoja.
 *
 * @author Leo Niemi
 */
public class Trie {

    Map<Note, Trie> children;
    boolean storesKey;

    public Trie(Note[] noteArray) {
        this(noteArray, 0);
    }

    public Trie() {
        children = new HashMap<>();
        storesKey = false;
    }

    private Trie(Note[] noteArray, int noteIndex) {
        children = new HashMap<>();
        if (noteIndex >= noteArray.length) {
            storesKey = true;
        } else {
            storesKey = false;
            Note note = noteArray[noteIndex];
            children.put(note, new Trie(noteArray, noteIndex + 1));
        }
    }

    public Trie add(Note[] noteArray) {
        return this.add(noteArray, 0);
    }

    public Trie add(Note[] noteArray, int noteIndex) {
        if (noteIndex < noteArray.length) {
            Note note = noteArray[noteIndex];
            if (children.containsKey(note)) {
                return children.get(note).add(noteArray, noteIndex + 1);
            } else {
                children.put(note, new Trie(noteArray, noteIndex + 1));
                return this;
            }
        } else if (noteIndex == noteArray.length) {
            if (this.storesKey) {
                return null;
            } else {
                this.storesKey = true;
                return this;
            }
        } else {
            throw new IllegalArgumentException("Ongelma indeksin kanssa: " + noteIndex + ", " + noteArray);
        }
    }

    public Trie search(Note[] noteArray) {
        if (noteArray.length == 0) {
            return storesKey ? this : null;
        } else {
            Note note = noteArray[0];
            System.out.println("Etsitään " + note);
            if (children.containsKey(note)) {
                return children.get(note).search(Arrays.copyOfRange(noteArray, 1, noteArray.length));
            } else {
                
                return null;
            }
        }
    }

    public Map<Note, Trie> getChildren() {
        return this.children;
    }

    @Override
    public String toString() {
        return "Contains note: " + this.storesKey;

    }

}
