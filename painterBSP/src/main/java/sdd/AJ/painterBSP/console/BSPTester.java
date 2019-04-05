package sdd.AJ.painterBSP.console;

import java.util.List;

import sdd.AJ.painterBSP.BSPLib.BSPTree;
import sdd.AJ.painterBSP.BSPLib.Heuristic.Heuristic;
import sdd.AJ.painterBSP.util.Eye;
import sdd.AJ.painterBSP.util.Segment;

public class BSPTester
{
    private BSPTree tree;
    private List<Segment> list;
    private Heuristic heuristic;
    private double mTimeConstructor;
    
   
    public List<Segment> getList()
    {
        return list;
    }

    public Heuristic getHeuristic()
    {
        return heuristic;
    }

    /**
     * Class constructor.
     * @param list      the list of the segments in the scene to be pre-processed
     * @param heuristic the heuristic used to select the segment used
     *                  to define the splitting plane
     */
    public BSPTester( List<Segment>  list, Heuristic heuristic)
    {
        this.list = list;
        this.heuristic = heuristic;
        long start_cpu = System.nanoTime();
        this.tree = new BSPTree(list,getHeuristic());
        long end_cpu = System.nanoTime();
        this.mTimeConstructor = (end_cpu - start_cpu);
        for (int i=1; i<=10 ; i++)
        {
            start_cpu = System.nanoTime();
            BSPTree temp = new BSPTree(getList(),getHeuristic());
            end_cpu = System.nanoTime();
            mTimeConstructor+= (end_cpu - start_cpu)/1000;
        }
        this.mTimeConstructor /= 10;
    }
    
    
    /**
     * Construct a new BSPTree with this heuristic.
     * We use CpuTime representing time elapsed from the start of the programme.
     * subtract end_cpu and start_cpu give expected time for the method call. 
     * @return an integer equal to time in millisecond thanks to divise by 1000.
     */
    public double constructorCpuTime()
    {

        return mTimeConstructor;
    }
    
    /**
     * Recursively computes the height of the tree.
     * @return an integer equal to the height of the tree
     */
    public int heightTest()
    {
        return tree.height();
    }
    
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
    public double painterCpuTime(double x, double y, double angle)
    {
        Eye eye = new Eye(x,y,angle);
        double res = 0;
        for (int i=0; i<=50 ; i++)
        {
            long start_cpu = System.nanoTime();
            tree.paintersAlgorithm((u,v,w)->{},eye);
            long end_cpu = System.nanoTime();
            res+= (end_cpu - start_cpu)/1000;
        }
        return res/50;
    }
    
    /**
     * Recursively computes the size of the tree.
     * @return an integer equal to the amount of nodes in the tree
     */
    public int sizeTest()
    {
        return tree.size();
    }
    
  
}
