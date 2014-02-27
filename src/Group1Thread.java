/*
 * Group1Thread.java
 */

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Each thread of this type computes a single hash of a password to compare to the
 * stolen hashes
 *
 * @author Dylan Bannon <drb2857@rit.edu>
 */
public class Group1Thread extends Thread {

    /** the string that this thread will hash (potential password). */
    private String wordToHash;
    /** synchronized map of computed hashes: key == hash, value == original password. */
    private PasswordTable tableOfHashes;

    /**
     * Constructs a new Group2Thread
     *
     * @param s string to hash
     * @param tableOfHashes map of hashes
     */
    public Group1Thread(String s, PasswordTable tableOfHashes) {
        wordToHash = s;
        this.tableOfHashes = tableOfHashes;
    }

    /*
     * Computes a hash of the given string, adds it to the map.
     *
     */
    @Override
    public void run() {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance ("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.exit(1);
        }
        byte[] data = null;
        try {
            data = wordToHash.getBytes ("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            System.exit(1);
        }
        for (int i = 0; i < 100000; i++) {
            if(data != null) {
                md.update (data);
                data = md.digest();
            }
        }

        // convert from byte array back to string.
        StringBuilder hexVal = new StringBuilder();
        for(int i = 0; i < data.length; i++) {
            String hs = Integer.toHexString(0xFF & data[i]);
            if(hs.length() == 1) hexVal.append('0');
            hexVal.append(hs);
        }
        String computedHash = hexVal.toString();

        try {
            tableOfHashes.put(computedHash,wordToHash);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
