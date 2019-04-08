package sdd.AJ.painterBSP.graphics;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javafx.stage.Stage;
import sdd.AJ.painterBSP.BSPLib.BSPTree;
import sdd.AJ.painterBSP.BSPLib.Heuristic.Heuristic;
import sdd.AJ.painterBSP.util.Eye;
import sdd.AJ.painterBSP.util.FileFormatException;
import sdd.AJ.painterBSP.util.IllustrationInputReader;
import sdd.AJ.painterBSP.util.Segment;

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
    private double step;

    public static final int MARGIN = 50;

    /**
     * Class constructor.
     * @param stage the javafx stage which will contain the window
     */
    public GraphicalCore(Stage stage)
    {
        this.window = new GraphicalWindow(stage, this);
        window.requestFocus();
        this.eye = new Eye(0, 0, 0);
        resetValues();
        step = 3;
        randomConstruction = false;
        heuristic = null;
    }

    /**
     * Resets values of the core, that is the position of the eye,
     * the loaded segments and the tree (if they have been created or altered).
     * The selected heuristic is not changed when this method is called, nor
     * is the size of a step.
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
    public void changeEye(double x, double y, double angle)
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
        window.displayEyeParameters(eye.getX(), eye.getY(), eye.getAngle());
        window.drawEye(eye.getX(), eye.getY(), eye.getAngle());
    }

    /**
     * Loads a new file using an IllustrationInputReader.
     * If the load is successful, resets all values besides step
     * and heuristic, then sets variables according to new file.
     * Otherwise throws an exception to
     * be handled by the caller.
     * @param file the file to be loaded into the programme
     * @throws IOException - an I/O error occurs (e.g.file not found)
     * @throws FileFormatException - the submitted file is ill-formatted
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
        window.toggleInteractiveMode(segments.size() < 1000);
    }

    /**
     * Builds a BSP tree using the selected heuristic and the loaded
     * segments. If either one of these is not specified, the window
     * notifies the user.
     * @return true if a BSP has been built, false otherwise
     */
    public boolean buildBSP()
    {
        if (segments == null)
        {
            window.warn(String.format(
                        "Aucun fichier n'est chargé.%n"+
                        "Veuillez charger un fichier valable."));
            return false;
        }
        else if (heuristic == null && !randomConstruction)
        {
            window.warn(String.format(
                        "Aucune heuristique n'est sélectionnée.%n"+
                        "Veuillez en sélectionner une."));
            return false;
        }
        else
        {
            if (randomConstruction)
                tree = BSPTree.RandomBSPTree(segments);
            else
                tree = new BSPTree(segments, heuristic);
            return true;
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
