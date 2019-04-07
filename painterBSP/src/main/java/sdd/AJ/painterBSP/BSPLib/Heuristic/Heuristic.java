package sdd.AJ.painterBSP.BSPLib.Heuristic;

import java.util.List;

import sdd.AJ.painterBSP.util.Segment;

/**
 * Functional interface used in the construction of a
 * 2D BSP tree to select the segment used as a splitting line.
 */
public interface Heuristic
{
    /**
     * Selects a segment from a non empty list of segments to
     * be used to deduce a splitting line.
     * @param list the list of segments from which a segment must be selected
     * @return a segment selected to define a splitting line
     */
    public Segment selectSegment(List<Segment> list);
}
