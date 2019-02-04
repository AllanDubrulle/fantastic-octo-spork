package sdd.AJ.painterBSP.graphics;

import javafx.scene.shape.Line;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.beans.binding.DoubleBinding;
import sdd.AJ.painterBSP.util.*;
import sdd.AJ.painterBSP.graphics.*;


public class Illustrator extends Group
{
    private int xBound, yBound;
    private Segment[] lines;
    private final DoubleBinding parentWidthProperty;
    private final DoubleBinding parentHeightProperty;

    public Illustrator(DoubleBinding parentWidthProperty, 
            DoubleBinding parentHeightProperty)
    {
        super();
        this.xBound = 1;
        this.yBound = 1;
        this.lines = new Segment[] {};
        this.parentWidthProperty = parentWidthProperty;
        this.parentHeightProperty = parentHeightProperty;
    }

    public void draw()
    {
        for (Segment s: lines)
            {
                Line line = new Line(scale(s.x1 + xBound),
                                     scale(s.x2 + yBound),
                                     scale(s.y1 + xBound),
                                     scale(s.y2 + yBound));
                line.setStroke(getFXColor(s));
                super.getChildren().add(line);
            }
    }

    public void clear()
    {
        getChildren().clear();
    }
    
    public void update(int newXBound, int newYBound, Segment[] newLines)
    {
        xBound = newXBound;
        yBound = newYBound;
        lines = newLines;
    }

    private double scale(double toResize)
    {
        double width =  parentWidthProperty.get();
        double height =  parentHeightProperty.get();
        if (height > width)
            return toResize * width / (2 * xBound);
        else
            return toResize * height / (2 * yBound);
    }
       
    private Color getFXColor(Segment s)
    {
        Color color;
        switch (s.getColor())
            {
            case BLEU: color = Color.BLUE;
                break;
            case ROUGE: color = Color.RED;
                break;
            case ORANGE: color = Color.ORANGE;
                break;
            case JAUNE: color = Color.YELLOW;
                break;
            case NOIR: color = Color.BLACK;
                break;
            case VIOLET: color = Color.PURPLE;
                break;
            case MARRON: color = Color.BROWN;
                break;
            case VERT: color = Color.GREEN;
                break;
            case GRIS: color = Color.GREY;
                break;
            case ROSE: color = Color.PINK;
                break;
            default: throw new RuntimeException();
            }
        return color;
    }

}
