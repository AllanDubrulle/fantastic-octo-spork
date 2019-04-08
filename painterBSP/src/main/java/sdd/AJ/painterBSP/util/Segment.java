package sdd.AJ.painterBSP.util;

/**
 * Class representing a segment by means of its endpoints (u, v)
 * and (x, y), as well as a colour. Instances of this class are
 * immutable and as such fields other than colour can be accessed
 * directly.
 */
public final class Segment
{

    public final double u, v, x, y; // Premier point (u, v) et deuxieme point (x, y)
    private final MyColor color;

    /**
     * Class constructor.
     * @param u the x-coordinate of the first point
     * @param v the y-coordinate of the first point
     * @param x the x-coordinate of the second point
     * @param y the y-coordinate of the second point
     * @param color the colour of the segment
     */
    public Segment(double u, double v, double x, double y, MyColor color)
    {
        this.u = u;
        this.v = v;
        this.x = x;
        this.y = y;
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
        double v1 = u - x;
        double v2 = y - v;
        double c = u * v2 + v * v1;
        return new Equation(v2, v1, c);
    }

    /**
     * Given an line equation f(x, y) = 0 and a number c, such
     * that (f(u, v) - c) * (f(x, y) - c) is strictly negative,
     * breaks the segment in two halfs s' and s'' such that
     * s' lies in the half-plane containing (u, v)
     * s'' lies in the half-plane containing (x, y).
     * Throws a RuntimeException if the precondition is not met.
     * @param equation the planar equation used to define the half-planes
     * @return an array containing two segments [s', s''] as previously
     *          described
     */
    public Segment[] breakSegment(Equation equation)
    {
        if (!equation.liesInTwoHalfs(this))
            throw new RuntimeException();
        double c = equation.getC();
        double t = (c - equation.compute(x, y)) /
            (equation.compute(u, v) - equation.compute(x, y));
        if (Math.abs(t)>1)
            System.out.println(t);
        double z1 = t * u + (1 - t) * x;
        double z2 = t * v + (1 - t) * y;
        return new Segment[]
            { new Segment(u, v, z1, z2, color), new Segment(z1, z2, x, y, color) };
    }

    /**
     * Equality method between two segments. There is no tolerance
     * when segment equality is tested (floating-point values must match).
     * @param other the segment with which equality is to be tested.
     * @return true iff both segments match in color and exact coordinates
     */
    public boolean equals(Segment other)
    {
        if (color != other.getColor())
            return false;
        return (  u == other.u && v == other.v &&
                  x==other.x && y == other.y )      ||
               (  x == other.u && y == other.v &&
                  u==other.x && v == other.y );
    }
}
