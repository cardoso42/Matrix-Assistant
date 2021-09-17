package lucascardoso910.matrix.matrixassistant;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class InitialController {
    // Variables in initial.fxml
    @FXML private Pane initialPane;
    @FXML private Label welcomeText, instructionsRows, instructionsColumns;
    @FXML private TextField inputRows, inputColumns;
    @FXML private Button readyBtn;

    private int rows;
    private int columns;

    private void getMatrixSize(ActionEvent event) throws IOException{
        rows = Integer.parseInt(inputRows.getText());
        columns = Integer.parseInt(inputColumns.getText());

        if (rows > 10 || columns > 10) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid input");
            alert.setContentText("Both numbers of rows and columns must be less than or equal to 10");
            alert.show();

            FXMLLoader fxmlLoader = new FXMLLoader(MatrixApplication.class.getResource("initial.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 960 , 540);
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.showAndWait();
        }
    }

    public void switchToInput(ActionEvent event) throws IOException {
        getMatrixSize(event);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("input-matrix.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 960 , 540);
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();

        InputController inputController = fxmlLoader.getController();
        inputController.setData(rows, columns);

        stage.setTitle("Matrix Assistant");
        stage.setScene(scene);
        stage.show();
    }

    public void setFieldsToAcceptOnlyNumbers() {
        MyTextField.addListener(inputRows, "\\d*");
        MyTextField.addListener(inputColumns, "\\d*");
    }
}