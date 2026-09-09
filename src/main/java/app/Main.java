package app;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.List;

public class Main extends Application {

    private final TravelTypeDAO travelTypeDAO = new TravelTypeDAO();
    private final TravelRecordDAO travelRecordDAO = new TravelRecordDAO();

    private TextField speedField;
    private TextField distanceField;
    private ComboBox<TravelType> typeComboBox;
    private Label resultLabel;
    private TableView<TravelRecord> tableView;

    @Override
    public void start(Stage stage) {
        stage.setTitle("Travel Time Calculator");

        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);
        form.setPadding(new Insets(15));

        speedField = new TextField();
        distanceField = new TextField();
        typeComboBox = new ComboBox<>();
        loadTravelTypes();

        Button calcButton = new Button("Calculate & Save");
        resultLabel = new Label();

        form.add(new Label("Speed:"), 0, 0);
        form.add(speedField, 1, 0);
        form.add(new Label("Distance:"), 0, 1);
        form.add(distanceField, 1, 1);
        form.add(new Label("Travel Type:"), 0, 2);
        form.add(typeComboBox, 1, 2);
        form.add(calcButton, 1, 3);
        form.add(resultLabel, 1, 4);

        tableView = buildTableView();
        loadRecords();

        calcButton.setOnAction(e -> handleCalculateAndSave());

        VBox root = new VBox(15, form, new Label("Saved Records:"), tableView);
        root.setPadding(new Insets(15));
        root.setAlignment(Pos.TOP_LEFT);

        stage.setScene(new Scene(root, 500, 500));
        stage.show();
    }

    private void loadTravelTypes() {
        try {
            List<TravelType> types = travelTypeDAO.getAllTypes();
            typeComboBox.getItems().addAll(types);
            if (!types.isEmpty()) {
                typeComboBox.getSelectionModel().selectFirst();
            }
        } catch (SQLException e) {
            showError("Failed to load travel types: " + e.getMessage());
        }
    }

    private void handleCalculateAndSave() {
        try {
            double speed = Double.parseDouble(speedField.getText());
            double distance = Double.parseDouble(distanceField.getText());
            TravelType selectedType = typeComboBox.getValue();

            if (selectedType == null) {
                showError("Please select a travel type.");
                return;
            }

            double time = TravelCalculator.timeCal(speed, distance);
            resultLabel.setText(String.format("Time: %.2f", time));

            TravelRecord record = new TravelRecord(speed, distance, time, selectedType.getId());
            travelRecordDAO.save(record);

            loadRecords();
            speedField.clear();
            distanceField.clear();

        } catch (NumberFormatException ex) {
            showError("Speed and distance must be numeric.");
        } catch (IllegalArgumentException ex) {
            showError(ex.getMessage());
        } catch (SQLException ex) {
            showError("Database error: " + ex.getMessage());
        }
    }

    private TableView<TravelRecord> buildTableView() {
        TableView<TravelRecord> table = new TableView<>();

        TableColumn<TravelRecord, Number> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getId()));

        TableColumn<TravelRecord, Number> speedCol = new TableColumn<>("Speed");
        speedCol.setCellValueFactory(data -> new javafx.beans.property.SimpleDoubleProperty(data.getValue().getSpeed()));

        TableColumn<TravelRecord, Number> distCol = new TableColumn<>("Distance");
        distCol.setCellValueFactory(data -> new javafx.beans.property.SimpleDoubleProperty(data.getValue().getDistance()));

        TableColumn<TravelRecord, Number> timeCol = new TableColumn<>("Time");
        timeCol.setCellValueFactory(data -> new javafx.beans.property.SimpleDoubleProperty(data.getValue().getTimeTaken()));

        table.getColumns().addAll(idCol, speedCol, distCol, timeCol);
        return table;
    }

    private void loadRecords() {
        try {
            List<TravelRecord> records = travelRecordDAO.getAllRecords();
            tableView.getItems().setAll(records);
        } catch (SQLException e) {
            showError("Failed to load records: " + e.getMessage());
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}