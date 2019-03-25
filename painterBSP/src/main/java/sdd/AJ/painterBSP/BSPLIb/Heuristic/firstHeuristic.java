package sdd.AJ.painterBSP.BSPLIb.Heuristic;

import java.util.List;

import sdd.AJ.painterBSP.util.Equation;
import sdd.AJ.painterBSP.util.Segment;

public class firstHeuristic implements Heuristic
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
                    if (sLineEquation.solve(t.x1, t.x2) * sLineEquation.solve(t.y1, t.y2)< 0)
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
