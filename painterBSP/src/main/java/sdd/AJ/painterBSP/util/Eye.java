package sdd.AJ.painterBSP.util;

import java.util.List;

import sdd.AJ.painterBSP.BSPLib.Painter;

/**
 * Represents an eye located at given coordinates,
 * and in a specified direction.
 */
public class Eye
{
    public static final int COVERS = 1;
    public static final int INTERSECTS = 0;
    public static final int OUT_OF_VIEW = -1;

    private double x;
    private double y;
    private double angle;

    /**
     * Class constructor.
     * @param x      x-coordinate of the eye
     * @param y      y-coordinate of the eye
     * @param angle  angle representing the forward direction
     */
    public Eye(double x, double y, double angle)
    {
        this.x = x;
        this.y = y;
        this.setAngle(angle);
    }

    /**
     * Updates all fields of the eye with given parameters.
     * @param x      new x-coordinate of the eye
     * @param y      new y-coordinate of the eye
     * @param angle  new angle representing the forward direction
     */
    public void update(double x, double y, double angle)
    {
        this.x = x;
        this.y = y;
        this.setAngle(angle);
    }

    /**
     * Resets all parameters to 0 (both coordinates and angle).
     */
    public void resetPosition()
    {
        this.x = 0;
        this.y = 0;
        this.angle = 0;
    }

    /**
     * Getter for x
     * @return the x-coordinate of the eye
     */
    public double getX()
    {
        return x;
    }

    /**
     * Getter for y
     * @return the y-coordinate of the eye
     */
    public double getY()
    {
        return y;
    }

    /**
     * Getter for angle
     * @return the angle representing the direction of view
     */
    public double getAngle()
    {
        return angle;
    }

    /**
     * Setter for angle. Ensures angle remains between 0 and 2 * pi.
     * @param angle an angle representing the new direction of view
     */
    public void setAngle(double angle)
    {
        double pi2 = 2* Math.PI;
        this.angle = ( (angle % pi2) + pi2) % pi2;
    }

    /**
     * Draws each (visible) segment in a list using the given painter.
     * @param list the list of segments to be drawn (if necessary)
     * @param p the painter tasked with drawing
     */
    public void visualiseList(List<Segment> list, Painter p)
    {
        for (Segment s: list)
        {
            this.visualise(s, p);
        }
    }

    /**
     * Instructs the painter to draw the given segment
     * if it is visible by this instance of eye.
     * @param s the segment to be drawn, if necessary
     * @param p the painter tasked with drawing
     */
    public void visualise(Segment s, Painter p)
    {
        switch (isVisible(s.u, s.v, s.x, s.y))
        {
            case COVERS:
                p.draw(0, 1, s.getColor());
                break;
            case INTERSECTS:
                double[] proportion = getVisibleProportion(s.u, s.v, s.x, s.y);
                p.draw(proportion[0], proportion[1], s.getColor());
                break;
            default: // OUT_OF_VIEW
                break;
        }

    }

    /**
     * Computes for a visible segment how much of the eye span
     * it covers (by giving the percentage to be filled in).
     * Assumes that isVisible applied to the segment returns INTERSECTS
     * @param u1 the first point's x coordinate
     * @param v1 the first point's y coordinate
     * @param u2 the second point's x coordinate
     * @param v2 the second point's y coordinate
     * @return an array of two double containing the start
     *         and end such that [start, end] is the subset of
     *         [0, 1] to be filled in.
     */
    private double[] getVisibleProportion(double u1, double v1, double u2, double v2)
    {
        double start, end;
        double a = Math.cos(angle);
        double b = Math.sin(angle);
        // We center everything to simplify calculations.
        u1 = u1 - this.x;
        v1 = v1 - this.y;
        u2 = u2 - this.x;
        v2 = v2 - this.y;
        // temp_i is cos(theta_i) where phi_i is the angle between
        // (a, b) and (ui, vi) for i = 1, 2
        double temp1 = (a * u1 + b * v1)/Math.hypot(u1, v1);
        double temp2 = (a * u2 + b * v2)/ Math.hypot(u2, v2);
        temp1 = Math.acos(temp1);
        temp2 = Math.acos(temp2);
        // now temp_i is the unsigned phi, to sign it we use
        // the fact that being to the right => negative angle
        double phi1 = isToTheRight(u1, v1) ? -temp1:  temp1;
        double phi2 = isToTheRight(u2, v2) ? -temp2 : temp2;

        // If the arc length between the angles is lower than pi, then
        // we can directly map the angles in [0, 1] using function f

        if (Math.abs(phi1 - phi2) <= Math.PI)
        {
            start= f(phi1);
            end = f(phi2);
        }

        // Otherwise, there are extreme cases which must be distinguished
        else
        {
            if( Math.abs(phi1) <= Math.PI /4)
            {
                start = f(phi1);
                end = 1- f(phi2);
            }
            else
            {
                start = f(phi2);
                end = 1 - f(phi1);
            }
        }
        return new double[] { start, end };
    }

