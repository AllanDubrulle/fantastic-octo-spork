package sdd.AJ.painterBSP.console;

import java.util.Collections;
import java.util.List;

import sdd.AJ.painterBSP.BSPLib.BSPTree;
import sdd.AJ.painterBSP.BSPLib.Heuristic.Heuristic;
import sdd.AJ.painterBSP.util.Eye;
import sdd.AJ.painterBSP.util.Segment;

public class BSPUnDeterministHeuristicTester extends BSPTester
{
    private BSPTree[] listBSP = new BSPTree[this.avgNbr]; 
    public BSPUnDeterministHeuristicTester(List<Segment> list, Heuristic heuristic)
    {
        super(list,heuristic);
        
        for (int i= 0;i<avgNbr;i++)
        {
            Collections.shuffle(getList()); //effet de bord 
            long start_cpu = System.nanoTime();
            BSPTree temp = new BSPTree(getList(),getHeuristic());
            long end_cpu = System.nanoTime();
            listBSP[i]= temp;
            this.avgTimeConstructor += (end_cpu - start_cpu)/1000;
        }
        this.avgTimeConstructor /= avgNbr;
    }


    @Override
    public double heightTest()
    {
        double res = 0;
        for (int i= 0;i<avgNbr;i++)
        {
            res+=listBSP[i].height();
        }
        return res/avgNbr; // division entière
    }

    @Override
    public double painterCpuTime(double x, double y, double angle)
    {
        Eye eye = new Eye(x,y,angle);
        double res = 0;
        for (int i=0; i<avgNbr ; i++)
        {
            res += painterCpuTimeTree(listBSP[i],eye);
        }
        return res/avgNbr;
    }
    
    private double painterCpuTimeTree(BSPTree tree, Eye eye)
    {
        double res = 0;
        for (int i=0; i<avgNbr ; i++)
        {
            long start_cpu = System.nanoTime();
            tree.paintersAlgorithm((u,v,w)->{},eye);
            long end_cpu = System.nanoTime();
            res+= (end_cpu - start_cpu)/1000;
        }
        return res;
        
    }

    @Override
    public double sizeTest()
    {
        double res = 0;
        for (int i= 0;i<avgNbr;i++)
        {
            res+=listBSP[i].size();
        }
        return res/avgNbr; // division entière
    }

}
