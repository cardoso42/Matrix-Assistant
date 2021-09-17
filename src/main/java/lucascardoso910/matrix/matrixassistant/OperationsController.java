package lucascardoso910.matrix.matrixassistant;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import io.github.mrrefactoring.Fraction;
import java.util.Optional;

public class OperationsController {
    // Variables in the operations.fxml
    @FXML Label minValueX, maxValueX, minValueY, maxValueY, defaultLabel;
    @FXML Button multRowBtn, multColBtn, addMultRowBtn, addMultColBtn, switchRowBtn, switchColBtn;
    @FXML ListView<String> history;
    @FXML Pane mainPane;

    private Matrix matrix;
    private int source;
    private double factor;
    private int target;
    private int columns;
    private int rows;

    private void updateLabels() {
        ObservableList<Node> children = mainPane.getChildren();
        int row = 0, column = 0;

        for (int i = 0; i < children.size(); i++) {
            Node child = children.get(i);

            if (child instanceof Label) {
                Double value = matrix.getMatrix().get(row).get(column);
                Fraction fraction = new Fraction(value);

                ((Label) child).setText(fraction.toFraction());
                column++;

                if (column == columns) {
                    column = 0;
                    row++;
                }
            }
        }
    }

    private void showMatrix() {
        double minX = minValueX.getLayoutX();
        double maxX = maxValueX.getLayoutX();
        double minY = minValueY.getLayoutY();
        double maxY = maxValueY.getLayoutY();
        double height = defaultLabel.getPrefHeight();
        double width = defaultLabel.getPrefWidth();

        defaultLabel.setVisible(false);
        minValueX.setVisible(false);
        maxValueX.setVisible(false);
        minValueY.setVisible(false);
        maxValueY.setVisible(false);

        double xDistance = (maxX - minX) / (columns - 1); // maxDistanceX = 40
        double yDistance = (maxY - minY ) / (rows - 1); // maxDistanceY = 40

        xDistance = Math.min(xDistance, 40);
        yDistance = Math.min(yDistance, 40);

        double initialX = ((maxX + minX) / 2) - (xDistance * ((columns - 1) / 2.0));
        double initialY = ((maxY + minY) / 2 ) - (yDistance * ((rows - 1) / 2.0));

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Label element = new Label();
                Fraction value = new Fraction(this.matrix.getMatrix().get(i).get(j));

                element.setVisible(true);
                element.setMaxWidth(width);
                element.setMinWidth(width);
                element.setMaxHeight(height);
                element.setMinHeight(height);
                element.setLayoutX(initialX + (xDistance * j));
                element.setLayoutY(initialY + (yDistance * i));
                element.setText(value.toFraction());

                mainPane.getChildren().add(element);
            }
        }
    }

    public void setData(Matrix matrix, int columns, int rows) {
        this.matrix = matrix;
        this.columns = columns;
        this.rows = rows;

        showMatrix();
    }

    private double getInfo(String request) throws Exception {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText(request);

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            return Double.parseDouble(result.get());
        }

        throw new Exception("User cancelled!");
    }

    public void multRow() {
        try {
            target = (int) getInfo("Insert the row you want to multiply:");
            factor = getInfo("Insert the multiplicative factor:");
        } catch (Exception e) {
            return;
        }

        matrix.multiplyRow(target - 1, factor);

        updateLabels();
        history.getItems().add(0, "Multiplied row " + target + " by a factor of " + factor);
    }

    public void divRow() {
        try {
            target = (int) getInfo("Insert the row you want to divide:");
            factor = getInfo("Insert the division factor:");
        } catch (Exception e) {
            return;
        }

        matrix.divideRow(target -1 , factor);

        updateLabels();
        history.getItems().add(0, "Divided row " + target + " by a factor of " + factor);
    }

    public void addMultRow() {
        try {
            source = (int) getInfo("Insert the row you want to multiply:");
            target = (int) getInfo("Insert the target of the operation:");
            factor = getInfo("Insert the multiplicative factor:");
        } catch (Exception e) {
            return;
        }

        matrix.addByMultipleOfRow((source - 1), (target - 1), factor);

        updateLabels();
        history.getItems().add(0, "Multiplied row " + source + " by a factor of " + factor + " and added its values to row " + target);
    }

    public void addDivRow() {
        try {
            source = (int) getInfo("Insert the row you want to divide:");
            target = (int) getInfo("insert the target of the operation:");
            factor = getInfo("Insert the multiplicative factor:");
        } catch (Exception e) {
            return;
        }

        matrix.addByDivisionOfRow((source - 1), (target - 1), factor);

        updateLabels();
        history.getItems().add(0, "Divided row " + source + " by a factor of " + factor + " and added its values to row " + target);
    }

    public void switchRow() {
        try {
            source = (int) getInfo("Insert one of the rows you want to switch:");
            target = (int) getInfo("Insert the other row:");
        } catch (Exception e) {
            return;
        }

        matrix.switchRows(source - 1, target - 1);

        updateLabels();
        history.getItems().add(0, "Switched rows " + target + " and " + source);
    }
}
