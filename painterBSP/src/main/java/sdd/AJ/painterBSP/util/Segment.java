package sdd.AJ.painterBSP.util;

import sdd.AJ.painterBSP.util.*;
import sdd.AJ.painterBSP.graphics.*;

public class Segment
{
    public final double x1, x2, y1, y2; //Premier point (x1, x2) et deuxieme point (y1, y2)
    private final MyColor color;

    public Segment(double x1, double x2, double y1, double y2, MyColor color) {
        this.x1 = x1;
        this.x2 =x2;
        this.y1 = y1;
        this.y2 = y2;
        this.color = color;
    }

    public MyColor getColor() {
        return color;
    }

    public Equation lineEquation() {
        double v1 = x1 - y1;
        double v2 = x2 - y2;
        return (x, y) -> (v1 * y  - v2 * x);
    }

    public Segment[] breakSegment(Equation equation, double c) {
        //TODO: delete following:
        //BROUILLON DOC: Etant donne une equation retourne
        //un tableau a deux elements  representant le segment
        //coupe en deux par la droite d'equation
        // equation.solve(x, y) = c
        double t = (-c - equation.solve(y1, y2))/
            (equation.solve(x1, x2) - equation.solve(y1, y2));
        double z1 = t * x1 + (1 - t) * y1;
        double z2 = t * x2 + (1 - t) * y2;
        return new Segment[]
            {new Segment(x1, x2, z1, z2, color),
             new Segment(z1, z2, y1, y2, color)};
    }
}
