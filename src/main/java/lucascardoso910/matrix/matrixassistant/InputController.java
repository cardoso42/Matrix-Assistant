package lucascardoso910.matrix.matrixassistant;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Vector;

public class InputController {
    @FXML private TextField defaultInput, minValueX, maxValueX, minValueY, maxValueY;
    @FXML private AnchorPane mainPane;

    private Matrix matrix;
    private int columns;
    private int rows;

    private void showMatrix() {
        double minX = minValueX.getLayoutX();
        double maxX = maxValueX.getLayoutX();
        double minY = minValueY.getLayoutY();
        double maxY = maxValueY.getLayoutY();
        double height = defaultInput.getPrefHeight();
        double width = defaultInput.getPrefWidth();

        defaultInput.setVisible(false);
        minValueX.setVisible(false);
        maxValueX.setVisible(false);
        minValueY.setVisible(false);
        maxValueY.setVisible(false);

        double xDistance = (maxX - minX) / (columns - 1); // maxDistanceX = 72.22222 - > 650 / 9
        double yDistance = (maxY - minY ) / (rows - 1); // maxDistanceY = 27.777 -> 250 / 9

        xDistance = Math.min(xDistance, (650.0 / 9));
        yDistance = Math.min(yDistance, (250.0 / 9));

        double initialX = ((maxX + minX) / 2) - (xDistance * ((columns - 1) / 2.0));
        double initialY = ((maxY + minY) / 2 ) - (yDistance * ((rows - 1) / 2.0));

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                TextField element = new TextField();

                element.setVisible(true);
                element.setMaxWidth(width);
                element.setMinWidth(width);
                element.setMaxHeight(height);
                element.setMinHeight(height);
                element.setLayoutX(initialX + (xDistance * j));
                element.setLayoutY(initialY + (yDistance * i));
                element.setText("0");
                MyTextField.addListener(element, "-?\\d*\\.\\d*");

                mainPane.getChildren().add(element);
            }
        }
    }

    private void getMatrixValues() {
        ObservableList<Node> children = mainPane.getChildren();
        Vector<Vector<Double>> values = new Vector<>(0);

        this.matrix = new Matrix();
        this.matrix.setMatrixSize(rows, columns);

        Vector<Double> row = new Vector<>(0);
        for (int i = 0; i < children.size(); i++) {
            Node child = children.get(i);
            if (child.isVisible()) {
                if (child instanceof TextField) {
                    Double element = Double.parseDouble(((TextField) child).getText());
                    row.add(element);
                }
            }

            if (row.size() == columns) {
                values.add(row);
                row = new Vector<>(0);
            }
        }

        this.matrix.setMatrix(values);
    }

    public void setData(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;

        showMatrix();
    }

    public void switchToOperations(ActionEvent event) throws IOException {
        getMatrixValues();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("operations.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 960, 540);
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();

        OperationsController operationsController = fxmlLoader.getController();
        operationsController.setData(this.matrix, this.columns, this.rows);

        stage.setTitle("Matrix Assistant");
        stage.setScene(scene);
        stage.show();
    }
}
