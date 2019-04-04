package sdd.AJ.painterBSP.graphics;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;

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
        DoubleInput dif = new DoubleInput("Abscisse : ",
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
