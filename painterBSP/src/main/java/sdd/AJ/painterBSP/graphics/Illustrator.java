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

    /**
     * Class constructor.
     * @param parentWidthProperty the width property of the container,
     * used to scale the illustrations (even if the window is resized).
     * @param parentHeightProperty the height property of the container,
     * used to scale the illustrations (even if the window is resized).
     */
    public Illustrator(DoubleBinding parentWidthProperty, DoubleBinding parentHeightProperty)
    {
        super(parentWidthProperty, parentHeightProperty);
        lines = null;
        eyeDrawn = false;
    }

    /**
     * Draws the segments in the lines list if it is not null.
     */
    public void draw()
    {
        if (lines != null)
        {
            for (Segment s : lines)
                {
                    super.draw(s.u, s.v, s.x, s.y, s.getColor());
                }
            super.createBorder(xBound, yBound, GraphicalCore.MARGIN);
        }
    }

    /**
     * Draws the eye in the plane representation of the scene.
     * @param x     the x-coordinate of the eye
     * @param y     the y-coordinate of the eye
     * @param angle the angle the eye is facing
     */
    public void drawEye(double x, double y, double angle)
    {
        // If the eye is drawn, the old eye is removed from the drawing
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

    /**
     * Updates the bounds of the scene and the segments in the scene.
     * @param newXBound the xBound of the new scene
     * @param newYBound the yBound of the new scene
     * @param newLines the list of segments of the new scene
     */
    public void update(int newXBound, int newYBound, List<Segment> newLines)
    {
        xBound = newXBound;
        yBound = newYBound;
        lines = newLines;
    }
}
