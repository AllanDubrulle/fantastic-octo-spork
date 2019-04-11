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
     * @param obj the segment with which equality is to be tested.
     * @return true iff both segments match in color and exact coordinates
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Segment other = (Segment) obj;
        if (color != other.color)
            return false;
        if (Double.doubleToLongBits(u) != Double.doubleToLongBits(other.u))
            return false;
        if (Double.doubleToLongBits(v) != Double.doubleToLongBits(other.v))
            return false;
        if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
            return false;
        if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
            return false;
        return true;
    }
    
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((color == null) ? 0 : color.hashCode());
        long temp;
        temp = Double.doubleToLongBits(u);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(v);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(x);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
