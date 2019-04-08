package sdd.AJ.painterBSP.console;

import java.util.List;

import sdd.AJ.painterBSP.BSPLib.Heuristic.Heuristic;
import sdd.AJ.painterBSP.BSPLib.BSPTree;
import sdd.AJ.painterBSP.util.Segment;
import sdd.AJ.painterBSP.util.Eye;

public abstract class BSPTester
{
    protected int avgNbr=30;
    protected List<Segment> list;
    protected double avgConstructorTime;

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
    public double constructorCpuTime()
    {
        return avgConstructorTime;
    }

    /**
     * Recursively computes the height of the tree.
     * @return an integer equal to the height of the tree
     */
    public abstract double heightTest();

    /**
    *
    * @param x      x-coordinate of the eye
    * @param y      y-coordinate of the eye
    * @param angle  angle representing the forward direction
    * Apply the painter's algorithm with a eye constructed with x ,y, angle and this BSPTree.
    * As we don't want to display something, we use a empty painter.
    * We use CpuTime representing time elapsed from the start of the programme.
    * subtract end_cpu and start_cpu give expected time for the method call.
    * @return an integer equal to time in millisecond thanks to divise by 1000.
    */
    public abstract double painterCpuTime(double x, double y, double angle);

    /**
     * @return an integer equal to the amount of nodes in the tree
     */
    public abstract double sizeTest();

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
            res+= (end_cpu - start_cpu)/1000;
        }
        return res/avgNbr;
    }
}
