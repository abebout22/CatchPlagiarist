package catchPlagiarist;

/**
 * This class creates a custom object which holds the document names and
 * similarities counts for two documents
 *
 * @author alanbebout
 * @version Dec 4, 2015
 */
public class cheaterObject
    implements Comparable<cheaterObject>
{

    /**
     * string to hold the first file name
     */
    String fileName1;
    /**
     * string to hold the second file name
     */
    String fileName2;
    /**
     * integer to hold the number of similarities between the two files
     */
    int    similarities;


    /**
     * Create a new cheaterObject object.
     *
     * @param name1
     *            the name of the first file
     * @param name2
     *            the name of the second file
     * @param sim
     *            the number of similarities
     */
    public cheaterObject(String name1, String name2, int sim)
    {
        this.fileName1 = name1;
        this.fileName2 = name2;
        this.similarities = sim;
    }


    @Override
    public int compareTo(cheaterObject o)
    {
        if (this.similarities == o.similarities)
        {
            return 0;
        }
        else if (this.similarities > o.similarities)
        {
            return 1;
        }
        else
        {
            return -1;
        }
    }
}
