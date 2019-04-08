package sdd.AJ.painterBSP.graphics;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;

/**
 * Dialog box used to specify a new step for the interactive
 * mode (where the eye is moved using the AZEQSD keys).
 */
public class EyeStepDialog extends Dialog<Double>
{
    /**
     * Class constructor.
     */
    public EyeStepDialog()
    {
        DialogPane pane = this.getDialogPane();
        DoubleInput dif = new DoubleInput("Pas : ");
        pane.setContent(dif);
        setHeaderText(String.format("Veuillez préciser la taille d'un%n"+
                                    "pas de l'oeil.%n"+
                                    "Confirmer sans entrer de valeur%n"+
                                    "revient à mettre le pas à zéro."));
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
