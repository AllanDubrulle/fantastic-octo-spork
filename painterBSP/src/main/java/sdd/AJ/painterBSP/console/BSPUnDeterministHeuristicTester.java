package sdd.AJ.painterBSP.console;

import java.util.Collections;
import java.util.List;

import sdd.AJ.painterBSP.BSPLib.BSPTree;
import sdd.AJ.painterBSP.BSPLib.Heuristic.Heuristic;
import sdd.AJ.painterBSP.util.Eye;
import sdd.AJ.painterBSP.util.Segment;

public class BSPUnDeterministHeuristicTester extends BSPTester
{
    private BSPTree[] bspList = new BSPTree[this.avgNbr];
    public BSPUnDeterministHeuristicTester(List<Segment> list)
    {
        super(list);
        long start_cpu;
        long end_cpu;
        BSPTree temp;
        for (int i= 0;i<avgNbr;i++)
        {
            start_cpu = System.nanoTime();
            temp = BSPTree.randomBSPTree(getList());
            end_cpu = System.nanoTime();
            bspList[i]= temp;
            this.avgConstructorTime += (end_cpu - start_cpu)/1000;
        }
        this.avgConstructorTime /= avgNbr;
    }


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
