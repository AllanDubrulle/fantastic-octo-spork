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

    protected List<Segment> lines;

    public Illustrator(DoubleBinding parentWidthProperty, DoubleBinding parentHeightProperty)
    {
        super(parentWidthProperty, parentHeightProperty);
        lines = null;
    }

    public void draw()
    {
        if (lines != null)
            for (Segment s : lines)
                {
                    super.draw(s.x1, s.x2, s.y1, s.y2, s.getColor());
                }
        // Temporary to see Eye(0, 0, 0)
        // TODO: remove
        super.draw(0, 0, 100, 100, MyColor.BLEU);
        super.draw(0, 0, 100, -100, MyColor.BLEU);
        
    }

    public void update(int newXBound, int newYBound, List<Segment> newLines)
    {
        xBound = newXBound;
        yBound = newYBound;
        lines = newLines;
    }
}
