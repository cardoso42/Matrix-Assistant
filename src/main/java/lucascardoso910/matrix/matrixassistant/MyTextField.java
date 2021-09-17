package lucascardoso910.matrix.matrixassistant;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class MyTextField extends TextField {
    public static void addListener(TextField textField, String regex) {
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches(regex)) {
                    textField.setText(newValue.replaceAll("[^" + regex + "]", ""));
                }
            }
        });
    }
}
