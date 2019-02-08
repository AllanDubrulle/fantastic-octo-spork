package sdd.AJ.painterBSP.BSPLIb.Heuristic;

import java.util.List;

import sdd.AJ.painterBSP.util.Segment;

public class LinearHeuristic implements Heuristic {

	public Segment selectSegment(List<Segment> list) {
		return list.get(0);
	}

}
