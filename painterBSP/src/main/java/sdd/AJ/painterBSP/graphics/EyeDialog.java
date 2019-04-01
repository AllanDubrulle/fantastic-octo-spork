package sdd.AJ.painterBSP.graphics;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;


public class EyeDialog extends Dialog<double[]>
{
    public EyeDialog()
    {
        DialogPane pane = this.getDialogPane();
        TextField xField = new TextField();
        TextField yField = new TextField();
        TextField angleField = new TextField();
        VBox fieldBox = new VBox(8);
        fieldBox.getChildren().addAll(xField, yField, angleField);
        pane.setContent(fieldBox);
        pane.setPrefSize(150.0, 150.0);
        setHeaderText("Please enter the parameters for the eye.");
        setTitle("Eye setup.");
        pane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        for (TextField field: new TextField[] {xField, yField, angleField})
        field.textProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                                String oldValue, String z)
            {
                if (!z.matches("\\d*")){
                    field.setText(z.replaceAll("[^\\d]", ""));
                }
                if(z.length() > 10) {
                    field.setText(z.substring(0, 8));
                }
            }
        });

        super.setResultConverter(dialogButton -> {
            if (dialogButton.equals(ButtonType.OK))
            {
                return new double[]
                    { Double.parseDouble(xField.getText()),
                      Double.parseDouble(yField.getText()),
                      Double.parseDouble(angleField.getText())};
            }
            return null;
        });
    }
}
