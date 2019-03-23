package sdd.AJ.painterBSP;

import sdd.AJ.painterBSP.graphics.GraphicalWindow;
import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class TestGraphique extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        GraphicalWindow root = new GraphicalWindow(stage);
        Scene scene = new Scene(root);
        stage.setMinHeight(450.0);
        stage.setMinWidth(600.0);
        stage.setScene(scene);
        stage.setTitle("Prototype Fantastic-octo-spork");
        stage.show();
    }
}
