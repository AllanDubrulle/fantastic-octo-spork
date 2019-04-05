package sdd.AJ.painterBSP.console;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
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
    private ThreadMXBean thread = ManagementFactory.getThreadMXBean();
    
    public BSPTree getTree()
    {
        return tree;
    }
    
    public void setTree(BSPTree tree)
    {
        this.tree = tree;
    }
    
    public List<Segment> getList()
    {
        return list;
    }

    public void setList(List<Segment> list)
    {
        this.list = list;
    }

    public Heuristic getHeuristic()
    {
        return heuristic;
    }
    
    public void setHeuristic(Heuristic heuristic)
    {
        this.heuristic = heuristic;
    }
    
    /**
     * Class constructor.
     * @param list      the list of the segments in the scene to be pre-processed
     * @param heuristic the heuristic used to select the segment used
     *                  to define the splitting plane
     */
    public BSPTester( List<Segment>  list, Heuristic heuristic)
    {
        this.tree = new BSPTree(list, heuristic);
        this.setList(list);
        this.heuristic = heuristic;
    }
    
    
    /**
     * Construct a new BSPTree with this heuristic.
     * We use CpuTime representing time elapsed from the start of the programme.
     * subtract end_cpu and start_cpu give expected time for the method call. 
     * @return an integer equal to time in millisecond thanks to divise by 1000.
     */
    public long constructorCpuTime()
    {
        /*long start_global = System.nanoTime();
        long start_cpu = thread.getCurrentThreadCpuTime();
        long start_user = thread.getCurrentThreadUserTime();*/

        long start_cpu = thread.getCurrentThreadCpuTime();
        @SuppressWarnings("unused")
        BSPTree temp = new BSPTree(getList(),getHeuristic());
        long end_cpu = thread.getCurrentThreadCpuTime();
        return (end_cpu - start_cpu)/1000;
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
    public long painterCpuTime(double x, double y, double angle)
    {
        Eye eye = new Eye(x,y,angle);
        long start_cpu = thread.getCurrentThreadCpuTime();
        tree.paintersAlgorithm((a,b,c) -> {}, eye);
        long end_cpu = thread.getCurrentThreadCpuTime();
        return (end_cpu - start_cpu)/1000;
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
