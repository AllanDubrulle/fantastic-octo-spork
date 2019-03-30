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

public class GraphicalPainter implements Painter
{
    private int xBound, yBound;
    private Segment[] lines;
    private final DoubleBinding parentWidthProperty;
    private final DoubleBinding parentHeightProperty;
    private final Group group;

        public GraphicalPainter(DoubleBinding parentWidthProperty, DoubleBinding parentHeightProperty)
    {
        this.group = new Group();
        this.xBound = 1;
        this.yBound = 1;
        this.lines = new Segment[] {};
        this.parentWidthProperty = parentWidthProperty;
        this.parentHeightProperty = parentHeightProperty;
        Line line = new Line(0,
                             20,
                             100,
                             20);
        line.setStroke(Color.BLUE);
        group.getChildren().add(line);
    }


    public void update(int newXBound, int newYBound, Segment[] newLines)
    {
        xBound = newXBound;
        yBound = newYBound;
        lines = newLines;
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
        //line.setStroke(ColorHandler.getFXColor(color));
        group.getChildren().add(line);
    }

    public Group getGroup()
    {
        return group;
    }

}
