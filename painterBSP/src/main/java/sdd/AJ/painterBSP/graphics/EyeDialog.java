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

/**
 * Dialog box used to specify a new step for the interactive
 * mode (where the eye is moved using the AZEQSD keys).
 */
public class EyeDialog extends Dialog<Double>
{
    /**
     * Class constructor.
     * A TextField is created, and its input is restricted to doubles.
     * If a ill-formed String is pasted into the field, the test is
     * reverted to its previous value.
     * When closed with confirmation, returns the content of the field
     * to the caller (of the showAndWait method).
     */
    public EyeDialog()
    {
        DialogPane pane = this.getDialogPane();
        TextField stepField = new TextField();
        pane.setContent(stepField);
        setHeaderText("Veuillez préciser la taille d'un pas de l'oeil..\n"+
                      "Confirmer sans entrer de valeur revient à annuler l'opération.");
        setTitle("Eye setup.");
        pane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        stepField.textProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String z)
            {
                if (!(z.matches("\\d*") || z.matches("\\d*\\.\\d*")))
                    stepField.setText(oldValue);
            }
        });


        super.setResultConverter(dialogButton -> {
                if (dialogButton.equals(ButtonType.OK) &&
                    !(stepField.getText().equals("")))
            {
                return Double.parseDouble(stepField.getText());
            }
            return null;
        });
    }
}
