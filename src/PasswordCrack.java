/*
 * PassswordCrack.java
 *
 * File:
 *   $$Id$
 *
 * Revisions:
 *   $$Log$
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

        // Read the dictionary file
        ArrayList<String> dictPasswords = new ArrayList<String>();
        ArrayList<String> dbHashes = new ArrayList<String>();
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
            dictPasswords.add(dictScanner.nextLine());
        }

        while(dbScanner.hasNextLine()) {
            dbHashes.add(dbScanner.nextLine());
        }

        dictScanner.close();
        dbScanner.close();
    }
}
