package sdd.AJ.painterBSP.graphics;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

/**
 * Textfield restricted to floating-point inputs in a decimal format.
 */
public class DoubleField extends TextField
{
    public DoubleField()
    {
        super();
        super.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String z)
                {
                    if (!(z.matches("\\d*") || z.matches("\\d*\\.\\d*")
                          || (z.matches("-\\d*") || z.matches("-\\d*\\.\\d*"))))
                        setText(oldValue);
                }
            });
        setPromptText("0");
    }

    /**
     * Returns the value currently entered in the field.
     * If no text is entered, returns 0
     * @return the value currently given, 0 if none
     */
    public double getValue()
    {
        if (getText().equals(""))
            return 0;
        return Double.parseDouble(getText());
    }

}
