package sdd.AJ.painterBSP.graphics;

import java.util.List;

import javafx.beans.binding.DoubleBinding;
import sdd.AJ.painterBSP.util.MyColor;
import sdd.AJ.painterBSP.util.Segment;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class Illustrator extends AbstractIllustrator
{

    private List<Segment> lines;
    private boolean eyeDrawn;
    private int xBound, yBound;

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
        this.xBound = 1;
        this.yBound = 1;
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
                    draw(s.u, s.v, s.x, s.y, s.getColor());
                }
            createBorder(xBound, yBound, GraphicalCore.MARGIN);
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
            draw(x, y, x + dx, y + dy, MyColor.NOIR);
            dx =  (bound / 10) * (a + b) * Math.sqrt(2) / 2;
            dy =  (bound / 10) *(b - a) * Math.sqrt(2) / 2;
            draw(x, y, x + dx, y + dy, MyColor.NOIR);
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

    /**
     * Given a double, multiplies it by a constant so that when used to
     * draw, the drawing is scaled to fit in the window.
     * @param  toResize the value to be rescaled
     * @return a properly scaled equivalent of the argument
     */
    private double scale(double toResize)
    {
        double width = parentWidthProperty.get();
        double height = parentHeightProperty.get();
        if (height > width)
            return toResize * width / (2 * (xBound + GraphicalCore.MARGIN));
        else
            return toResize * height / (2 * (yBound + GraphicalCore.MARGIN));
    }

    /**
     * Draws a colored line joining points (x1, x2) and (y1, y2).
     * The line is scaled appropriately so that the drawing fits the
     * container.
     * @param x1 the first point's x-coordinate
     * @param x2 the first point's y-coordinate
     * @param y1 the second point's x-coordinate
     * @param y2 the second point's y-coordinate
     * @param color the color to be used in the drawing
     */
    private void draw(double x1, double x2, double y1, double y2, MyColor color)
    {
        Line line = new Line(scale(x1 + xBound),
                             scale(-x2 + yBound),
                             scale(y1 + xBound),
                             scale(-y2 + yBound));
        line.setStroke(getFXColor(color));
        super.getChildren().add(line);
    }

        /**
     * Creates a rectangular border around the illustration.
     * @param x      half of the x bound of the drawing
     * @param y      half of the y bound of the drawing
     * @param margin the padding between the bounds of the drawing
     *               and the container
     */
    private void createBorder(double x, double y, int margin)
    {
        Rectangle border = new Rectangle(scale(2* (x + margin)),
                                         scale(2* (y + margin)));
        border.setX(-scale(margin));
        border.setY(-scale(margin));
        border.setStyle("-fx-stroke:black; -fx-fill: rgba(0, 0, 0, 0);");
        getChildren().add(border);
        // Outer border to prevent the drawing from shaking:
        margin = 2* margin;
        border = new Rectangle(scale(2* (x + margin)),
                               scale(2* (y + margin)));
        border.setX(-scale(margin));
        border.setY(-scale(margin));
        border.setStyle("-fx-fill: rgba(0, 0, 0, 0);");
        getChildren().add(border);
    }
}
