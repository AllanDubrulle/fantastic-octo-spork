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
    private BSPTree tree;
    private GraphicalWindow window;
    private int xBound, yBound;
    private GraphicalPainter painter;
    private boolean randomConstruction;
    private Heuristic heuristic;

    public GraphicalCore(GraphicalWindow window)
    {
        this.window = window;
        resetValues();
        randomConstruction = false;
        heuristic = null;
    }

    private void resetValues()
    {
        // The selected heuristic is not changed
        eye = null;
        segments = null;
        tree = null;
        xBound=-1;
        yBound =-1;
        painter = null;
    }


    public void changeEye(double x, double y, double angle)
    {
        eye = new Eye(x, y, angle);
    }

    public void loadFile(File file)
        throws IOException
    {
        // Reset avant de charger nouveau fichier
        resetValues();
        IllustrationInputReader iir = new IllustrationInputReader(file);
        xBound = iir.getXBound();
        yBound = iir.getYBound();
        segments = iir.getSegments();
        window.loadSegments(xBound, yBound, segments);
    }

    public void buildBSP()
    {
        if (segments == null)
            window.warn("Aucun fichier n'est chargé.\n"+
                        "Veuillez charger un fichier valable.");

        else if (heuristic == null && !randomConstruction)
            window.warn("Aucune heuristique n'est sélectionnée.\n"+
                        "Veuillez en sélectionner une.");
        else
        {
            if (randomConstruction)
                tree = BSPTree.RandomBSPTree(segments);
            else
                tree = new BSPTree(segments, heuristic);
        }
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
                window.warn("Aucun arbre n'a été créé.\n"+
                            "Veuillez sélectionner un fichier\n"+
                            "et une heuristique puis confirmer.");
            else
                window.warn("Les paramètres de l'oeil n'ont pas été\n" +
                            "spécifiés.\n" +
                            "Soumettez ces paramètres avant de confirmer.");
           }
    }

    public void setHeuristic(Heuristic h)
    {
        randomConstruction = false;
        heuristic = h;
    }

    public void activateRandom()
    {
        randomConstruction = true;
        heuristic = null;
    }
}
