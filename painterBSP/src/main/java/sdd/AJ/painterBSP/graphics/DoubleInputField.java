package sdd.AJ.painterBSP.graphics;

import sdd.AJ.painterBSP.graphics.DoubleField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.util.converter.DoubleStringConverter;
import javafx.scene.control.TextFormatter;


/**
 * A descendant of VBox containing one or multiple TextFields
 * of which inputs are restricted to decimal numbers.
 */
public class DoubleInputField extends GridPane
{

    private DoubleField[] fields;
    /**
     * Class constructor.
     * @param labels the labels of the fields. The amount of labels given
     *               is the amount of fields appearing
     */
    public DoubleInputField(String... labels)
    {
        super();
        if (labels.length == 0)
            throw new IllegalArgumentException("Amount of labels must exceed 0.");
        fields = new DoubleField[labels.length];
        Label lbl;
        HBox hb;
        for (int i = 0; i < labels.length; i++)
        {
            lbl = new Label(labels[i]);
            fields[i] = new DoubleField();
            add(lbl, 0, i);
            add(fields[i], 1, i);
        }
    }

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
