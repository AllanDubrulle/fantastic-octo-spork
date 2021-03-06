package sdd.AJ.painterBSP.util;

/**
 * Class representing a line equation of the form:
 *                  f(x, y) = c
 * where f(x, y) = nx * x + ny * y is a functional.
 * Contains utility methods to determine whether
 * a point is contained in the represented line,
 * or in one of the halfs planes defined by the line.
 */
public class Equation
{
    private double nx, ny;
    private double c;

    private static final double EPS = 1e-9;

    /**
     * Class constructor.
     * @param nx image of (1, 0) by associated functional
     * @param ny image of (0, 1) by associated functional
     * @param c  describes the points through which the line passes
     */
    public Equation(double nx, double ny, double c)
    {
        this.nx = nx;
        this.ny = ny;
        this.c = c;
    }

    /**
     * Returns the image of (x, y) by the functional
     * associated with the equation.
     * @param x the first parameter
     * @param y the second parameter
     * @return f(x, y)
     */
    public double compute(double x, double y)
    {
        return nx * x + ny * y;
    }

    /**
     * Determines whether a point lies in the positive half-plane.
     * @param x the first component of the point
     * @param y the second component of the point
     * @return true iff (x, y) is such that f(x, y) is greater or equal to c
     */
    public boolean isInPositivePlane(double x, double y)
    {
        return nx * x + ny * y >= c + EPS;
    }

    /**
     * Determines whether a point lies in the negative half-plane.
     * @param x the first component of the point
     * @param y the second component of the point
     * @return true iff (x, y) is such that f(x, y) lesser or equal to c
     */
    public boolean isInNegativePlane(double x, double y)
    {
        return nx * x + ny * y <= c - EPS;
    }

    /**
     * Determines whether a point lies in the line.
     * @param x the first component of the point
     * @param y the second component of the point
     * @return true iff (x, y) is such that f(x, y) ~ c
     */
    public boolean isInLine(double x, double y)
    {
        return Math.abs(nx * x + ny * y- c)< EPS;
    }

    /**
     * Determines if a segment is (partially) in each
     * half-plane, that being equivalent to the line
     * f(x, y) = c having an intersection with the segment.
     * @param s a segment
     * @return true iff the ends of s lie in different half-planes
     */
    public boolean liesInTwoHalfs(Segment s)
    {
        return (isInNegativePlane(s.u, s.v) && isInPositivePlane(s.x, s.y))
            || (isInPositivePlane(s.u, s.v) && isInNegativePlane(s.x, s.y));
    }

    /**
     * Getter for the value of c
     * @return the value of c
     */
    public double getC()
    {
        return c;
    }

}
