package sdd.AJ.painterBSP.graphics;

import java.util.List;
import java.util.ArrayList;
import javafx.scene.shape.Line;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.beans.binding.DoubleBinding;
import sdd.AJ.painterBSP.util.*;
import sdd.AJ.painterBSP.graphics.*;

public class Illustrator extends AbstractIllustrator
{

    private List<Segment> lines;
    private boolean eyeDrawn;

    public Illustrator(DoubleBinding parentWidthProperty, DoubleBinding parentHeightProperty)
    {
        super(parentWidthProperty, parentHeightProperty);
        lines = null;
        eyeDrawn = false;
    }

    public void draw()
    {
        if (lines != null)
            for (Segment s : lines)
                {
                    super.draw(s.x1, s.x2, s.y1, s.y2, s.getColor());
                }
        super.createBorder(xBound, yBound, GraphicalCore.MARGIN);
    }

    public void drawEye(double x, double y, double angle)
    {
        if (eyeDrawn)
        {
            int i = super.getChildren().size();
            super.getChildren().remove(i-1);
            super.getChildren().remove(i-2);
        }

        if (lines != null) // Checks if a scene is loaded
        {
            int bound = xBound < yBound ? yBound : xBound;
            double a = Math.cos(angle);
            double b = Math.sin(angle);
            double dx = (bound / 10) * (a - b) * Math.sqrt(2) / 2;
            double dy = (bound / 10) * (a + b) * Math.sqrt(2) / 2;
            super.draw(x, y, x + dx, y + dy, MyColor.NOIR);
            dx =  (bound / 10) * (a + b) * Math.sqrt(2) / 2;
            dy =  (bound / 10) *(b - a) * Math.sqrt(2) / 2;
            super.draw(x, y, x + dx, y + dy, MyColor.NOIR);
            eyeDrawn = true;
        }
    }


    @Override
    public void clear()
    {
        super.clear();
        eyeDrawn = false;
    }

    public void update(int newXBound, int newYBound, List<Segment> newLines)
    {
        xBound = newXBound;
        yBound = newYBound;
        lines = newLines;
    }
}
