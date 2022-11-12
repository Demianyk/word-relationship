package org.ddemianyk.words.wordrelationship.exception;

public class WordPairAlreadyExistsException extends RuntimeException {
    public WordPairAlreadyExistsException(String word1, String word2) {
        super("Relationship between words " + word1 + " and " + word2 + " already exists");
    }
}
