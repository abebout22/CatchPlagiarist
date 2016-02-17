package catchPlagiarist;

import java.util.HashMap;

/**
 * This class creates a custom object which holds both a hashMap and also a
 * fileName string
 *
 * @author alanbebout
 * @version Dec 4, 2015
 */
public class customObject
{
    /**
     * The hashmap field of the new custom object
     */
    HashMap<Integer, String> hashmap;
    /**
     * The name of the file, a field in the custom object
     */
    String                   fileName;


    /**
     * Create a new customObject object.
     *
     * @param h
     *            the hashmap
     * @param name
     *            the file name
     */
    public customObject(HashMap h, String name)
    {
        this.hashmap = h;
        this.fileName = name;
    }
}
