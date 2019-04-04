package sdd.AJ.painterBSP.graphics;

import javafx.beans.binding.DoubleBinding;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import sdd.AJ.painterBSP.util.MyColor;


/**
 * Class used to draw segments.
 * @see sdd.AJ.painterBSP.graphics.Illustrator
 * @see sdd.AJ.painterBSP.graphics.GraphicalPainter
 */
public abstract class AbstractIllustrator extends Group
{
    protected int xBound, yBound;
    protected final DoubleBinding parentWidthProperty;
    protected final DoubleBinding parentHeightProperty;

    /**
     * Class constructor.
     * @param parentWidthProperty the width property of the container,
     * used to scale the illustrations (even if the window is resized).
     * @param parentHeightProperty the height property of the container,
     * used to scale the illustrations (even if the window is resized).
     */
    public AbstractIllustrator(DoubleBinding parentWidthProperty, DoubleBinding parentHeightProperty)
    {
        super();
        this.parentWidthProperty = parentWidthProperty;
        this.parentHeightProperty = parentHeightProperty;
        this.xBound = 1;
        this.yBound = 1;
    }

    /**
     * Removes all content from the illustration.
     */
    public void clear()
    {
        getChildren().clear();
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
    protected void draw(double x1, double x2, double y1, double y2, MyColor color)
    {
        Line line = new Line(scale(x1 + xBound),
                             scale(-x2 + yBound),
                             scale(y1 + xBound),
                             scale(-y2 + yBound));
        line.setStroke(getFXColor(color));
        super.getChildren().add(line);
    }

    /**
     * Returns the JavaFX color matching with the parameter.
     * @param the color of which a FX version is requested
     * @return the matching JavaFX color
     */
    protected static Color getFXColor(MyColor color)
    {
        Color FXColor;
        switch (color)
        {
        case BLEU:
            FXColor = Color.BLUE;
            break;
        case ROUGE:
            FXColor = Color.RED;
            break;
        case ORANGE:
            FXColor = Color.ORANGE;
            break;
        case JAUNE:
            FXColor = Color.YELLOW;
            break;
        case NOIR:
            FXColor = Color.BLACK;
            break;
        case VIOLET:
            FXColor = Color.PURPLE;
            break;
        case MARRON:
            FXColor = Color.BROWN;
            break;
        case VERT:
            FXColor = Color.GREEN;
            break;
        case GRIS:
            FXColor = Color.GREY;
            break;
        case ROSE:
            FXColor = Color.PINK;
            break;
        default:
            throw new RuntimeException();
        }
        return FXColor;
    }

    /**
     * Creates a rectangular border around the illustration.
     * @param x      half of the x bound of the drawing
     * @param y      half of the y bound of the drawing
     * @param margin the padding between the bounds of the drawing
     *               and the container
     */
    protected void createBorder(double x, double y, int margin)
    {
        Rectangle border = new Rectangle(scale(2* (x + margin)),
                                         scale(2* (y + margin)));
        border.setX(-scale(margin));
        border.setY(-scale(margin));
        border.setStyle("-fx-stroke:black; -fx-fill: rgba(0, 0, 0, 0);");
        getChildren().add(border);
    }

}
