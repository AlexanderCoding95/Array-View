

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class main extends Application {

    private final List<Integer> array = new ArrayList<>();
    private final ListView<Integer> listView = new ListView<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        populateArray(50);
        updateListView();

        Button showArrayButton = new Button("Show Array");
        Button additionButton = new Button("Add Numbers");
        Button removeButton = new Button("Remove Number");
        Button sortingButton = new Button("Sort Array");
        Button arraysizeButton = new Button("Array Size");
        Button searchnumberButton = new Button("Search Number");

        showArrayButton.setOnAction(e -> updateListView());
        additionButton.setOnAction(e -> addNumbers());
        removeButton.setOnAction(e -> deleteNumber());
        sortingButton.setOnAction(e -> sortArray());
        arraysizeButton.setOnAction(e -> displayArraySize());
        searchnumberButton.setOnAction(e -> searchNumber());


        VBox layout = new VBox(10);
        layout.getChildren().addAll(showArrayButton, additionButton, searchnumberButton, removeButton, sortingButton, arraysizeButton, listView);


        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Array GUI");
        primaryStage.show();
    }

    private void populateArray(int size) {
        array.clear();
        for (int i = 0; i < size; i++) {
            array.add((int) (Math.random() * 100));
        }
    }

    private void updateListView() {
        listView.getItems().setAll(array);
    }

    private void addNumbers() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText("Enter numbers to add:");
        dialog.showAndWait().ifPresent(input -> {
            String[] numbers = input.split(",");
            for (String num : numbers) {
                try {
                    int value = Integer.parseInt(num.trim());
                    array.add(value);
                } catch (NumberFormatException e) {

                }
            }
            updateListView();
        });
    }

    private void deleteNumber() {
        int selectedIndex = listView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            array.remove(selectedIndex);
            updateListView();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("No item selected");
            alert.showAndWait();
        }
    }

    private void sortArray() {
        Collections.sort(array);
        updateListView();
    }

    private void displayArraySize() {
        int size = array.size();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Array Size");
        alert.setContentText("Size of array: " + size);
        alert.showAndWait();
    }

    private void searchNumber() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText("Enter number to search:");
        dialog.showAndWait().ifPresent(input -> {
            try {
                int number = Integer.parseInt(input);
                for (int i = 0; i < array.size(); i++) {
                    if (array.get(i) == number) {
                        listView.getSelectionModel().select(i);
                    }
                }
            } catch (NumberFormatException e) {

            }
        });
    }
}
