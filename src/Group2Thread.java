/*
 * Group2Thread.java
 *
 *
 */


import java.util.List;

/**
 * Each thread of this type compares a computed hash against all others in the database
 *
 * @author Dylan Bannon <drb2857@rit.edu>
 */
public class Group2Thread extends Thread {

    private String lineFromFile;
    private List<PasswordTuple> computedHashes;

    public Group2Thread(String lineFromFile, List<PasswordTuple> computedHashes) {
        this.lineFromFile = lineFromFile;
        this.computedHashes = computedHashes;
    }

    @Override
    public void run() {
        String[] line = lineFromFile.split("\\s+");
        String user = line[0];
        String hash = line[1];
        for(PasswordTuple pt : computedHashes) {
            if(pt.getHash().equals(hash)) {
                System.out.println(user + " " + pt.getPassword());
            }
        }
        try {
            wait();
        } catch (InterruptedException e) {
            System.err.println(e);
            System.exit(1);
        }
    }
}
