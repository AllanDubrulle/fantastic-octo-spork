package sdd.AJ.painterBSP.console;

import java.util.List;

import sdd.AJ.painterBSP.BSPLib.BSPTree;
import sdd.AJ.painterBSP.util.Eye;
import sdd.AJ.painterBSP.util.Segment;

public class RandomHeuristicTester extends BSPTester
{
    private BSPTree[] bspList = new BSPTree[this.avgNbr];
    
    /**
     * Class constructor.
     * @param list      the list of the segments in the scene to be pre-processed
     *  bspList contains avgNbr BspTree built randomly.                
     *  the average time for build a BSPTree with heuristic in millisecond is pre-processed in 
     *      avgConstructorTime. 
     */
    public RandomHeuristicTester(List<Segment> list)
    {
        super(list);
        long start_cpu;
        long end_cpu;
        BSPTree temp;
        double res =0;
        for (int i= 0;i<avgNbr;i++)
        {
            start_cpu = System.nanoTime();
            temp = BSPTree.randomBSPTree(getList());
            end_cpu = System.nanoTime();
            bspList[i]= temp;
            res += (end_cpu - start_cpu)/1000000.;
        }
        this.avgConstructorTime = (res/avgNbr);
    }

    /**
     * @return a double equal to the average height for a BSPTree built randomly using bspList 
     */
    @Override
    public double heightTest()
    {
        double res = 0;
        for (int i= 0;i<avgNbr;i++)
        {
            res+=bspList[i].height();
        }
        return res/avgNbr;
    }

    /**
         /**
     * @param x      x-coordinate of the eye
     * @param y      y-coordinate of the eye
     * @param angle  angle representing the forward direction
     * @return an double equal to the average time for use painter's algorithm with BSPTree build 
     * randomly and a eye corresponding at x,y,angle position 
     */
    @Override
    public double painterCpuTime(double x, double y, double angle)
    {
        Eye eye = new Eye(x,y,angle);
        double res = 0;
        for (int i=0; i<avgNbr ; i++)
        {
            res += painterCpuTimeTree(bspList[i],eye);
        }
        return res/avgNbr;
    }

    /**
     * @return a double equal to the average size for a BSPTree built randomly using bspList 
     */
    @Override
    public double sizeTest()
    {
        double res = 0;
        for (int i= 0;i<avgNbr;i++)
        {
            res+=bspList[i].size();
        }
        return res/avgNbr;
    }

}
