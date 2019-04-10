package sdd.AJ.painterBSP.console;

import java.util.List;

import sdd.AJ.painterBSP.BSPLib.BSPTree;
import sdd.AJ.painterBSP.util.Eye;
import sdd.AJ.painterBSP.util.Segment;

public abstract class BSPTester
{
    protected int avgNbr=30;
    protected List<Segment> list;
    protected double avgConstructorTime;


    /**
     * Class constructor.
     * @param list the list of the segments in the scene to be pre-processed
     */
    public BSPTester(List<Segment> list)
    {
        this.list= list;
    }

    /**
     * @return a double-precision floating point number
     *         equal to the average time required to build a
     *         BSPTree with a specific heuristic (in milliseconds).
     */
    public double constructorCpuTime()
    {
        return avgConstructorTime;
    }

    /**
     * @return a double-precision floating point number equal to the
     * height of a BSPTree built with a specific heuristic
     */
    public abstract double heightTest();

    /**
    * @param x      x-coordinate of the eye
    * @param y      y-coordinate of the eye
    * @param angle  angle representing the forward direction of the eye
    * @return a double-precision floating point number equal to the average
    * CPU time in milliseconds for the use of the painter's algorithm
    */
    public abstract double painterCpuTime(double x, double y, double angle);

    /**
     * @return a double-precision floating point number
     * equal to the (average) amount of nodes
     * in a BSPTree built with a specific heuristic
     */
    public abstract double sizeTest();

    /**
     * Applies the painter's algorithm with a given eye and computes average
     * CPU time for a call.
     * As no display is required, an empty painter is used.
     * We use CPU time representing time elapsed from the
     * start of the programme and subtract the time prior to the method
     * call to the time after the method call
     * (acquired times are in nanoseconds).
     * @param eye   a eye in the scene.
     * @param tree  a tree representing the scene (built with a specific heuristic).
     * @return a double-precision floating point number
     * equal to the average time of a call to the painter's algorithm in
     * milliseconds
     */
    protected double painterCpuTimeTree(BSPTree tree, Eye eye)
    {
        double res = 0;
        long start_cpu;
        long end_cpu;
        for (int i=0; i<avgNbr ; i++)
        {
            start_cpu = System.nanoTime();
            tree.paintersAlgorithm((u,v,w)->{},eye);
            end_cpu = System.nanoTime();
            res+= (end_cpu - start_cpu)/1000000.;
        }
        return res/avgNbr;
    }
}
