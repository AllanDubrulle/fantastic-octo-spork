package sdd.AJ.painterBSP.BSPLib;

import java.util.ArrayList;
import java.util.List;
import java.lang.Math;
import sdd.AJ.painterBSP.BSPLib.Heuristic.Heuristic;
import sdd.AJ.painterBSP.BSPLib.Heuristic.LinearHeuristic;
import sdd.AJ.painterBSP.util.Equation;
import sdd.AJ.painterBSP.util.Segment;
import java.util.Collections;

public class BSPTree
{
    private List<Segment> list;
    private BSPTree left, right;
    private Equation equation;

    /**
     * Class constructor.
     * @param list      the list of the segments in the scene to be pre-processed
     * @param heuristic the heuristic used to select the segment used
     *                  to define the splitting plane
     */
    public BSPTree(List<Segment> list, Heuristic heuristic)
    {
        // BSP construction algorithm
        // If the given list is empty, we instanciate an empty node.
        this.list = new ArrayList<>();
        left = null;
        right = null;
        equation = null;
        if (!list.isEmpty())
        {
            // If the list is not empty, we select a segment
            // using the provided heuristic and use it to split the scene
            // into two half-planes.
            Segment pivot = heuristic.selectSegment(list);
            list.remove(pivot);
            equation = pivot.lineEquation();
            List<Segment> leftList = new ArrayList<>();
            List<Segment> rightList = new ArrayList<>();
            double d = equation.solve(pivot.x1, pivot.x2);
            final double eps = 1e-10;
            this.list.add(pivot);
            for (Segment s : list)
            {
                // Case in which one of the ends of the segment is
                // in the splitting line.
                if (Math.abs(equation.solve(s.x1, s.x2) - d) < eps)
                {
                    // We test whether the line segment s is contained
                    // in the splitting line.
                    if (Math.abs(equation.solve(s.y1, s.y2) - d) < eps)
                    {
                        this.list.add(s);
                    }
                    // Testing whether the line segment is in the
                    // positive half-plane
                    else if (equation.solve(s.y1, s.y2) > d)
                    {
                        rightList.add(s);
                    }

                    else // The segment is in the negative half-plane.
                    {
                        leftList.add(s);
                    }
                }

                // Case in which the other end of the segment is
                // in the splitting line.
                else if (Math.abs(equation.solve(s.y1, s.y2) - d) < eps)
                {
                    if (equation.solve(s.x1, s.x2) > d)
                    {
                        rightList.add(s);
                    }

                    else //(x1, x2) in the negative half-plane
                    {
                        leftList.add(s);
                    }
                }

                else if (equation.solve(s.x1, s.x2) > d)
                {
                    if (equation.solve(s.y1, s.y2) > d)
                    {
                        rightList.add(s);
                    }

                    else // the two points are in different half-planes, s is split
                    {
                        Segment[] brokenS = s.breakSegment(equation, d);
                        rightList.add(brokenS[0]);
                        leftList.add(brokenS[1]);
                    }
                }

                else if (equation.solve(s.x1, s.x2) < d)
                {
                    if (equation.solve(s.y1, s.y2) < d)
                    {
                        leftList.add(s);
                    }

                    else // the two points are in different half-planes, s is split
                    {
                        Segment[] brokenS = s.breakSegment(equation, d);
                        rightList.add(brokenS[1]);
                        leftList.add(brokenS[0]);
                    }
                }
            }
            // We recursively build the children trees.
            left = new BSPTree(leftList, heuristic);
            right = new BSPTree(rightList, heuristic);
        }
    }

    /**
     * Given a list, builds a BSP Tree using the random heuristic
     * (the list is shuffled then built in a linear manner.
     * @param list the list of the segments in the scene to be pre-processed
     * @return a BSPTree built representing the scene
     */
    public static BSPTree RandomBSPTree(List<Segment> list)
    {
        Collections.shuffle(list);
        return new BSPTree(list, new LinearHeuristic());
    }

    /**
     * Getter for the equation of the splitting plane
     * @return the equation of the of the splitting plane
     */
    public Equation getEquation()
    {
        return equation;
    }

    /**
     * Tests if the tree is empty
     * @return true iff the tree contains no data and has no children
     */
    public boolean isEmpty()
    {
        if (list == null && left == null && right == null)
            return true;
        else
            return false;
    }

    /**
     * Tests if the tree consists of only a leaf
     * @return true iff the tree has no children
     */
    public boolean isLeaf()
    {
        return left == null && right == null;
    }

    /**
     * Recursively computes the height of the tree.
     * @return an integer equal to the height of the tree
     */
    public int height()
    {
        if (isEmpty())
            return 0;
        else
            return 1 + Math.max(left.height(), right.height());
    }

    /**
     * Recursively computes the size of the tree.
     * @return an integer equal to the amount of nodes in the tree
     */
    public int size()
    {
        if (isEmpty())
            return 0;
        else
            return 1 + left.size() + right.size();
    }
}
