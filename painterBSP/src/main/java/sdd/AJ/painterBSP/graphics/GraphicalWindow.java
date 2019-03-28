package sdd.AJ.painterBSP.graphics;

import java.util.List;
import sdd.AJ.painterBSP.util.*;
import sdd.AJ.painterBSP.BSPLib.Heuristic.*;
import sdd.AJ.painterBSP.graphics.*;
import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class GraphicalWindow extends GridPane
{
    private Stage stage;
    private GraphicalCore core;
    private Illustrator planeDrawing;

    public GraphicalWindow(Stage stage)
    {
        // Creation of the pane meant to contain all components
        super();
        this.stage = stage;

        setPrefSize(800, 600);

        // Setup of column and row constraints
        ColumnConstraints ccons1 = new ColumnConstraints();
        ccons1.setHgrow(Priority.NEVER);
        ccons1.setPercentWidth(20);
        ColumnConstraints ccons2 = new ColumnConstraints();
        ccons2.setHgrow(Priority.NEVER);
        ccons2.setPercentWidth(80);
        getColumnConstraints().addAll(ccons1, ccons2); // , ccons3);

        RowConstraints rcons1 = new RowConstraints();
        rcons1.setVgrow(Priority.NEVER);
        rcons1.setPercentHeight(10);
        RowConstraints rcons2 = new RowConstraints();
        rcons2.setVgrow(Priority.NEVER);
        rcons2.setPercentHeight(70);
        RowConstraints rcons3 = new RowConstraints();
        rcons3.setVgrow(Priority.NEVER);
        rcons3.setPercentHeight(10);
        RowConstraints rcons4 = new RowConstraints();
        rcons4.setVgrow(Priority.NEVER);
        rcons4.setPercentHeight(10);
        getRowConstraints().addAll(rcons1, rcons2, rcons3, rcons4);

        // Illustration description
        Label planeLabel = new Label("   Illustration de l'ensemble de segments chargés:");
        planeLabel.setStyle("-fx-font-size:18; -fx-font-family:sans-serif;");
        planeLabel.setContentDisplay(ContentDisplay.CENTER);
        planeLabel.setTextAlignment(TextAlignment.CENTER);
        planeLabel.setAlignment(Pos.CENTER);
        add(planeLabel, 1, 0);

        // Instanciation of the class managing illustration and insertion in layout
        planeDrawing = new Illustrator(widthProperty().multiply(0.6), heightProperty().multiply(0.6));
        GridPane.setHalignment(planeDrawing, HPos.CENTER);
        GridPane.setValignment(planeDrawing, VPos.CENTER);
        add(planeDrawing, 1, 1);

        // Description of what is seen by the eye
        Label visionLabel = new Label("   La sortie de l'algorithme du peintre:");
        visionLabel.setStyle("-fx-font-size:18; -fx-font-family:sans-serif;");
        visionLabel.setContentDisplay(ContentDisplay.CENTER);
        visionLabel.setTextAlignment(TextAlignment.CENTER);
        visionLabel.setAlignment(Pos.CENTER);
        add(visionLabel, 1, 2);

        // Control layout
        VBox ctrlBox = new VBox(8);
        ctrlBox.setAlignment(Pos.CENTER);

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Sélectionnez le fichier à traiter");
        Button btnFile = new Button("Input file");
        btnFile.setOnMouseClicked(x -> {
            File file = fileChooser.showOpenDialog(stage);
            if (file != null)
            {
                try
                {
                    core.loadFile(file);
                }

                catch (IOException e)
                {
                    core.handleReadingError();
                    warn("Une erreur a été rencontrée au cours\n"+
                         "de la lecture du fichier");
                }
            }
        });

        ChoiceBox<String> heuristics = new ChoiceBox<>();
        heuristics.getItems().addAll("Dans l'ordre",
                                     "Aléatoirement",
                                     "Heuristique 1");
        heuristics
            .getSelectionModel()
            .selectedIndexProperty()
            .addListener(
                         new ChangeListener<Number>()
                         {
                             public void changed(ObservableValue ov,
                                                 Number value,
                                                 Number new_value)
                             {
                                 int new_val = new_value.intValue();
                                 if (new_val == 0)
                                     core.setHeuristic(new LinearHeuristic());
                                 else if (new_val == 1)
                                     core.activateRandom();
                                 else if (new_val == 2)
                                     core.setHeuristic(new firstHeuristic());
                             }
                         });


        Button eyeButton = new Button("Dessiner fichier chargé"); // Paramètres du point de vue");

        eyeButton.setOnMouseClicked(x -> {
                planeDrawing.clear();
                planeDrawing.draw();
        });

        Button treeButton = new Button("Construire l'arbre");
        treeButton.setOnMouseClicked(x -> {
                core.buildBSP();
        });

        ctrlBox.getChildren().addAll(btnFile,
                                     heuristics,
                                     eyeButton,
                                     treeButton);
        add(ctrlBox, 0, 1);

        for (Control c : new Control[] { btnFile, eyeButton, heuristics})
        {
            c.prefWidthProperty().bind(widthProperty().multiply(0.15));
        }

    }

    public void setCore(GraphicalCore core)
    {
        this.core = core;
    }

    public void loadSegments(int xBound, int yBound, List<Segment> segments)
    {
        planeDrawing.update(xBound, yBound, segments);
    }

    //Message does not warp automatically, newlines must be specified
    public void warn(String message)
    {
        Label label = new Label(message);
        label.setWrapText(true);
        Alert errorAlert = new Alert(AlertType.ERROR);
        errorAlert.getDialogPane().setContent(label);
        errorAlert.showAndWait();
    }

}
