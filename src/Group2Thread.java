/*
 * Group2Thread.java
 *
 *
 */


/**
 * Each thread of this type compares a computed hash against all others in the database
 *
 * @author Dylan Bannon <drb2857@rit.edu>
 */
public class Group2Thread extends Thread {

    private String hashToCheck;
    private String computedHash;

    public Group2Thread(String hashFromFile) {
        hashToCheck = hashFromFile;
    }
}
