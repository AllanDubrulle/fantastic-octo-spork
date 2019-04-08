package sdd.AJ.painterBSP.console;

import java.util.List;

import sdd.AJ.painterBSP.BSPLib.Heuristic.Heuristic;
import sdd.AJ.painterBSP.util.Segment;

public abstract class BSPTester
{
    protected int avgNbr=30;
    protected List<Segment> list;
    protected Heuristic heuristic;
    protected double avgTimeConstructor;
    
    public BSPTester(List<Segment> list, Heuristic heuristic)
    {
        this.list= list;
        this.heuristic = heuristic;
    }
    
    public List<Segment> getList()
    {
        return list;
    }

    public Heuristic getHeuristic()
    {
        return heuristic;
    }
    
    /**
     * Construct a new BSPTree with this heuristic.
     * We use CpuTime representing time elapsed from the start of the programme.
     * subtract end_cpu and start_cpu give expected time for the method call.
     * @return an integer equal to time in millisecond thanks to divise by 1000.
     */
    public double constructorCpuTime()
    {
        return avgTimeConstructor;
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
}
