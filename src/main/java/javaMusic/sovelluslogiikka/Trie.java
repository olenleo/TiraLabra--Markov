package javaMusic.sovelluslogiikka;


/**
 * Luokka luo Trie-tietorakneteen joka tallentaa nuottisarjoja periaattella 1) taulukon indeksi on nuotin korkeus ja 2) taulukon sisältö on kyseisen nuotin esiintymisten lukumäärä.
 * @author Leo Niemi
 */
public class Trie {

    private TrieNode root;

    public Trie() {
        root = new TrieNode(0);
    }

    
    /**
     * Metodi lisää nuottisarjan trieen tallentaen jokaisen nuotin esiintymisfrekvenssin taulukkoon 
     * @param arrayOfNotes 
     */
    public void insert(int[] arrayOfNotes) {
       
        TrieNode node = root;
        for (int i = 0; i < arrayOfNotes.length; i++) {
            int note = arrayOfNotes[i];
            TrieNode[] arr = node.getChildren();
            if (arr[note] == null) {
                TrieNode temp = new TrieNode(i);
                arr[note] = temp;
                arr[note].increaseFreq();
                node = temp;
            } else {
                arr[note].increaseFreq();
                node = arr[note];
            }
        }
        node.setEnd();
    }

    public boolean startsWith(int[] prefix) {
        TrieNode node = searchNode(prefix);
        return node != null;
    }

    public boolean search(int[] word) {
        TrieNode node = searchNode(word);
        if  (node == null) {
            return false;
        } else {
            if  (node.isEnd()) {
                return true;
            }
        }

        return false;
    }

    public TrieNode searchNode(int[] s) {
        TrieNode node = root;
        for (int i = 0; i < s.length; i++) {
            int note = s[i];
            if  (node.getChildren()[note] != null) {
                node = node.getChildren()[note];
            } else {
                return null;
            }
        }
        if  (node == root) {
            return null;
        }
        return node;
    }
}
