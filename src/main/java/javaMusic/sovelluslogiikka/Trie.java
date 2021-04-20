package javaMusic.sovelluslogiikka;

import java.util.Arrays;
import java.util.Random;

/**
 * Luokka luo Trie-tietorakneteen joka tallentaa nuottisarjoja periaattella 1)
 * taulukon indeksi on nuotin korkeus ja 2) taulukon sisältö on kyseisen nuotin
 * esiintymisten lukumäärä.
 *
 * @author Leo Niemi
 */
public class Trie {

    private TrieNode root;
    private Random r;
    private double num;

    public Trie() {
        root = new TrieNode(0);
        r = new Random();
        num = r.nextDouble();
    }

    /**
     * Metodi lisää nuottisarjan trieen tallentaen jokaisen nuotin
     * esiintymisfrekvenssin taulukkoon
     *
     * @param arrayOfNotes Taulukollinen koknaislukuja välillä 0-127
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
        if (node == null) {
            return false;
        } else {
            if (node.isEnd()) {
                return true;
            }
        }

        return false;
    }

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
     * Metodi tulostaa trien syvyyshaun avulla. Testausta varten
     * @param root
     * @param freqArray
     * @param depth 
     */
    public void printTrie(TrieNode root, String[] freqArray, int depth) {
        if (root.isEnd()) {
            System.out.println(Arrays.toString(freqArray));
        }
        for (int i = 0; i < 126; i++) {
            if (root.getChildren()[i] != null) {
                freqArray[depth] = "N:" + i + " F:" + Integer.toString(root.getChildren()[i].getFreq());
                printTrie(root.getChildren()[i], freqArray, depth + 1);
            }
        }
    }
    /**
     * Metodi arpoo yhden nuottisarjan
     * @param root
     * @param freqArray
     * @param depth
     * @return 
     */
    public String[] printASequence(TrieNode root, String[] freqArray, int depth) {

        double sumOfOdds = 0;
        if (root.isEnd()) {
            System.out.println(Arrays.toString(freqArray) + ",");
            return freqArray;
        }

        double[] odds = new double[127];
        for (int i = 0; i < 126; i++) {
            if (root.getChildren()[i] != null) {
                odds[i] = root.getChildren()[i].getFreq();
                sumOfOdds += odds[i];
            }
        }

        double edellinen = odds[0];
        for (int i = 1; i < odds.length; i++) {
            if (odds[i] != 0) {
                double nykyinen = odds[i] / sumOfOdds;
                odds[i] = (double) (edellinen + nykyinen);
                edellinen = odds[i];
            }
        }

        for (int i = 0; i < odds.length; i++) {
            if (num <= odds[i] && odds[i] > 0) {
                freqArray[depth] = Integer.toString(i);
                num = r.nextDouble();
                return printASequence(root.getChildren()[i], freqArray, depth + 1);
            }
        }
        return freqArray;
    }

}
