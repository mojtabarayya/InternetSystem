package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.*;
import models.SubscriberModel;

public class SubscriberListController {

    @FXML private TextField searchField;
    @FXML private TableView<SubscriberModel> subscriberTable;
    @FXML private TableColumn<SubscriberModel, String> nameCol, phoneCol, packageCol, statusCol;

    @FXML
    public void initialize() {
        nameCol.setCellValueFactory(data -> data.getValue().nameProperty());
        phoneCol.setCellValueFactory(data -> data.getValue().phoneProperty());
        packageCol.setCellValueFactory(data -> data.getValue().packageProperty());
        statusCol.setCellValueFactory(data -> data.getValue().statusProperty());

        loadAllSubscribers();
    }

    private void loadAllSubscribers() {
        ObservableList<SubscriberModel> list = SubscriberModel.getAllSubscribers();
        subscriberTable.setItems(list);
    }

    @FXML
    private void handleSearch() {
        String keyword = searchField.getText();
        ObservableList<SubscriberModel> results = SubscriberModel.searchSubscribers(keyword);
        subscriberTable.setItems(results);
    }
}