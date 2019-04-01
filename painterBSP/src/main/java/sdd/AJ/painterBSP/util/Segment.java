package sdd.AJ.painterBSP.util;

import sdd.AJ.painterBSP.util.*;
import sdd.AJ.painterBSP.graphics.*;

/**
 * Class representing a segment represented its endpoints (x1, x2)
 * and (y1, y2), as well as a colour.
 */
public final class Segment
{

    public final double x1, x2, y1, y2; // Premier point (x1, x2) et deuxieme point (y1, y2)
    private final MyColor color;

    /**
     * Class constructor.
     */
    public Segment(double x1, double x2, double y1, double y2, MyColor color)
    {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.color = color;
    }

    /**
     * Getter for the colour of the segment.
     * @return the colour of the segment
     */
    public MyColor getColor()
    {
        return color;
    }

    /**
     * Creates the line equation passing through the segment.
     * @see sdd.AJ.painterBSP.util.Equation
     * @return the equation of the line passing through (0, 0)
     *          with direction (x1 - y1, x2 - y2)
     */
    public Equation lineEquation()
    {
        double v1 = x1 - y1;
        double v2 = y2 - x2;
        double c = x1 * v2 + x2 * v1;
        return new Equation(v2, v1, c);
    }

    /**
     * Given an planar equation f(x, y) = 0 and a number c, such
     * that (f(x1, x2) - c) * (f(y1, y2) - c) < 0,
     * breaks the segment in two halfs s' and s'' such that
     * s' lies in the half-plane containing (x1, x2)
     * s'' lies in the half-plane containing (y1, y2).
     * Throws a RuntimeException if the precondition is not met.
     * @param equation the planar equation used to define the half-planes
     * @param c        the scalar used to define the half-planes
     * @return an array containing two segments [s', s''] as previously
     *          described
     */
    public Segment[] breakSegment(Equation equation)
    {
        if (!equation.liesInTwoHalfs(this))
            throw new RuntimeException();
        double c = equation.getC();
        double t = (c - equation.compute(y1, y2)) / (equation.compute(x1, x2) - equation.compute(y1, y2));
        if (Math.abs(t)>1)
            System.out.println(t);
        double z1 = t * x1 + (1 - t) * y1;
        double z2 = t * x2 + (1 - t) * y2;
        return new Segment[] { new Segment(x1, x2, z1, z2, color), new Segment(z1, z2, y1, y2, color) };
    }

    /**
     * Equality method between two segments.
     * @param other the segment with which equality is to be tested.
     * @return true iff both segments match in color and exact coordinates
     */
    public boolean equals(Segment other)
    {
        if (color != other.getColor())
            return false;
        return (  x1 == other.x1 && x2 == other.x2 &&
                  y1==other.y1 && y2 == other.y2 )      ||
               (  y1 == other.x1 && y2 == other.x2 &&
                  x1==other.y1 && x2 == other.y2 );
    }
}
