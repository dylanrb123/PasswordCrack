/*
 * Group1Thread.java
 *
 * File:
 *   $Id$
 *
 * Revisions:
 *   $Log$
 *
 */

/**
 * Each thread of this type computes a single hash of a password to compare to the
 * stolen hashes
 *
 * @author Dylan Bannon <drb2857@rit.edu>
 */
public class Group1Thread extends Thread {

    /** the string that this thread will hash. */
    private String wordToHash;

    public Group1Thread(String s) {
        wordToHash = s;
    }

    @Override
    public void run() {

    }
}
