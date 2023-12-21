package org.example;

import java.util.*;

public class DocumentVectorization {
    public static Map<String,Integer> createBagOfWords(List<List<String>> tokenizedData) {
        Map<String, Integer> bagOfWords = new HashMap<>();

        //Create our vocabulary with all unique tokens
        Set<String> vocabulary = new HashSet<>();
        for (List<String> document : tokenizedData) {
            vocabulary.addAll(document);
        }

        // Initialize the bag of words with zeros
        for (String word : vocabulary) {
            bagOfWords.put(word, 0);
        }

        // Update token counts in the bag of words
        for (List<String> document : tokenizedData) {
            for (String word : document) {
                bagOfWords.put(word, bagOfWords.get(word) + 1);
            }
        }

        return bagOfWords;
    }
    public static double[] getDocumentVector(List<String> document, Map<String, Integer> bagOfWords) {
        double[] documentVector = new double[bagOfWords.size()];

        for (int i = 0; i < documentVector.length; i++) {
            String word = new ArrayList<>(bagOfWords.keySet()).get(i);
            int count = bagOfWords.get(word);

            // Calculate Term Frequency
            long termFrequency = document.stream().filter(token -> token.equals(word)).count();
            double tf = (double) termFrequency / document.size();

            documentVector[i] = tf * count;
        }

        return documentVector;
    }
}