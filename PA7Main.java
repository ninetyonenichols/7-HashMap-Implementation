
/**
 * AUTHOR: Justin Nichols
 * FILE: PA7Main.java
 * ASSIGNMENT: Programming Assignment 7 - HashMapImpl
 * COURSE: CSC210; Section D; Spring 2019
 * PURPOSE: Accepts as input the name of a CSV file, a required command-line  
 *              argument ("LOCATION", "CATCOUNT", and "DEBUG" are only valid choices),
 *              as well as an optional command-line argument (provided iff 
 *              required command is "LOCATION", in which case, the optional
 *              argument will be the name of a job-category).
 *          If optional cmd is "DEBUG", prints information about the MyHashMap obj
 *          	used to implement PA7Main.
 *          Otherwise, prints the number of jobs that pertain to the given query (i.e. all
 *              jobs within a certain category, or all jobs within a certain
 *              category that are also at a certain location). 
 *              
 * 
 * USAGE: 
 * java PA7Main infile COMMAND optional
 * 
 * where 'COMMAND' and 'optional' are both Strings (described in the 'Purpose' 
 *     section), and 'infile' is the name of an input file of the following 
 *     format: 
 *     
 * 
 *  EXAMPLE INPUT--
 *      Input File:                       
 *  ---------------------------------------------------------------------
 *  | Company,Title,Category,Location,Responsibilities,Minimum \        |
 *  |     Qualifications,Preferred Qualifications                       |
 *  | Google,TitleA,CategoryX,Tel Aviv,Everything and the rest, BS, MS  |
 *  | Google,TitleB,CategoryX,Tel Aviv,Everything and the rest, BS, MS  |
 *  | Google,TitleB,CategoryY,Houston,Everything and the rest, BS, MS   |
 *  | Google,TitleC,CategoryX,Jonesboro,Everything and the rest, BS, MS | 
 *  | 6 this_is_an_error_line                                           |
 *  | errors are okay as_long_as_they consist                           |
 *  | of 6_strings_or_fewer separated                                   |
 *  | by whitespace                                                     |
 *  ---------------------------------------------------------------------
 *
 * Note: the '\' in the example above indicates that both the line which
 *           precedes it and the line that follow should actually just be
 *           one line. 
 * 
 * Apart from that. the format of the input file must match the format 
 *     shown above.
 *     
 * No support exists for any further commands
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;



public class PA7Main {
    final static int NUM_FIELDS = 7;

    public static void main(String[] args) {
        Scanner infile = getInfile(args);

        // checking command
        String command = args[1];
        Set<String> validCommands = new HashSet<String>();
        validCommands.add("CATCOUNT");
        validCommands.add("LOCATIONS");
        validCommands.add("DEBUG");

        // (making map & printing results) iff command valid
        if (validCommands.contains(command)) {
            MyHashMap<String, Integer> queryToCount = buildMap(infile, args);
            printResults(args, queryToCount);
        } else {
            System.out.println("Invalid Command");
            System.exit(1);
        }
    }

    /*
     * Gets the input file and makes sure it opens
     * 
     * @param String[] args, the command-line arguments
     * 
     * @return Scanner infile, the input file
     */
    public static Scanner getInfile(String[] args) {
        String fname = args[0];

        Scanner infile = null;
        try {
            infile = new Scanner(new File(fname));
        } catch (FileNotFoundException fileNotFound) {
            fileNotFound.printStackTrace();
            System.exit(1);
        }
        return infile;
    }

    /*
     * Builds the map from the queried value to the resulting count (i.e. from
     * the desired category to the number of jobs within that category)
     * 
     * @param Scanner infile, the input file
     * 
     * @param String[] args, the command-line arguments
     * 
     * @return MyHashMap<String, Integer> queryToCount, the map described above
     */
    public static MyHashMap<String, Integer> buildMap(Scanner infile,
            String[] args) {
        String command = args[1];
        MyHashMap<String, Integer> queryToCount = 
        		new MyHashMap<String, Integer>();

        String line = infile.nextLine();
        while (infile.hasNextLine()) {
            line = infile.nextLine();
            String[] infoList = line.split(",");
            if (infoList.length != NUM_FIELDS) {
                continue;
            }

            // building the map
            // choosing what the keys should be, based on the command
            String query = "";
            if (command.equals("CATCOUNT") || command.equals("DEBUG")) {
                query = infoList[2];
            } else if (args[2].equals(infoList[2])) {
                // args[2] is the desired cat
                // infoList[2] is the actual cat
                query = infoList[3];
            } else {
                continue;
            }
            Integer count = queryToCount.get(query);
            queryToCount.put(query, (count == null) ? 1 : count + 1);
        }
        return queryToCount;
    }

    /*
     * Prints the results of the query
     * 
     * @param String[] args, the command-line arguments
     * 
     * @param MyHashMap<String, Integer> queryToCount, a map from the queried
     * value to the number of jobs that match it
     */
    public static void printResults(String[] args,
            MyHashMap<String, Integer> queryToCount) {
        // printing header
        String command = args[1];
        if (command.equals("DEBUG")) {
            queryToCount.printTable();
            return;
        } else if (command.equals("CATCOUNT")) {
            System.out.println("Number of positions for each category");
        } else {
            String cat = args[2];
            System.out.println("LOCATIONS for category: " + cat);
        }
        System.out.println("-------------------------------------");

        // sorting results
        ArrayList<String> sortedQueries = new ArrayList<String>(
                queryToCount.keySet());
        Collections.sort(sortedQueries);

        // printing results
        for (String query : sortedQueries) {
            String count = queryToCount.get(query).toString();
            System.out.println(query + ", " + count);
        }
    }
}

