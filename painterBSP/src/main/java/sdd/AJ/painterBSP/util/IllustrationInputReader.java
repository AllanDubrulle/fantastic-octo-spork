package sdd.AJ.painterBSP.util;

import java.util.List;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;

import sdd.AJ.painterBSP.util.*;
import sdd.AJ.painterBSP.graphics.*;

public class IllustrationInputReader
{
    private int n, xBound, yBound;
    private List<Segment> segments;

    public IllustrationInputReader(String filename)
        throws IOException, FileFormatException
    {
        try (BufferedReader br = new BufferedReader(new FileReader(filename)))
        {
            processFile(br);
        }
    }

    public IllustrationInputReader(File f) throws
        IOException, FileFormatException
    {
        try (BufferedReader br = new BufferedReader(new FileReader(f)))
        {
            processFile(br);
        }
    }

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


    public int getXBound()
    {
        return xBound;
    }

    public int getYBound()
    {
        return yBound;
    }

    public List<Segment> getSegments()
    {
        return segments;
    }
}
