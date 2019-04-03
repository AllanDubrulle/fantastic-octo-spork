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

/**
 * Main logic class of the UI programme.
 */
public class GraphicalCore
{
    private Eye eye;
    private List<Segment> segments;
    private BSPTree tree;
    private GraphicalWindow window;
    private int xBound, yBound;
    private boolean randomConstruction;
    private Heuristic heuristic;
    private double step = 0.1;

    public static final int MARGIN = 50;

    /**
     * Class constructor.
     * @param window the main window of the programme
     */
    public GraphicalCore(Stage stage)
    {
        this.window = new GraphicalWindow(stage, this);
        window.requestFocus();
        this.eye = new Eye(0, 0, 0);
        resetValues();
        randomConstruction = false;
        heuristic = null;
    }

    /**
     * Resets values of the core, that is the position of the eye,
     * the loaded segments and the tree (if they have been created or altered).
     * The selected heuristic is not changed when this method is called.
     */
    private void resetValues()
    {
        eye.resetPosition();
        window.displayEyeParameters(0, 0, 0);
        segments = null;
        tree = null;
        xBound=-1;
        yBound =-1;
    }


    /**
     * Changes the parameters of the eye. Restricts possible values
     * to the bounds of the loaded scene plus a margin.
     * @param x     the new x-coordinate of the eye
     * @param y     the new y-coordinate of the eye
     * @param angle the new angle of the eye
     */
    private void changeEye(double x, double y, double angle)
    {
        if (x > xBound + MARGIN)
            x = xBound + MARGIN;
        else if (x < - (xBound + MARGIN))
            x = - xBound - MARGIN;
        if (y > yBound + MARGIN)
            y = yBound + MARGIN;
        else if (y < - (yBound + MARGIN))
            y = - yBound - MARGIN;
        eye.update(x, y, angle);
        window.displayEyeParameters(x, y, angle);
        window.drawEye(eye.getX(), eye.getY(), eye.getAngle());
    }

    /**
     * Loads a new file using an IllustrationInputReader.
     * If the load is successful, resets all values (by calling
     * the resetValues() method. Otherwise throws an exception to
     * be handled by the caller.
     * @param file the file to be loaded into the programme
     * @throws IOException
     * @throws FileFormatException
     */
    public void loadFile(File file)
        throws IOException, FileFormatException
    {
        IllustrationInputReader iir = new IllustrationInputReader(file);
        resetValues();
        xBound = iir.getXBound();
        yBound = iir.getYBound();
        segments = iir.getSegments();
        window.loadSegments(xBound, yBound, segments);
        window.drawEye(eye.getX(), eye.getY(), eye.getAngle());
        window.requestFocus();
    }

    /**
     * Builds a BSP tree using the selected heuristic and the loaded
     * segments. If either one of these is not specified, the window
     * notifies the user.
     */
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

    /**
     * Applies the painter's algorithm to the BSPTree if it is
     * built, using the GraphicalPainter instance provided by the caller.
     * @param p the painter used in the painter's algorithm
     */
    public void display(GraphicalPainter p)
    {
        if (tree != null)
        {
            p.clear();
            tree.paintersAlgorithm(p, eye);
        }
    }

    /**
     * Setter for the heuristic variable.
     * @param h the new heuristic
     */
    public void setHeuristic(Heuristic h)
    {
        randomConstruction = false;
        heuristic = h;
    }

    /**
     * Used to enable the random heuristic (in which the list is
     * shuffled then processed in a linear fashion.
     */
    public void activateRandom()
    {
        randomConstruction = true;
        heuristic = null;
    }

    /**
     * Getter for the window of the core.
     * @return window the instance of GraphicalWindow bound to this core
     */
    public GraphicalWindow getWindow()
    {
        return this.window;
    }

    /**
     * Setter for the step variable.
     * @param newStep the new value for step
     */
    public void setStep(double newStep)
    {
        step = newStep;
    }

    /**
     * Moves the eye up a step.
     */
    public void eyeUp()
    {
        changeEye(eye.getX(), eye.getY() + step, eye.getAngle());
    }

    /**
     * Moves the eye down a step.
     */
    public void eyeDown()
    {
        changeEye(eye.getX(), eye.getY() - step, eye.getAngle());
    }

    /**
     * Moves the eye a step to the left.
     */
    public void eyeLeft()
    {
        changeEye(eye.getX() - step, eye.getY(), eye.getAngle());
    }

    /**
     * Moves the eye a step to the right.
     */
    public void eyeRight()
    {
        changeEye(eye.getX() + step, eye.getY(), eye.getAngle());
    }

    /**
     * Turns the eye 0.01 radians to the left.
     */
    public void eyeRotateLeft()
    {
        changeEye(eye.getX(), eye.getY(), eye.getAngle() +  0.01);
    }

    /**
     * Turns the eye 0.01 radians to the right.
     */
    public void eyeRotateRight()
    {
        changeEye(eye.getX(), eye.getY(), eye.getAngle() - 0.01);
    }
}
