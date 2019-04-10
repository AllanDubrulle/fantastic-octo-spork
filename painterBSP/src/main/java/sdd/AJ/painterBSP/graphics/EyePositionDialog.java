package sdd.AJ.painterBSP.graphics;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;

/**
 * Dialog box used to specify the coordinates and orientation of the eye,
 * used when a heavy file is loaded (instead of interactive mode).
 */
public class EyePositionDialog extends Dialog<double[]>
{
    /**
     * Class constructor.
     */
    public EyePositionDialog()
    {
        DialogPane pane = this.getDialogPane();
        DoubleInput dif = new DoubleInput("Abscisse : ",
                                                    "Ordonn\u00e9e : ",
                                                    "Angle : ");
        pane.setContent(dif);
        setHeaderText(String.format("S\u00e9lectionnez les coordonn\u00e9es%n"+
                                    "et l'orientation de l'oeil."));
        setTitle("Param\u00e8tres de l'oeil (mode non interactif)");
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
