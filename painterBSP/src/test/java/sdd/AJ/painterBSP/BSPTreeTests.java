package sdd.AJ.painterBSP;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import sdd.AJ.painterBSP.BSPLib.BSPTree;
import sdd.AJ.painterBSP.util.FileFormatException;
import sdd.AJ.painterBSP.util.IllustrationInputReader;
import sdd.AJ.painterBSP.util.Segment;

public class BSPTreeTests
{
    public static ArrayList<Segment> segmentList;
    @BeforeClass
    public static void initializeFile() throws IOException, FileFormatException
    {
        IllustrationInputReader iir = new IllustrationInputReader("Scenes/random/randomHuge.txt");
        segmentList = (ArrayList<Segment>) iir.getSegments();
    }

    @Test
    public void stackoverflow()
    {
         BSPTree test = BSPTree.RandomBSPTree(segmentList);
    }
}
