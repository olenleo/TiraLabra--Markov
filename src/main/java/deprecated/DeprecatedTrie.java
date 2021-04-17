package deprecated;

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
public class DeprecatedTrie {

    Map<Note, DeprecatedTrie> children;
    Map<Note[], Integer> timesObserved;
    boolean storesKey;
    

    public DeprecatedTrie(Note[] noteArray) {
        this(noteArray, 0);
    }

    public DeprecatedTrie() {
        children = new HashMap<>();
        storesKey = false;
        timesObserved = new HashMap<>();

    }

    private DeprecatedTrie(Note[] noteArray, int noteIndex) {
        children = new HashMap<>();
        if (noteIndex >= noteArray.length) {
            storesKey = true;
        } else {
            storesKey = false;
            Note note = noteArray[noteIndex];
            children.put(note, new DeprecatedTrie(noteArray, noteIndex + 1));
        }
    }

    public DeprecatedTrie add(Note[] noteArray) {
        if (timesObserved.get(noteArray) == null) {
            timesObserved.put(noteArray, 1);
        } else {
            System.out.println("HEP");
            timesObserved.put(noteArray, timesObserved.get(noteArray) + 1);
        }
        return this.add(noteArray, 0);
    }

    public DeprecatedTrie add(Note[] noteArray, int noteIndex) {
        if (noteIndex < noteArray.length) {
            Note note = noteArray[noteIndex];
            if (children.containsKey(note)) {
                return children.get(note).add(noteArray, noteIndex + 1);
            } else {
                children.put(note, new DeprecatedTrie(noteArray, noteIndex + 1));
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

    public DeprecatedTrie search(Note[] noteArray) {
        if (noteArray.length == 0) {
            return storesKey ? this : null;
        } else {
            Note note = noteArray[0];
            if (children.containsKey(note)) {
                return children.get(note).search(Arrays.copyOfRange(noteArray, 1, noteArray.length));
            } else {
                
                return null;
            }
        }
    }

    public Map<Note, DeprecatedTrie> getChildren() {
        return this.children;
    }

    public Map<Note[], Integer> getTimesObserved() {
        return this.timesObserved;
    }
    @Override
    public String toString() {
        return "Contains note: " + this.storesKey;
    }
}
