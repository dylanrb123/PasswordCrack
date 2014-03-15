/*
 * PasswordTable.java
 *
 */

import java.util.HashMap;

/**
 * A synchronized HashMap. Putting causes a notifyAll, getting requires a wait.
 *
 * @author Dylan Bannon <drb2857@rit.edu>
 */
public class PasswordTable {

    /** Stores the computed hashes, key = hash, value = password */
    private HashMap<String, String> theTable;
    /** The maximum number of hashes that can be in the table */
    private int maxEntries;

    /**
     * Constructs a new PasswordTable, initializes the HashMap
     */
    public PasswordTable() {
        theTable = new HashMap<String, String>();
    }

    /**
     * puts an entry in the map and wakes up all waiting threads.
     *
     * @param computedHash a hash computed by a Group1Thread.
     * @param password the original string that got hashed.
     * @throws InterruptedException
     */
    public synchronized void put(String computedHash, String password) 
            throws InterruptedException {
        theTable.put(computedHash, password);
        notifyAll();
    }

    /**
     * Returns the password corresponding to a given hash, if present. Else returns null.
     * @param hash the hash to check
     * @return the corresponding password or null
     * @throws InterruptedException
     */
    public synchronized String get(String hash) throws InterruptedException {
        // wait condition, if there is nothing in the table or the size is less than the maximum,
        // as would happen if all of the passwords haven't been calculated yet.
        while(theTable.size() == 0 || theTable.size() < maxEntries) wait();
        // may need notifyAll() if it finds a match?
        return theTable.get(hash);

    }


    /**
     * Sets the max size of the map
     *
     * @param maxEntries the max size
     */
    public void setMaxEntries(int maxEntries) {
        this.maxEntries = maxEntries;
    }
}
