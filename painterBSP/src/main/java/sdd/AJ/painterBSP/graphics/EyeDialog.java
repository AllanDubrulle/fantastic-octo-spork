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
 * Dialog box used to specify a new step for the interactive
 * mode (where the eye is moved using the AZEQSD keys).
 */
public class EyeDialog extends Dialog<Double>
{
    /**
     * Class constructor.
     */
    public EyeDialog()
    {
        DialogPane pane = this.getDialogPane();
        DoubleInputField dif = new DoubleInputField("Pas : ");
        pane.setContent(dif);
        setHeaderText("Veuillez préciser la taille d'un\npas de l'oeil.\n"+
                      "Confirmer sans entrer de valeur\nrevient à mettre le pas à zéro.");
        setTitle("Paramètres de l'oeil (interactif)");
        pane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        super.setResultConverter(dialogButton -> {
                if (dialogButton.equals(ButtonType.OK))
                {
                    return dif.getValues()[0];
                }
                return null;
            });
    }
}
