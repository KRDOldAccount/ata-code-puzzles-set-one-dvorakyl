package com.kenzie.breadthfirstsearch.wordsearch;

import com.kenzie.breadthfirstsearch.wordsearch.sharedmodel.Coordinate;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.kenzie.breadthfirstsearch.wordsearch.SampleWordSearches.SORE_SEARCH;

/**
 * Class for completing word search puzzles.
 */
public class WordSearcher {
    private final WordSearch wordSearch;

    /**
     * Create a word search instance for the provided problem.
     * @param wordSearch - the word search puzzle to solve
     */
    public WordSearcher(WordSearch wordSearch) {
        this.wordSearch = wordSearch;
    }

    /**
     * Main method for manual testing.
     *
     * @param args - unused
     */
    public static void main(String[] args) {
        WordSearcher wordSearcher = new WordSearcher(SORE_SEARCH);

        System.out.println(wordSearcher.calculateWordCounts());
    }

    /**
     * Calculate how many ways (permutations) you can use the letters in the puzzle to spell
     * each word provided as part of the puzzle.
     *
     * @return a Map of the desired words, and how many ways (permutations) you can use the letters in the puzzle to
     * spell each word provided as part of the puzzle.
     */
    public Map<String, Long> calculateWordCounts() {

//        HashMap<String, Long> returnCounts = new HashMap<>();
//        List<String> wordsToFind = wordSearch.getWordsToFind();
//
//        for (String word : wordsToFind) {
//            returnCounts.put(word, 0L);
//        }
//
//        for (int i = 0; i < wordSearch.getHeight(); i++) {
//            for (int j = 0; j < wordSearch.getWidth(); j++) {
//                Coordinate coordinate = new Coordinate(i, j);
//                for (String word : wordsToFind) {
//                    return goThroughWord(word, returnCounts, wordSearch, coordinate, 0);
//                }
//            }
//        }

        return Collections.emptyMap();
    }
}
