/*
 * PasswordCrack.java
 *
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
        // synchronized list to keep track of hashes to check
        List<PasswordTuple> computedHashes = Collections.synchronizedList(new ArrayList<PasswordTuple>());

        File dictFile = new File(dictionaryFileName);
        File dbFile = new File(dbFileName);
        Scanner dictScanner = null;
        Scanner dbScanner = null;

        // construct the scanners
        try{
            dictScanner = new Scanner(dictFile);
            dbScanner = new Scanner(dbFile);
        } catch (FileNotFoundException e){
            System.err.println(e);
        }

        while(dictScanner.hasNextLine()) {
            group1Threads.add(new Group1Thread(dictScanner.nextLine(),computedHashes));
        }

        while(dbScanner.hasNextLine()) {
            group2Threads.add(new Group2Thread(dbScanner.nextLine(),computedHashes));
        }

        dictScanner.close();
        dbScanner.close();
    }
}
