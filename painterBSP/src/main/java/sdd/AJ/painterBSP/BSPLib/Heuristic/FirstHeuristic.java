package sdd.AJ.painterBSP.BSPLib.Heuristic;

import java.util.List;

import sdd.AJ.painterBSP.util.Equation;
import sdd.AJ.painterBSP.util.Segment;

public class FirstHeuristic implements Heuristic
{
    @Override
    public Segment selectSegment(List<Segment> list)
    {
        int[] gs = new int[list.size()];
        for(Segment s : list)
        {
            Equation sLineEquation = s.lineEquation();
            for(int i = 0 ; i< gs.length ; i++ )
            {
                Segment t = list.get(i);
                if (!s.equals(t))
                {
                    if (sLineEquation.liesInTwoHalfs(t) ||
                        sLineEquation.isInLine(t.u, t.v) ||
                        sLineEquation.isInLine(t.x, t.y))
                    {
                        gs[i]++;
                    }
                }

            }

        }

        int maxI = 0;
        for (int i = 1; i<gs.length ; i++ )
        {
            if (gs[maxI]<gs[i])
                maxI = i;
        }
        return list.get(maxI);
    }

}
