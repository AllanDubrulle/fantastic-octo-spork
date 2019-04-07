package sdd.AJ.painterBSP.graphics;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;


/**
 * A descendant of VBox containing one or multiple TextFields
 * of which inputs are restricted to decimal numbers.
 * @see sdd.AJ.painterBSP.graphics.DoubleField
 */
public class DoubleInput extends GridPane
{

    private DoubleField[] fields;
    
    /**
     * Class constructor.
     * @param labels the labels of the fields. The amount of labels given
     *               matches the amount of fields appearing
     */
    public DoubleInput(String... labels)
    {
        super();
        if (labels.length == 0)
            throw new IllegalArgumentException("Amount of labels must exceed 0.");
        fields = new DoubleField[labels.length];
        Label lbl;
        for (int i = 0; i < labels.length; i++)
        {
            lbl = new Label(labels[i]);
            fields[i] = new DoubleField();
            add(lbl, 0, i);
            add(fields[i], 1, i);
        }
    }

    /**
     * Reads the values in the different fields. If a field
     * has nothing inside, its value is considered to be 0.
     * @return the values in the fields in an array ordered
     * the same way as the labels are
     */
    public double[] getValues()
    {
        double[] val = new double[fields.length];
        for (int i = 0; i < fields.length; i++)
        {
            val[i] = fields[i].getValue();
        }
        return val;
    }

}
