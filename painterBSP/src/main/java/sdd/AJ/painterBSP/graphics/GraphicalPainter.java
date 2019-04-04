package sdd.AJ.painterBSP.graphics;

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

}
