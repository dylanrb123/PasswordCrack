/*
 * Group1Thread.java
 *
 */

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Each thread of this type computes a single hash of a password to compare to the
 * stolen hashes
 *
 * @author Dylan Bannon <drb2857@rit.edu>
 */
public class Group1Thread extends Thread {

    /** the string that this thread will hash (potential password). */
    private String wordToHash;
    /** synchronized list of computed hashes   */
    private List<PasswordTuple> computedHashes;

    public Group1Thread(String s, List<PasswordTuple> computedHashes) {
        wordToHash = s;
        this.computedHashes = computedHashes;
    }

    @Override
    public void run() {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance ("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            System.err.println(e);
            System.exit(1);
        }
        byte[] data = null;
        try {
            data = wordToHash.getBytes ("UTF-8");
        } catch (UnsupportedEncodingException e) {
            System.err.println(e);
            System.exit(1);
        }
        for (int i = 0; i < 1000; i++) {
            if(data != null) {
                md.update (data);
                data = md.digest();
            }
        }
        String computedHash = null;
        try {
            computedHash = new String(data,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            System.err.println(e);
            System.exit(1);
        }
        computedHashes.add(new PasswordTuple(wordToHash,computedHash));
        notifyAll();
    }
}
