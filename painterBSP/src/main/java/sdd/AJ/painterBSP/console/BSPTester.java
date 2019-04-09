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
     * @param list      the list of the segments in the scene to be pre-processed
     */
    public BSPTester(List<Segment> list)
    {
        this.list= list;
    }

    public List<Segment> getList()
    {
        return list;
    }

    /**
     * Construct a new BSPTree with this heuristic.
     * We use CpuTime representing time elapsed from the start of the programme.
     * subtract end_cpu and start_cpu give expected time for the method call.
     * @return an integer equal to time in millisecond thanks to divise by 1000.
     */
    
    /**
     * 
     * @return a double equal to average time for build a BSPTree with a specific heuristic in millisecond.  
     */
    public double constructorCpuTime()
    {
        return avgConstructorTime;
    }

    /**
     * @return a double equal to the height of a BSPTree build with a specific heuristic
     */
    public abstract double heightTest();

    /**
    *
    * @param x      x-coordinate of the eye
    * @param y      y-coordinate of the eye
    * @param angle  angle representing the forward direction
    * @return a double equal to average time in millisecond for use painterCputime with a specific heuristic.
    */
    public abstract double painterCpuTime(double x, double y, double angle);

    /**
     * @return a double equal to the amount of nodes in a BSPTree build with a specific heuristic
     */
    public abstract double sizeTest();
    /**
     * @param eye   a eye in the scene.
     * @param tree  a tree representing the scene build with a specific heuristic.
    * Apply the painter's algorithm with the eye.
    * As we don't want to display something, we use a empty painter.
    * We use CpuTime representing time elapsed from the start of the programme.
    * subtract end_cpu and start_cpu give expected time for the method call.
    * @return a double equal to time in millisecond thanks to divise by 1 000 000.
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
