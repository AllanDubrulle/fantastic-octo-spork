package sdd.AJ.painterBSP.graphics;

import sdd.AJ.painterBSP.util.Eye;
import sdd.AJ.painterBSP.graphics.MyColor;
import sdd.AJ.painterBSP.BSPLib.Painter;


import sdd.AJ.painterBSP.graphics.MyColor;
import sdd.AJ.painterBSP.BSPLib.Painter;
import javafx.scene.shape.Line;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.beans.binding.DoubleBinding;
import sdd.AJ.painterBSP.util.*;
import sdd.AJ.painterBSP.graphics.*;

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
        line.setStroke(getFXColor(color));
        super.getChildren().add(line);
        //TODO REMOVE (DEBUG)
        line = new Line(0,
                             20,
                             width,
                             20);
        line.setStroke(Color.BLACK);
        super.getChildren().add(line);
    }

}
