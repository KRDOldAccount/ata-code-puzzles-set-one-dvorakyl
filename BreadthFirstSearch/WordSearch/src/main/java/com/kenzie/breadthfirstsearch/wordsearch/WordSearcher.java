package com.kenzie.breadthfirstsearch.wordsearch;

import com.kenzie.breadthfirstsearch.wordsearch.sharedmodel.Coordinate;
import com.kenzie.breadthfirstsearch.wordsearch.sharedmodel.Direction;

import java.util.*;
import java.util.stream.Collectors;

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

        HashMap<String, Long> answer = new HashMap<>();
//        Coordinate start = new Coordinate(0,0);
        List<String> listOfWords = new ArrayList<>();
        listOfWords = this.wordSearch.getWordsToFind();



        for (String wordToSearch: listOfWords) {
            answer.put(wordToSearch, bfsHelper(wordToSearch));
        }

        return answer;
    }

    public long bfsHelper(String s) {
        long wordCount2 = 0;
        for (int i = 0; i < wordSearch.getHeight(); i++) {
            for (int j = 0; j < wordSearch.getWidth(); j++) {
                Coordinate coordinate = new Coordinate(i, j);
                wordCount2 += bfs(s, coordinate);

            }
        }
        return wordCount2;
    }


    public long bfs(String word, Coordinate origin) {
        long wordCount = 0;
        int depth = 0;

        Queue<Coordinate> currentTreeLevel = new LinkedList();
        Queue<Coordinate> nextTreeLevel = new LinkedList<>();

        //add the root of the tree to start everything off
        currentTreeLevel.add(origin);

        while(!currentTreeLevel.isEmpty()) {

            Coordinate currentCoordinate = currentTreeLevel.poll();

            char expectedCharacter = word.charAt(depth);
            char actualCharacter = wordSearch.getCharacterAt(currentCoordinate);

            if(actualCharacter == expectedCharacter) {
                if (depth == word.length() - 1) {
                    wordCount++;
                } else {
                    nextTreeLevel.addAll(getNeighbors(currentCoordinate));
                }
            }

            if (currentTreeLevel.isEmpty()) {
                currentTreeLevel.addAll(nextTreeLevel);
                nextTreeLevel.clear();
                depth++;
            }
        }


        return wordCount;
    }

    private Set<Coordinate> getNeighbors (Coordinate origin) {
        return Direction.ALL_DIRECTIONS.stream()
                .map(direction -> direction.addToCoordinate(origin))
                .filter(coordinate -> {
                    int row = coordinate.getRow();
                    int col = coordinate.getColumn();
                    return row >= 0 && row < wordSearch.getHeight()
                            && col >= 0 && col < wordSearch.getWidth();
                })
                .collect(Collectors.toSet());
    }


}
