package sdd.AJ.painterBSP.graphics;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.util.converter.DoubleStringConverter;
import javafx.scene.control.TextFormatter;
import sdd.AJ.painterBSP.graphics.DoubleInputField;

/**
 * Dialog box used to specify the coordinates and orientation of the eye,
 * used when a heavy file is loaded (instead of interactive mode).
 */
public class EyeDialog2 extends Dialog<double[]>
{
    /**
     * Class constructor.
     */
    public EyeDialog2()
    {
        DialogPane pane = this.getDialogPane();
        DoubleInputField dif = new DoubleInputField("Abscisse : ",
                                                    "Ordonnée : ",
                                                    "Angle : ");
        pane.setContent(dif);
        setHeaderText("Sélectionnez les coordonnées\net l'orientation de l'oeil.");
        setTitle("Paramètres de l'oeil (mode non interactif)");
        pane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        super.setResultConverter(dialogButton -> {
                if (dialogButton.equals(ButtonType.OK))
                {
                    return dif.getValues();
                }
                return null;
            });
    }
}
