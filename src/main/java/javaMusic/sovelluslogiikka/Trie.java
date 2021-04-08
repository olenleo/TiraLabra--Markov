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

    public Trie(Note[] key) {
        this(key, 0);
    }

    public Trie() {
        children = new HashMap<>();
        storesKey = false;
    }

    private Trie(Note[] key, int charIndex) {
        children = new HashMap<>();
        if (charIndex >= key.length) {
            storesKey = true;
        } else {
            storesKey = false;
            Note character = key[charIndex];
            children.put(character, new Trie(key, charIndex + 1));

            System.out.println(character);
        }
    }

    public Trie add(Note[] key) {
        return this.add(key, 0);
    }

    public Trie add(Note[] key, int charIndex) {
        if (charIndex < key.length) {
            Note character = key[charIndex];
            if (children.containsKey(character)) {
                return children.get(character).add(key, charIndex + 1);
            } else {
                children.put(character, new Trie(key, charIndex + 1));
                return this;
            }
        } else if (charIndex == key.length) {
            if (this.storesKey) {
                return null;
            } else {
                this.storesKey = true;
                return this;
            }
        } else {
            throw new IllegalArgumentException("Ongelma indeksin kanssa: " + charIndex + ", " + key);
        }
    }

    public Trie search(Note[] key) {
        if (key.length == 0) {
            return storesKey ? this : null;
        } else {
            Note character = key[0];
            System.out.println("Etsitään " + character);
            if (children.containsKey(character)) {
                System.out.println("On childreneitä");
                return children.get(character).search(Arrays.copyOfRange(key, 1, key.length));
            } else {
                return null;
            }

        }
    }

    public Map<Note, Trie> getChildren() {
        return this.children;
    }

    public static boolean keyIsEmpty(Note[] key) {

        for (Note key1 : key) {
            if (key1.getNote() != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Contains key: " + this.storesKey;

    }

}
