package sdd.AJ.painterBSP.graphics;

import java.util.List;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.beans.binding.DoubleBinding;
import sdd.AJ.painterBSP.util.*;
import sdd.AJ.painterBSP.graphics.*;

public abstract class AbstractIllustrator extends Group
{
    protected int xBound, yBound;
    protected final DoubleBinding parentWidthProperty;
    protected final DoubleBinding parentHeightProperty;

    public AbstractIllustrator(DoubleBinding parentWidthProperty, DoubleBinding parentHeightProperty)
    {
        super();
        this.parentWidthProperty = parentWidthProperty;
        this.parentHeightProperty = parentHeightProperty;
        this.xBound = 1;
        this.yBound = 1;
    }

    public void clear()
    {
        getChildren().clear();
    }

    private double scale(double toResize)
    {
        double width = parentWidthProperty.get();
        double height = parentHeightProperty.get();
        if (height > width)
            return toResize * width / (2 * (xBound + GraphicalCore.MARGIN));
        else
            return toResize * height / (2 * (yBound + GraphicalCore.MARGIN));
    }

    protected void draw(double x1, double x2, double y1, double y2, MyColor color)
    {
        Line line = new Line(scale(x1 + xBound),
                             scale(-x2 + yBound),
                             scale(y1 + xBound),
                             scale(-y2 + yBound));
        line.setStroke(getFXColor(color));
        super.getChildren().add(line);
    }

    protected static Color getFXColor(MyColor myColor)
    {
        Color color;
        switch (myColor)
        {
        case BLEU:
            color = Color.BLUE;
            break;
        case ROUGE:
            color = Color.RED;
            break;
        case ORANGE:
            color = Color.ORANGE;
            break;
        case JAUNE:
            color = Color.YELLOW;
            break;
        case NOIR:
            color = Color.BLACK;
            break;
        case VIOLET:
            color = Color.PURPLE;
            break;
        case MARRON:
            color = Color.BROWN;
            break;
        case VERT:
            color = Color.GREEN;
            break;
        case GRIS:
            color = Color.GREY;
            break;
        case ROSE:
            color = Color.PINK;
            break;
        default:
            throw new RuntimeException();
        }
        return color;
    }

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
