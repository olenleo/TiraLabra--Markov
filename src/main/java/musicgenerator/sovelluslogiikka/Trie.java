package musicgenerator.sovelluslogiikka;

/**
 * Luokka luo Trie-tietorakneteen joka tallentaa nuottisarjoja periaattella 1)
 * taulukon indeksi on nuotin korkeus ja 2) taulukon sisältö on kyseisen nuotin
 * esiintymisten lukumäärä.
 *
 * @author Leo Niemi
 */
public class Trie {

    private TrieNode root;
    private int division = 480; // Melko turvallinen luku oletusarvoksi

    /**
     * Konstruktorissa luodaan trien juuri.
     *
     * @param len haluttu nuottisarjojen pituus
     */
    public Trie(int len) {
        root = new TrieNode(0, len, null);
    }

    /**
     * Metodi lisää nuottisarjan trieen tallentaen jokaisen nuotin
     * esiintymisfrekvenssin taulukkoon.
     *
     * @param arrayOfNotes Taulukollinen nuotteja
     */
    public void insert(Note[] arrayOfNotes) {
        TrieNode node = root;
        for (int i = 0; i < arrayOfNotes.length; i++) {
            int note = arrayOfNotes[i].getPitch();
            TrieNode[] arr = node.getChildren();

            if (arr[note] == null) {
                TrieNode temp = new TrieNode(arrayOfNotes[i]);
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

    /**
     * Tällä hetkellä tarpeeton hakumetodi.
     *
     * @param prefix kokonaisen nuottisarjan osamäärä.
     * @return Kyseinen nuottisarja löytyy triestä.
     */
    public boolean startsWith(int[] prefix) {
        TrieNode node = searchNode(prefix);
        return node != null;
    }

    /**
     * Tällä hetkellä tarpeeton hakumetodi.
     *
     * @param word Nuottisarja
     * @return Kyseinen nuottisarja löytyy triestä, ja sen päättävä nuotti on
     * lehti.
     */
    public boolean search(int[] word) {
        TrieNode node = searchNode(word);
        if (node == null) {
            return false;
        } else {
            if (node.isEnd()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Tällä hetkellä tarpeeton hakumetodi etsii TrieNoden.
     *
     * @param s
     * @return TrieNode on
     */
    public TrieNode searchNode(int[] s) {
        TrieNode node = root;
        for (int i = 0; i < s.length; i++) {
            int note = s[i];
            if (node.getChildren()[note] != null) {
                node = node.getChildren()[note];
            } else {
                return null;
            }
        }
        if (node == root) {
            return null;
        }
        return node;
    }

    public TrieNode getRoot() {
        return root;
    }

    /**
     * Asetetaan midi-tiedostosta luettu division-arvo.
     *
     * @param division Neljäsosanuottia vastaava aikayksiköiden lukumäärä.
     */
    public void setDivision(int division) {
        this.division = division;
    }
}
