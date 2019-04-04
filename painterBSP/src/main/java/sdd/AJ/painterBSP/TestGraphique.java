package sdd.AJ.painterBSP;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sdd.AJ.painterBSP.graphics.GraphicalCore;

/**
 * Main class of the graphical application.
 */
public class TestGraphique extends Application
{
    @Override
    public void start(Stage stage)
    {
        GraphicalCore core = new GraphicalCore(stage);
        Scene scene = new Scene(core.getWindow());
        stage.setMinHeight(450.0);
        stage.setMinWidth(600.0);
        stage.setScene(scene);
        stage.setTitle("2D BSP and painter's algorithm");
        stage.show();
    }
}
