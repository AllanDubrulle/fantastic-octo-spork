package sdd.AJ.painterBSP;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import sdd.AJ.painterBSP.BSPLib.BSPTree;
import sdd.AJ.painterBSP.BSPLib.heuristic.LinearHeuristic;
import sdd.AJ.painterBSP.util.FileFormatException;
import sdd.AJ.painterBSP.util.IllustrationInputReader;
import sdd.AJ.painterBSP.util.Segment;
import java.io.InputStream;

/**
 * Unit tests to check that no stackoverflow or overhead memory
 * errors occur
 */
public class BSPTreeTests
{

    /**
     * Tests with the largest file randomHuge.txt
     */
    @Test
    public void stackoverflowRandom() throws IOException, FileFormatException
    {
        BSPTree test = new BSPTree(getList("Scenes/random/randomHuge.txt"),
                                   new LinearHeuristic());
    }

    /**
     * Tests with the largest ellipse type file: ellipsesLarge.txt
     */
    @Test
    public void stackoverflowEllipses() throws IOException, FileFormatException
    {
        BSPTree test = new BSPTree(getList("Scenes/ellipses/ellipsesLarge.txt"),
                                   new LinearHeuristic());
    }

    /**
     * Tests with the largest rectangle-type file: rectanglesHuge.txt
     */
    @Test
    public void stackoverflowRectangles() throws IOException, FileFormatException
    {
        BSPTree test = new BSPTree(getList("Scenes/rectangles/rectanglesHuge.txt"),
                                   new LinearHeuristic());
    }

    private List<Segment> getList(String fileName) throws IOException, FileFormatException
    {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream is = classLoader.getResourceAsStream(fileName);
        IllustrationInputReader iir = new IllustrationInputReader(is);
        return iir.getSegments();
    }

}
