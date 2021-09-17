package lucascardoso910.matrix.matrixassistant;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MatrixApplication extends Application {
    Matrix matrix = new Matrix();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MatrixApplication.class.getResource("initial.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 960 , 540);

        InitialController initialController = fxmlLoader.getController();
        initialController.setFieldsToAcceptOnlyNumbers();

        stage.setTitle("Matrix Assistant");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}