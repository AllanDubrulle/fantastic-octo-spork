package sdd.AJ.painterBSP.graphics;

import java.util.Optional;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;

public class GraphicalWindow extends GridPane
{
    private Stage stage;
    private GraphicalCore core;
    private Illustrator planeDrawing;
    private Label eyeParameters;
    private GraphicalPainter painter;

    /**
     * Class constructor.
     * @param stage the stage of the JavaFX Application the window is used in
     */
    public GraphicalWindow(Stage stage, GraphicalCore core)
    {
        /************************************************************
                           *    Initial setup    *
        *************************************************************/
        super();
        this.core = core;
        this.stage = stage;
        setPrefSize(800, 600);

        /************************************************************
         *            Setup of row and column constraints          *
        *************************************************************/
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

        /************************************************************
                 *            Illustration labels          *
        *************************************************************/
        Label planeLabel = new Label("   Illustration de l'ensemble de segments chargés:");
        planeLabel.setStyle("-fx-font-size:18; -fx-font-family:sans-serif;");
        planeLabel.setContentDisplay(ContentDisplay.CENTER);
        planeLabel.setTextAlignment(TextAlignment.CENTER);
        planeLabel.setAlignment(Pos.CENTER);
        add(planeLabel, 1, 0);

        Label visionLabel = new Label("   La sortie de l'algorithme du peintre:");
        visionLabel.setStyle("-fx-font-size:18; -fx-font-family:sans-serif;");
        visionLabel.setContentDisplay(ContentDisplay.CENTER);
        visionLabel.setTextAlignment(TextAlignment.CENTER);
        visionLabel.setAlignment(Pos.CENTER);
        add(visionLabel, 1, 2);

        /************************************************************
                 *          Painter and Illustrator          *
        *************************************************************/
        planeDrawing = new Illustrator(widthProperty().multiply(0.6), heightProperty().multiply(0.6));
        GridPane.setHalignment(planeDrawing, HPos.CENTER);
        GridPane.setValignment(planeDrawing, VPos.CENTER);
        add(planeDrawing, 1, 1);

        painter = new GraphicalPainter(widthProperty().multiply(0.6), heightProperty().multiply(0.2));
        add(painter, 1, 3);

        /************************************************************
                *          File selection button          *
        *************************************************************/
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
                    warn("Une erreur a été rencontrée au cours\n"+
                         "de la lecture du fichier.");
                }

                catch (FileFormatException e)
                {
                    warn("Le fichier soumis n'est pas conforme\n"+
                         "au format attendu.");
                }
            }
        });

        /************************************************************
                *          Heuristic selection          *
        *************************************************************/

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
                             @Override
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
                                     core.setHeuristic(new FirstHeuristic());
                             }
                         });

        /************************************************************
                *          Tree construction button          *
        *************************************************************/
        Button treeButton = new Button("Construire l'arbre");
        treeButton.setOnMouseClicked(x -> {
                core.buildBSP();
        });

        /************************************************************
              *          Eye related buttons and menus       *
        *************************************************************/
        eyeParameters = new Label("");
        eyeParameters.setWrapText(true);

        Button eyeButton = new Button("Configurer l'oeil");
        eyeButton.setOnMouseClicked(x -> {
                Optional<Double> t  = (new EyeDialog()).showAndWait();
                if (t.isPresent())
                    core.setStep(t.get());
                requestFocus();
            });

        /************************************************************
                *          Control layout options          *
        *************************************************************/
        VBox ctrlBox = new VBox(8);
        ctrlBox.setAlignment(Pos.CENTER);

        ctrlBox.getChildren().addAll(btnFile,
                                     heuristics,
                                     treeButton,
                                     eyeButton,
                                     eyeParameters);
        add(ctrlBox, 0, 1);

        for (Control c : new Control[]
            { btnFile, heuristics, treeButton, eyeButton})
        {
            c.prefWidthProperty().bind(widthProperty().multiply(0.18));
            c.setFocusTraversable(false);
            // So that the window never loses focus
        }

        /************************************************************
                     *          Key bindings              *
        *************************************************************/
        setOnKeyPressed(ke -> //ke is a KeyEvent in this case
                        {
                            switch (ke.getCode())
                            {
                                case A:
                                core.eyeRotateLeft();
                                core.display(painter);
                                break;
                                case E:
                                core.eyeRotateRight();
                                core.display(painter);
                                break;
                                case Z:
                                core.eyeUp();
                                core.display(painter);
                                break;
                                case Q:
                                core.eyeLeft();
                                core.display(painter);
                                break;
                                case S:
                                core.eyeDown();
                                core.display(painter);
                                break;
                                case D:
                                core.eyeRight();
                                core.display(painter);
                                break;
                            }
                        });
    }

    /**
     * Loads a set of segments along with the bounds of the scene so
     * that they are drawn.
     * @param xBound the bound (in absolute value) on the
     *        x-coordinates of segments in the scene
     * @param yBound the bound (in absolute value) on the
     *        y-coordinates of segments in the scene
     * @param segments the list of segments to be drawn
     */
    public void loadSegments(int xBound, int yBound, List<Segment> segments)
    {
        planeDrawing.update(xBound, yBound, segments);
        planeDrawing.clear();
        planeDrawing.draw();
    }

    /**
     * Instructs the illustrator to draw the eye on the plane representation
     * of the scene.
     * @param x     the x-coordinate of the eye
     * @param y     the y-coordinate of the eye
     * @param angle the angle the eye is facing
     */
    public void drawEye(double x, double y, double angle)
    {
        planeDrawing.drawEye(x, y, angle);
    }


    /**
     * Alerts the user with a warning message in a dialog box.
     * Do note that the message does not wrap automatically, thus newlines
     * must be explicitely specified.
     * @param message the message to be displayed
     */
    public void warn(String message)
    {
        Label label = new Label(message);
        label.setWrapText(true);
        Alert errorAlert = new Alert(AlertType.ERROR);
        errorAlert.getDialogPane().setContent(label);
        errorAlert.showAndWait();
    }

    /**
     * Displays the parameters of the eye in the UI.
     * @param x     the x-coordinate of the eye
     * @param y     the y-coordinate of the eye
     * @param angle the angle the eye is facing
     */
    public void displayEyeParameters(double x, double y, double angle)
    {
        eyeParameters.setText("Paramètres de l'oeil\n" +
                              "Abscisse: " + String.format("%.3f", x) + "\n" +
                              "Ordonnée: " + String.format("%.3f", y) + "\n" +
                              "Angle: " + String.format("%.3f", angle) + "\n"
                              );
    }

}
