package javaMusic.sovelluslogiikka;

import java.util.HashMap;
import java.util.Map;


/**
 * Luokka toteuttaa trie-puun lisäys- ja hakutoiminnot.
 * Hyvin alustava toteutus, lue: 
 * https://github.com/olenleo/TiraLabra--Markov/blob/main/Viikkoraportti2.md
 * Tulen jatkossa siis kirjoittamaan tämän luokan puhtaaksi ilman muistiinpanoja. 
 * 
 * @author Leo Niemi
 */
public class Trie {
    Map<Character, Trie> children;
    boolean storesKey;
    

    public Trie(String key) {
        this(key, 0);
    }

    public Trie() {
        children = new HashMap<>();
        storesKey = false;
    }

    private Trie(String key, int charIndex) {
        children = new HashMap<>();
        if (charIndex >= key.length()) {
            storesKey = true;
        } else {
            storesKey = false;
            Character character = key.charAt(charIndex);
            children.put(character, new Trie(key, charIndex + 1));
        }
    }

    public Trie add(String key) {
        return this.add(key, 0);
    }

    public Trie add(String key, int charIndex) {
        if (charIndex < key.length()) {
            Character character = key.charAt(charIndex);
            if (children.containsKey(character)) {
                return children.get(character).add(key, charIndex + 1);
            } else {
                children.put(character, new Trie(key, charIndex + 1));
                return this;
            }
        } else if (charIndex == key.length()) {
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

    public Trie search(String key) {
        if (key.isEmpty()) {
            return storesKey ? this : null;
        } else {
            Character character = key.charAt(0);
            if (children.containsKey(character)) {
                return children.get(character).search(key.substring(1));
            } else {
                return null;
            }
        }
    }
    @Override
    public String toString() {
        return "Contains key: " + this.storesKey;
        
    }
}
