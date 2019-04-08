package sdd.AJ.painterBSP.graphics;

import javafx.scene.paint.Color;
import javafx.beans.binding.DoubleBinding;
import javafx.scene.shape.Line;
import sdd.AJ.painterBSP.BSPLib.Painter;
import sdd.AJ.painterBSP.util.MyColor;

/**
 * Graphical implementation of the Painter interface.
 * Descends from the AbstractIllustrator class.
 */
public class GraphicalPainter extends AbstractIllustrator
    implements Painter
{
    /**
     * Class constructor.
     * @see sdd.AJ.painterBSP.graphics.AbstractIllustrator
     * @param parentWidthProperty the width property of the container,
     * used to scale the view (even if the window is resized).
     * @param parentHeightProperty the height property of the container,
     * used to scale the view (even if the window is resized).
     */
    public GraphicalPainter(DoubleBinding parentWidthProperty, DoubleBinding parentHeightProperty)
    {
        super(parentWidthProperty, parentHeightProperty);
    }

    @Override
    public void draw(double start, double end, MyColor color)
    {
        double width = parentWidthProperty.get();
        double height = parentHeightProperty.get();
        Line line = new Line(start * width,
                             height/2,
                             end * width,
                             height/2);
        line.setStrokeWidth(10);
        line.setStroke(getFXColor(color));
        super.getChildren().add(line);
    }

    @Override
    public void clear()
    {
        super.clear();
        Line line = new Line(0,
                             parentHeightProperty.get()/2,
                             parentWidthProperty.get(),
                             parentHeightProperty.get()/2);
        line.setStrokeWidth(10);
        line.setStroke(Color.WHITE);
        super.getChildren().add(line);
    }

}
