package sdd.AJ.painterBSP;

import sdd.AJ.painterBSP.graphics.GraphicalWindow;
import sdd.AJ.painterBSP.graphics.GraphicalCore;
import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class TestGraphique extends Application
{
    @Override
    public void start(Stage stage)
    {
        GraphicalWindow root = new GraphicalWindow(stage);
        GraphicalCore core = new GraphicalCore(root);
        root.setCore(core);
        Scene scene = new Scene(root);
        stage.setMinHeight(450.0);
        stage.setMinWidth(600.0);
        stage.setScene(scene);
        stage.setTitle("2D BSP and painter's algorithm");
        stage.show();
        root.requestFocus();
    }
}
