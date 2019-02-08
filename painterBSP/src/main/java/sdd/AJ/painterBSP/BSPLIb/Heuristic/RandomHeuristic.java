package sdd.AJ.painterBSP.BSPLIb.Heuristic;

import java.util.Collections;
import java.util.List;

import sdd.AJ.painterBSP.util.Segment;

public class RandomHeuristic implements Heuristic {

	public Segment selectSegment(List<Segment> list) { //TODO modifier le random
		Collections.shuffle(list);
		return list.get(0);
	}

}
