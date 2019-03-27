package sdd.AJ.painterBSP.util;

import sdd.AJ.painterBSP.util.Segment;
import sdd.AJ.painterBSP.BSPLib.Painter;
import java.lang.Math;
import java.util.List;


public class Eye
{

    private double x;
    private double y;
    private double angle;

    public double getX()
    {
        return x;
    }

    public void setX(double x)
    {
        this.x = x;
    }

    public double getY()
    {
        return y;
    }

    public void setY(double y)
    {
        this.y = y;
    }
    public double getAngle()
    {
        return angle;
    }

    public void setAngle(double angle)
    {
        // Mesure principale entre -PI et PI
        while (angle >  Math.PI)
            angle -= 2 * Math.PI;
        while (angle <= -Math.PI)
            angle += 2 * Math.PI;
        this.angle = angle;
    }

    public void drawList(List<Segment> list, Painter p)
    {
        for (Segment s: list)
        {
            this.draw(s, p);
        }
    }


    public void draw(Segment s, Painter p)
    {
        double dx1 = s.x1 - x;
        double dx2 = s.x2 - y;
        double angleX = rotateIfNecessary(Math.atan2(dx1, dx2));
        double dy1 = s.y1 - x;
        double dy2 = s.y2 - y;
        double angleY = rotateIfNecessary(Math.atan2(dy1, dy2));
        if (angleX < angleY)
        {
            double temp = angleX;
            angleX = angleY;
            angleY = angleX;
        }
        // Ici  on suppose que angle X plus grand que angle Y
        if (isVisible(angleX, angleY))
        {
            double[] visibleProp = getVisibleProportion(angleX, angleY);
            p.draw(visibleProp[0], visibleProp[1], s.getColor());
        }
    }

    private double[] getVisibleProportion(double angleX, double angleY)
    {
        if (angleX > this.angle + Math.PI/2)
            angleX = this.angle + Math.PI/2;
        if (angleY < this.angle)
            angleY = this.angle;
        //Ici on considere la bij entre [0, 1] et [angle, angle + pi/2]
        double start = (angleY - this.angle) * (2/Math.PI);
        double end = (angleX - this.angle) * (2/Math.PI);;
        return new double[] {start, end};
    }

    private boolean isVisible(double angle1, double angle2)
    {
        // Hypothese: deja rotationne si c'etait necessaire
        boolean ok1 = this.angle < angle1
            && angle1 < this.angle + 2*Math.PI;
        boolean ok2 = this.angle < angle2
            && angle2 < this.angle + 2*Math.PI;
        // cas ou aucun point n'est dans le cone mais le segment est visible
        boolean ok3 = angle1 > this.angle + Math.PI/2  && angle2 < this.angle;
        return ok1 || ok2 || ok3;
    }

    private double rotateIfNecessary(double angleToRotate)
    {
        if (this.angle > Math.PI/2 && angleToRotate < -Math.PI/2)
            return angleToRotate + 2* Math.PI;
        else
            return angleToRotate;
    }

}