    /**
     * Decreasing bijection between [-pi/4, pi/4] and [0, 1]
     * extended as follows: if x < -pi/4, then f(x) = 1
     *                      if x > pi/4 then f(x) = 0
     * @param x the function argument
     * @return f(x)
     */
    private double f(double x)
    {
        if (x > Math.PI/4)
            return 0;
        else if (x < -Math.PI/4)
            return 1;
        return 1 - (x + Math.PI/4) * (2 / Math.PI);
    }


    /**
     * Determines whether a segment is visible or not.
     * @param u1 the first point's x coordinate
     * @param v1 the first point's y coordinate
     * @param u2 the second point's x coordinate
     * @param v2 the second point's y coordinate
     * @return Eye.COVERS if the segment covers the whole
     * viewpoint, Eye.INTERSECTS if the segment is partially
     * visible or Eye.OUT_OF_VIEW if it is completely invisible.
     */
    public int isVisible(double u1, double v1, double u2, double v2)
    {
        double a = Math.cos(angle);
        double b = Math.sin(angle);
        // We center everything to simplify calculations.
        u1 = u1 - this.x;
        v1 = v1 - this.y;

        u2 = u2 - this.x;
        v2 = v2 - this.y;
        // We calculate the scalar product between the parameters and (a, b)
        double scal1 = a * u1 + b * v1;
        double scal2 = a * u2 + b * v2;

        // To test whether a point is visible, we use the scalar
        // product. A point (u, v) is visible iff its angular coordinate
        // if at most pi/4 from angle iff scalar product
        // between (a, b) and (u, v)/||(u, v)|| is at least sqrt(2)/2

        if ( scal1 >= Math.hypot(u1, v1) * Math.sqrt(2)/2 )
            return INTERSECTS;
        else if ( scal2 >= Math.hypot(u2, v2) * Math.sqrt(2)/ 2)
            return INTERSECTS;
        // If both points lie on a same side (left or right) of
        // the eye, then the segment is not visible.
        // Moreover, the segment cannot be seen if both points
        // lie beneath the eye.
        if ((isToTheRight(u1, v1) && isToTheRight(u2, v2)) ||
            (!isToTheRight(u1, v1) && !isToTheRight(u2, v2)) ||
            (scal1 <= 0 && scal2 <= 0))
            return OUT_OF_VIEW;
        /* We can now assume that both points lie on a different side
         * of the plane and that at least one is "above" the eye (relative
         * to angle).
         *
         * Assuming this, the view is completely obstructed if and only if
         * (0, 0) is beneath the line defined by the points (u1, v1) and
         * (u2, v2).
         *
         * Assume one of the two points is to the right of the eye.
         * Thus, the other is to the left of the eye, thus the other one is
         * to the left (and may lie beneath the eye).
         */

        if (isToTheRight(u1, v1))
        {
            if (!beneathOrigin(u1, v1, u2, v2))
                return COVERS;
            else
                return OUT_OF_VIEW;
        }
        else if (isToTheRight(u2, v2))
        {
            if (!beneathOrigin(u2, v2, u1, v1))
                return COVERS;
        }

        return OUT_OF_VIEW;
    }



    /**
     * Returns true iff the point (u, v) is to the right of (0, 0),
     * relatively to the direction given by angle.
     */
    private boolean isToTheRight(double u, double v)
    {
        return Math.sin(angle) * u - Math.cos(angle) * v >= 0;
    }

    /**
     * Returns true iff the line represented by the normal
     * vector ((v2 - v1), (u1 - u2)), is beneath (0, 0),
     * relatively to the direction given by the normal vector
     * (that is the vector (u2 - u1, v2 - v1) rotated 90 degrees to the right).
     */
    private boolean beneathOrigin(double u1, double v1, double u2, double v2)
    {
        return (v2 - v1) * u1 + (u1 - u2) * v1 < 0;
    }

}
