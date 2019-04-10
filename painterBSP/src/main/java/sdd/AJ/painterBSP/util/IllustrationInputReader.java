package sdd.AJ.painterBSP.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Class used to read a file. Stores the data in the read
 * file as instance variables. Data is a representation of a scene.
 * Has multiple constructors to enable multiple ways of reading
 * (via a path or a File object)
 */
public class IllustrationInputReader
{
    private int n, xBound, yBound;
    private List<Segment> segments;

    /**
     * Class constructor.
     * @param filename the name of the file to be read
     * @throws IOException if a I/O problem occurs during the reading
     * @throws FileFormatException if any other issue arises during the reading
     */
    public IllustrationInputReader(String filename)
        throws IOException, FileFormatException
    {
        try (BufferedReader br = new BufferedReader(new FileReader(filename)))
        {
            processFile(br);
        }
    }

    /**
     * Class constructor.
     * @param f the file to be read
     * @throws IOException if a I/O problem occurs during the reading
     * @throws FileFormatException if any other issue arises during the reading
     */
    public IllustrationInputReader(File f) throws
        IOException, FileFormatException
    {
        try (BufferedReader br = new BufferedReader(new FileReader(f)))
        {
            processFile(br);
        }
    }

    /**
     * Class constructor.
     * @param an input stream representing the file to be read
     * @throws IOException if a I/O problem occurs during the reading
     * @throws FileFormatException if any other issue arises during the reading
     */
    public IllustrationInputReader(InputStream is)
        throws IOException, FileFormatException
    {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is)))
        {
            processFile(br);
        }
    }

    /**
     * Support method to avoid code duplication in constructors.
     * @param bf a BufferedReader containing a file to be read
     */
    private void processFile(BufferedReader br)
        throws IOException, FileFormatException
    {
        StringTokenizer st;
        try
        {
            st = new StringTokenizer(br.readLine());
            if (st.countTokens() != 4)
                throw new FileFormatException();
            st.nextToken();
            xBound = Integer.parseInt(st.nextToken());
            yBound = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            segments = new ArrayList<Segment>();
            for (int k = 0; k < n; k++)
            {
                st = new StringTokenizer(br.readLine());
                if (st.countTokens() != 5)
                    throw new FileFormatException();
                segments.add(new Segment(Double.parseDouble(st.nextToken()),
                                         Double.parseDouble(st.nextToken()),
                                         Double.parseDouble(st.nextToken()),
                                         Double.parseDouble(st.nextToken()),
                                         MyColor.valueOf(st.nextToken().toUpperCase())));
            }
        }
        catch (NumberFormatException e) // If a parsed string is not a number.
        {
            throw new FileFormatException();
        }
    }


    /**
     * Getter for the x-coordinate bound on the scene.
     * @return the bound on x-coordinates
     */
    public int getXBound()
    {
        return xBound;
    }

    /**
     * Getter for the y-coordinate bound on the scene.
     * @return the bound on y-coordinates
     */
    public int getYBound()
    {
        return yBound;
    }

    /**
     * Getter for the list of segments representing the scene.
     * @return the list of segments representing the scene
     */
    public List<Segment> getSegments()
    {
        return segments;
    }
}
