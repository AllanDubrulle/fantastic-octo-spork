package sdd.AJ.painterBSP.BSPLIb.Heuristic;

import java.util.List;

import sdd.AJ.painterBSP.util.Segment;

public interface Heuristic
{
    public Segment selectSegment(List<Segment> List);
}
