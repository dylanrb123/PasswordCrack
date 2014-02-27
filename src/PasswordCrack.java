/*
 * PasswordCrack.java
 *
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main file, spawns threads
 *
 * @author Dylan Bannon <drb2857@rit.edu>
 */
public class PasswordCrack {

    /**
     * The main method
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Print usage message if incorrect number of args
        if(args.length != 2) {
            System.err.println("Usage: PasswordCheck dictionary db");
            System.exit(1);
        }
        String dictionaryFileName = args[0];
        String dbFileName = args[1];

        ArrayList<Group1Thread> group1Threads = new ArrayList<Group1Thread>();
        ArrayList<Group2Thread> group2Threads = new ArrayList<Group2Thread>();

        PasswordTable pTable = new PasswordTable();

        File dictFile = new File(dictionaryFileName);
        File dbFile = new File(dbFileName);

        Scanner dictScanner = null;
        Scanner dbScanner = null;

        // construct the scanners
        try{
            dictScanner = new Scanner(dictFile);
            dbScanner = new Scanner(dbFile);
        } catch (FileNotFoundException e){
            e.printStackTrace();
            System.exit(1);
        }

        // build group 2 threads
        while(dictScanner.hasNextLine()) {
            group1Threads.add(new Group1Thread(dictScanner.nextLine(),pTable));
        }

        // build group 2 threads
        int numGroup2Threads = 0;
        while(dbScanner.hasNextLine()) {
            if(group2Threads.size() == 0) {
                group2Threads.add(new Group2Thread(dbScanner.nextLine(),pTable,null));
            } else {
                group2Threads.add(new Group2Thread(dbScanner.nextLine(),pTable, group2Threads.get(numGroup2Threads-1)));
            }
            numGroup2Threads++;
        }

        pTable.setMaxEntries(group1Threads.size());

        // Start the threads
        for(Thread t : group2Threads) {
            t.start();
        }
        for(Thread t : group1Threads) {
           t.start();
        }

        dictScanner.close();
        dbScanner.close();
    }
}
