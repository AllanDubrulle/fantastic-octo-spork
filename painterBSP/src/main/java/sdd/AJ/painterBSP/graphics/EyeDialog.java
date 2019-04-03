package sdd.AJ.painterBSP.graphics;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.util.converter.DoubleStringConverter;
import javafx.scene.control.TextFormatter;


public class EyeDialog extends Dialog<Double>
{
    public EyeDialog()
    {
        DialogPane pane = this.getDialogPane();
        TextField stepField = new TextField();
        pane.setContent(stepField);
        pane.setPrefSize(150.0, 150.0);
        setHeaderText("Please enter the step for the eye.");
        setTitle("Eye setup.");
        pane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        stepField.textProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String z)
            {
                if (!(z.matches("\\d*") || z.matches("\\d*\\.\\d*")))
                    stepField.setText(z.replaceAll("[^\\d]", ""));
            }
        });


        super.setResultConverter(dialogButton -> {
            if (dialogButton.equals(ButtonType.OK))
            {
                return Double.parseDouble(stepField.getText());
            }
            return null;
        });
    }
}
