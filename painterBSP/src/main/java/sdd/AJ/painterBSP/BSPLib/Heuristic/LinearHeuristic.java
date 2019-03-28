package sdd.AJ.painterBSP.BSPLib.Heuristic;

import java.util.List;

import sdd.AJ.painterBSP.util.Segment;

public class LinearHeuristic implements Heuristic
{
    @Override
    public Segment selectSegment(List<Segment> list)
    {
        return list.get(0);
    }
}