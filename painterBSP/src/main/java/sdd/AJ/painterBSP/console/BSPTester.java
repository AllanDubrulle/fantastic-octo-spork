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
    
    public BSPTester( List<Segment>  list, Heuristic heuristic)
    {
        this.tree = new BSPTree(list, heuristic);
        this.setList(list);
        this.heuristic = heuristic;
    }
    
    public long constructorCpuTime()
    {

        long start_cpu = thread.getCurrentThreadCpuTime();
        @SuppressWarnings("unused")
        BSPTree temp = new BSPTree(getList(),getHeuristic());
        long end_cpu = thread.getCurrentThreadCpuTime();
        return end_cpu - start_cpu;
    }
    
    public int heightTest()
    {
        return tree.height();
    }
    
    public long painterCpuTime(double x, double y, double angle)
    {
        Eye eye = new Eye(x,y,angle);
        long start_cpu = thread.getCurrentThreadCpuTime();
        tree.paintersAlgorithm((a,b,c) -> {}, eye);
        long end_cpu = thread.getCurrentThreadCpuTime();
        return end_cpu - start_cpu;
    }
    public int sizeTest()
    {
        return tree.size();
    }
    
  
}
