package javaMusic.sovelluslogiikka;

import java.util.Arrays;
import java.util.Random;

public class Arpoja {

    private Random r = new Random();
    private double num = 0;

    public Arpoja(Trie trie) {

    }

    public void addFrequenciesForChildren(TrieNode node) {
        int sum = 0;
        for (int i = 0; i < 127; i++) {
            sum += node.getChildren()[i].getFreq();
        }
    }

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
     *
     * @param root
     * @param freqArray
     * @param depth
     * @return
     *
     *
     */
    public String[] generateSequence(TrieNode root, String[] freqArray, int depth) {
        num = r.nextDouble();
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
                return generateSequence(root.getChildren()[i], freqArray, depth + 1);
            }
        }
        return freqArray;
    }

}
