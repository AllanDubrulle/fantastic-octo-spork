package sdd.AJ.painterBSP.graphics;

import sdd.AJ.painterBSP.util.*;
import sdd.AJ.painterBSP.graphics.*;
import sdd.AJ.painterBSP.BSPLib.*;
import sdd.AJ.painterBSP.BSPLib.Heuristic.*;
import java.util.List;
import java.util.Arrays;
import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class GraphicalCore
{
    private Eye eye;
    private List<Segment> segments;
    private Heuristic heuristic;
    private BSPTree tree;
    private GraphicalWindow window;
    private int xBound, yBound;
    private GraphicalPainter painter;

    public GraphicalCore(GraphicalWindow window)
    {
        this.window = window;
    }

    public void changeEye(double x, double y, double angle)
    {
        eye = new Eye(x, y, angle);
    }

    public void loadFile(File file)
        throws IOException
    {
        IllustrationInputReader iir = new IllustrationInputReader(file);
        xBound = iir.getXBound();
        yBound = iir.getYBound();
        segments = iir.getSegments();
        // Tree and eye are reset when new file is loaded
        // However the selected heuristic is not changed
        tree = null;
        eye = null;
        window.drawSegments(xBound, yBound, segments);
    }

    public void buildBSP()
    {
        tree = new BSPTree(segments, heuristic);
    }

    public void buildRandomBSP()
    {
        tree = BSPTree.RandomBSPTree(segments);
    }

    public void setHeuristic(Heuristic h)
    {
        heuristic = h;
    }

    public void handleReadingError()
    {
        tree = null;
        eye = null;
    }

    public void display(Painter p)
    {
        if (tree != null && eye != null)
            p.paint(tree, eye);
        else
        {
            if (tree != null)
                window.warn("No tree has been built.\n"+
                            "Please select a heuristic and proceed with\n"+
                            "the construction of a tree");
            else
                window.warn("Eye settings have not been specified.\n" +
                            "Please specify settings before selecting\n" +
                            "this option.");
           }
    }



}
