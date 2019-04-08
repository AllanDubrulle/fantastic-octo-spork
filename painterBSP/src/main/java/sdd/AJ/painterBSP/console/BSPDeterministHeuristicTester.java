package sdd.AJ.painterBSP.console;

import java.util.List;

import sdd.AJ.painterBSP.BSPLib.BSPTree;
import sdd.AJ.painterBSP.BSPLib.Heuristic.Heuristic;
import sdd.AJ.painterBSP.util.Eye;
import sdd.AJ.painterBSP.util.Segment;

/**
 * Class used to conduct performance tests on heuristics used
 * for BSP tree construction.
 */
public class BSPDeterministHeuristicTester extends BSPTester
{
    
    private BSPTree tree;

    /**
     * Class constructor.
     * @param list      the list of the segments in the scene to be pre-processed
     * @param heuristic the heuristic used to select the segment used
     *                  to define the splitting plane
     */
    public BSPDeterministHeuristicTester( List<Segment>  list, Heuristic heuristic)
    {
        super(list,heuristic);
        long start_cpu = System.nanoTime();
        this.tree = new BSPTree(list,getHeuristic());
        long end_cpu = System.nanoTime();
        this.avgTimeConstructor = (end_cpu - start_cpu);
        for (int i=1; i<=avgNbr; i++)
        {
            start_cpu = System.nanoTime();
            BSPTree temp = new BSPTree(getList(),getHeuristic());
            end_cpu = System.nanoTime();
            avgTimeConstructor+= (end_cpu - start_cpu)/1000;
        }
        this.avgTimeConstructor /= avgNbr;
    }
    
    @Override
    public double heightTest()
    {
        return tree.height();
    }


    @Override
    public double painterCpuTime(double x, double y, double angle)
    {
        Eye eye = new Eye(x,y,angle);
        double res = 0;
        for (int i=0; i<=avgNbr ; i++)
        {
            long start_cpu = System.nanoTime();
            tree.paintersAlgorithm((u,v,w)->{},eye);
            long end_cpu = System.nanoTime();
            res+= (end_cpu - start_cpu)/1000;
        }
        return res/avgNbr;
    }


    @Override
    public double sizeTest()
    {
        return tree.size();
    }

}
