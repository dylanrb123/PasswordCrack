/*
 * Group1Thread.java
 *
 *
 *
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

    /** the string that this thread will hash. */
    private String wordToHash;

    public Group1Thread(String s) {
        wordToHash = s;
    }

    @Override
    public void run() {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance ("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            System.err.println(e);
        }
        byte[] data = null;
        try {
            data = wordToHash.getBytes ("UTF-8");
        } catch (UnsupportedEncodingException e) {
            System.err.println(e);
        }
        for (int i = 0; i < 1000; i++) {
            if(data != null) {
                md.update (data);
                data = md.digest();
            }

        }
    }
}
