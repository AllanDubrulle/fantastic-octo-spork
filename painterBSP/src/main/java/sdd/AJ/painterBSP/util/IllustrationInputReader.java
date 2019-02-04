package sdd.AJ.painterBSP.util;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.StringTokenizer;

import sdd.AJ.painterBSP.util.*;
import sdd.AJ.painterBSP.graphics.*;


public class IllustrationInputReader
{
    private int n, xBound, yBound;
    private Segment[] segments;

    public IllustrationInputReader(String filename)
        throws IOException
    {
        try (BufferedReader br =
             new BufferedReader(new FileReader(filename)))
            {
                processFile(br);
            }
    }
    
    public IllustrationInputReader(File f)
            throws IOException
    {
    try (BufferedReader br =
             new BufferedReader(new FileReader(f)))
            {
                processFile(br);
            }
    }
    private void processFile(BufferedReader br)
            throws IOException
    {
        StringTokenizer st;
                st = new StringTokenizer(br.readLine());
                st.nextToken();
                xBound = Integer.parseInt(st.nextToken());
                yBound = Integer.parseInt(st.nextToken());
                n = Integer.parseInt(st.nextToken());
                segments = new Segment[n];
                for (int k = 0; k < n; k++)
                    {
                        st = new StringTokenizer(br.readLine());
                        segments[k] =
                            new Segment(Double.parseDouble(st.nextToken()),
                                        Double.parseDouble(st.nextToken()),
                                        Double.parseDouble(st.nextToken()),
                                        Double.parseDouble(st.nextToken()),
                                        MyColor.valueOf(st.nextToken().toUpperCase()));
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
    
    public Segment[] getSegments()
    {
        return segments;
    }
}