package javaMusic.sovelluslogiikka;

import java.util.Arrays;
import java.util.Random;
import noteReader.NoteReader;

/**
 * Trie-rakenteen solmu.
 *
 * @author Leo Niemi
 */
public class TrieNode {

    private TrieNode[] children;
    private boolean end = false;
    private int depth;
    private int freq;
    private Note note;

    /**
     * Jokainen TrieNode sisältää viitteen lapsiinsa.
     *
     * @param depth Solmun syvyys tietorakenteessa
     * @param position
     * @param note
     */
    public TrieNode(int depth, int position, Note note) {
        this.children = new TrieNode[127];
        this.depth = depth;
        this.freq = 0;
        this.note = note;
        if (this.depth == position - 1) {
            this.end = true;
        }

    }

    public boolean isEnd() {
        return end;
    }

    public void increaseFreq() {
        this.freq++;
    }

    public int getFreq() {
        return freq;
    }

    public int getDepth() {
        return depth;
    }

    public void setEnd() {
        this.end = true;
    }

    public TrieNode[] getChildren() {
        return children;
    }

    public TrieNode(Note note) {
        this.children = new TrieNode[127];
        this.note = note;
    }

    public Note getNote() {
        return note;
    }

    @Override
    public String toString() {
        return "" + this.freq + " , " + this.isEnd();
    }

}
