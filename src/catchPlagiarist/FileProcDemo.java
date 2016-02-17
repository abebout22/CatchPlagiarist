package catchPlagiarist;

// imported java utilities
import java.util.Scanner;
import java.util.ArrayDeque;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * This is the class FileProcDemo, designed to catch plagiarists in inputted
 * documents
 *
 * @author alanbebout
 * @version Dec 4, 2015
 */
public class FileProcDemo
{
    /// Users/alanbebout/Desktop/CSE017/CSE17_F15_Project_DataSets/medium_set
    /**
     * the main method
     *
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args)
        throws FileNotFoundException
    {
        // the three primary modifiable inputs
        // commented out below are the non-command-line inputs
// int number = 6;
// int threshold = 200;
// File folder = new
// File("/Users/alanbebout/Desktop/CSE017/CSE17_F15_Project_DataSets/medium_set");

        // these here allow for command line
        int number = Integer.parseInt(args[1]);
        int threshold = Integer.parseInt(args[2]);
        ;
        File folder = new File(args[0]);

        // arrayList to hold the information regarding plagiarized documents
        ArrayList<cheaterObject> cheaters = new ArrayList<cheaterObject>();

        // int which will eventually hold the number of nGram similarities
        // two documents
        int similarities = 0;

        // ArrayList to hold all of the HashMapped documents
        ArrayList<customObject> dataBase = new ArrayList<customObject>();

        // this loop traverses the folder of files, calling the hashMaker
        // method on each, and then adding the resulting HashMap and the
        // title of the file to the dataBase arrayList
        for (File doc : folder.listFiles())
        {
            customObject co = new customObject(
                hashMaker(number, doc),
                doc.toString().substring(70));
            dataBase.add(co);
        }

        // some outputs
        System.out.println(dataBase.size() + " customObject items in dataBase");
        System.out.println("");

        // the outer loop, which iterates through the document HashMaps
        for (int n = 0; n < dataBase.size(); n++)
        {

            // next loop, which also iterates through HashMaps
            for (int o = n + 1; o < dataBase.size(); o++)
            {

                // reset for similarities after each inter-HashMap comparison
                similarities = 0;

                // the set of integer hashcodes, which will be used to compare
                // the strings
                Set<Integer> keySet = dataBase.get(n).hashmap.keySet();

                // the iterator used to traverse the set of hashcode keys
                Iterator<Integer> keySetIterator = keySet.iterator();

                // while loop to traverse the set with the iterator
                while (keySetIterator.hasNext())
                {
                    int key = keySetIterator.next();

                    // checking if any two hashcodes are common
                    if (dataBase.get(o).hashmap.containsKey(key))
                    {

                        // if so, increment similarities
                        similarities++;
                    }
                }

                // checking to see if the number of similarities between two
                // documents
                // exceeds a threshold above which the authors are considered
                // plagiarists
                if (similarities >= threshold)
                {
                    cheaterObject cheat1 = new cheaterObject(
                        dataBase.get(n).fileName,
                        dataBase.get(o).fileName,
                        similarities);
                    cheaters.add(cheat1);
                }
            }
        }

        // printing out all of the cheater info
        Collections.sort(cheaters);
        for (int x = 0; x < cheaters.size(); x++)
        {
            System.out.println(
                cheaters.get(x).fileName1 + " and " + cheaters.get(x).fileName2
                    + " have " + cheaters.get(x).similarities
                    + " phrases in common.");
        }
    }


    /**
     * method designed to create the hashmap from the document
     *
     * @param number
     *            the number of words in each nGram
     * @param doc
     *            the identity of the document being parsed
     * @return a HashMap representing every possible combination of n
     *         consecutive words in the document
     * @throws FileNotFoundException
     *             if the file isnt found
     */
    public static HashMap<Integer, String> hashMaker(int number, File doc)
        throws FileNotFoundException
    {

        // the specialized scanner to parse through the document
        Scanner scanner = new Scanner(new BufferedReader(new FileReader(doc)))
            .useDelimiter("\\s* \\s*");

        // the HashMap
        HashMap<Integer, String> nGramMap = new HashMap<Integer, String>();

        // the arrayDeque holding all of the words in an nGram
        ArrayDeque<String> nGram = new ArrayDeque<String>();
        // while loop designed to create every n-word combination of of words
        // in the document
        while (scanner.hasNext())
        {
            String nextWord = scanner.next();
            nGram.add(nextWord);
            if (nGram.size() >= number)
            {
                nGramMap.put(nGram.toString().hashCode(), "arbitrary");
                nGram.remove();
            }
        }
        // printing the number of nGrams for a given document
        // System.out.print(fileName + " ");
        // System.out.println(nGramMap.size() + " nGrams");
        scanner.close();

        // returning the HashMap, to then be used in the custom object
        return nGramMap;
    }

}
