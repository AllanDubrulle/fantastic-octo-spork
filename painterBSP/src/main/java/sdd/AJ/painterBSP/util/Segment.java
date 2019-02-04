package sdd.AJ.painterBSP.util;

import sdd.AJ.painterBSP.util.*;
import sdd.AJ.painterBSP.graphics.*;

public class Segment
{
    public final double x1, x2, y1, y2; //Premier point (x1, x2) et deuxieme point (y1, y2)
    private final MyColor color;

    public Segment(double x1, double x2, double y1, double y2, MyColor color)
    {
        this.x1 = x1;
        this.x2 =x2;
        this.y1 = y1;
        this.y2 = y2;
        this.color = color;
    }

    public MyColor getColor()
    {
        return color;
    }
}
