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
     * Builds a list of BSP trees built using the random heuristic.
     * The average time to build a BSP tree with the random heuristic
     * in milliseconds is computed during construction and assigned
     * to the field avgConstructorTime.
     * @param list the list of the segments in the scene to be pre-processed
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
            temp = BSPTree.randomBSPTree(list);
            end_cpu = System.nanoTime();
            bspList[i]= temp;
            res += (end_cpu - start_cpu)/1000000.;
        }
        this.avgConstructorTime = (res/avgNbr);
    }

    /**
     * @return a double-precision floating point number
     * equal to the average height of a BSP tree built by means
     * of the random heuristic (computed using the list of trees generated during
     * class instanciation)
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
     * @param x      x-coordinate of the eye
     * @param y      y-coordinate of the eye
     * @param angle  angle representing the forward direction
     * @return a double-precision floating point number equal to the
     * average time for a call to the painter's algorithm with BSPTree built
     * with the random heuristic and an eye corresponding at x,y,angle position
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
     * @return a double equal to the average size for a BSPTree built
     * with the random heuristic using the list of trees generated
     * during class instanciation
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
