package javaMusic.sovelluslogiikka;
import java.util.Arrays;
/**
 * Trie-rakenteen solmu.
 * @author Leo Niemi
 */
public class TrieNode {

    private TrieNode[] children;
    private boolean end = false;
    private int depth;
    private int freq;
    
    /**
     * Jokainen TrieNode sisältää viitteen lapsiinsa.
     * @param depth Solmun syvyys tietorakenteessa
     */
    public TrieNode(int depth) {
        this.children = new TrieNode[127];
        this.depth = depth;
        this.freq = 0;
        if (this.depth == 5) {
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

    public TrieNode() {
        this.children = new TrieNode[127];
    }
    
    @Override
    public String toString() {
        return "" + this.freq + " Depth : " + this.depth;
    }



}
