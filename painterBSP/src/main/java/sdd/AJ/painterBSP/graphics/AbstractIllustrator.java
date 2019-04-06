package sdd.AJ.painterBSP.graphics;

import javafx.beans.binding.DoubleBinding;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import sdd.AJ.painterBSP.util.MyColor;


/**
 * Class used to draw segments.
 * @see sdd.AJ.painterBSP.graphics.Illustrator
 * @see sdd.AJ.painterBSP.graphics.GraphicalPainter
 */
public abstract class AbstractIllustrator extends Group
{
    protected final DoubleBinding parentWidthProperty;
    protected final DoubleBinding parentHeightProperty;

    /**
     * Class constructor.
     * @param parentWidthProperty the width property of the container,
     * used to scale the illustrations (even if the window is resized).
     * @param parentHeightProperty the height property of the container,
     * used to scale the illustrations (even if the window is resized).
     */
    public AbstractIllustrator(DoubleBinding parentWidthProperty, DoubleBinding parentHeightProperty)
    {
        super();
        this.parentWidthProperty = parentWidthProperty;
        this.parentHeightProperty = parentHeightProperty;
    }

    /**
     * Removes all content from the illustration.
     */
    public void clear()
    {
        getChildren().clear();
    }

    /**
     * Returns the JavaFX color matching with the parameter.
     * @param the color of which a FX version is requested
     * @return the matching JavaFX color
     */
    protected static Color getFXColor(MyColor color)
    {
        Color FXColor;
        switch (color)
        {
        case BLEU:
            FXColor = Color.BLUE;
            break;
        case ROUGE:
            FXColor = Color.RED;
            break;
        case ORANGE:
            FXColor = Color.ORANGE;
            break;
        case JAUNE:
            FXColor = Color.YELLOW;
            break;
        case NOIR:
            FXColor = Color.BLACK;
            break;
        case VIOLET:
            FXColor = Color.PURPLE;
            break;
        case MARRON:
            FXColor = Color.BROWN;
            break;
        case VERT:
            FXColor = Color.GREEN;
            break;
        case GRIS:
            FXColor = Color.GREY;
            break;
        case ROSE:
            FXColor = Color.PINK;
            break;
        default:
            throw new RuntimeException();
        }
        return FXColor;
    }


}
