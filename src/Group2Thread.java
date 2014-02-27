/*
 * Group2Thread.java
 */

/**
 * Each thread of this type compares a given hash against all computed hashes in the database.
 *
 * @author Dylan Bannon <drb2857@rit.edu>
 */
public class Group2Thread extends Thread {

    /** A line from the database file in format: [user] [hashed password] */
    private String lineFromFile;
    /** The synchronized map of hashes to compare against. Key == hash, value == original password */
    private PasswordTable tableOfHashes;
    /** Reference to previous Group2Thread that was created */
    private Group2Thread previousThread;

    /**
     * Constructs a Group2Thread
     *
     * @param lineFromFile line from the database file, format: [user] [hashed password]
     * @param tableOfHashes Synchronized map of computed hashes, key == hash, value == original password
     * @param previousThread Reference to previous thread, null if this is first thread
     */
    public Group2Thread(String lineFromFile, PasswordTable tableOfHashes, Group2Thread previousThread) {
        this.lineFromFile = lineFromFile;
        this.tableOfHashes = tableOfHashes;
        this.previousThread = previousThread;

    }

    /*
     * Compares the hash value from the database and prints the resulting username and password
     * if it finds a match
     *
     */
    @Override
    public void run() {
        String[] line = lineFromFile.split("\\s+");
        String user = line[0];
        String hash = line[1];
        String password = "";
        try {
            password = tableOfHashes.get(hash);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Password would be null if not in database
        if(password != null) {

            try {
                // ensure the proper order of output. previousThread will be null if this is the first one.
                if(previousThread != null) {
                    previousThread.join();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(user + " " + password);
        }
    }
}
