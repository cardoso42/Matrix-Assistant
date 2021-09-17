package lucascardoso910.matrix.matrixassistant;

import java.util.Vector;

public class Matrix {
    private Vector<Vector<Double>> matrix = new Vector<Vector<Double>>(0);

    private Vector<Double> getMultipleOfRow(int row, double factor) {
        Vector<Double> chosenRow = (Vector<Double>) matrix.get(row).clone();

        for (int i = 0; i < chosenRow.size(); i++) {
            Double element = chosenRow.get(i);
            chosenRow.set(i, element * factor);
        }

        return chosenRow;
    }

    public void multiplyRow(int row, double factor) {
        Vector<Double> newRow = getMultipleOfRow(row, factor);
        matrix.set(row, newRow);
    }

    public void divideRow(int row, double factor) {
        Vector<Double> newRow = getMultipleOfRow(row, (1 / factor));
        matrix.set(row, newRow);
    }

    public void addByMultipleOfRow(int objectRow, int targetRow, double factor) {
        Vector<Double> multipleOfRow = getMultipleOfRow(objectRow, factor);
        Vector<Double> row = matrix.get(targetRow);

        for (int i = 0; i < row.size(); i++) {
            row.set(i, row.get(i) + multipleOfRow.get(i));
            multipleOfRow.set(i, multipleOfRow.get(i) * (1/factor));
        }

        this.matrix.set(targetRow, row);
    }

    public void addByDivisionOfRow(int objectRow, int targetRow, double factor) {
        Vector<Double> divisionOfRow = getMultipleOfRow(objectRow, (1 / factor));
        Vector<Double> row = matrix.get(targetRow);

        for (int i = 0; i < row.size(); i++) {
            row.set(i, row.get(i) + divisionOfRow.get(i));
            divisionOfRow.set(i, divisionOfRow.get(i) * (factor));
        }

        this.matrix.set(targetRow, row);
    }

    public void switchRows(int rowIndex1, int rowIndex2) {
        Vector<Double> row1 = matrix.get(rowIndex1);
        Vector<Double> row2 = matrix.get(rowIndex2);

        this.matrix.set(rowIndex1, row2);
        this.matrix.set(rowIndex2, row1);
    }

    public void setMatrixSize(int rows, int columns) {
        this.matrix = new Vector<>(0);

        for (int i = 0; i < rows; i++) {
            this.matrix.add(new Vector<Double>(columns));
        }
    }

    public void setMatrix(Vector<Vector<Double>> matrixInfo) {
        for (int i = 0; i < matrixInfo.size(); i++) {
            this.matrix.set(i, matrixInfo.get(i));
        }
    }

    public Vector<Vector<Double>> getMatrix() {
        return this.matrix;
    }

    public int getRowsNumber() {
        return this.matrix.size();
    }

    public int getColumnsNumber() {
        return this.matrix.get(0).size();
    }
}
