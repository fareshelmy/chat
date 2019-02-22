/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.control.eventhandlers;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import model.control.implementations.DatabaseConnector;
import model.control.implementations.ServerService;
import model.control.implementations.UserDAOImpl;

/**
 * FXML Controller class
 *
 * @author M
 */
public class ServerGUIController implements Initializable {

    @FXML
    private AnchorPane ServerUIAnchorPane;
    @FXML
    private Button StartStopButton;
    @FXML
    private Button UserRegistrationButton;
    @FXML
    private TextField UserRegistrationTextField;
    @FXML
    private TextField AnnouncementTextField;
    @FXML
    private Button AnnouncementButton;
    @FXML
    private Label PhoneNumberLabel;
    @FXML
    private Label FistNameLabel;
    @FXML
    private TextField FistNameTextField;
    @FXML
    private Label EmailLabel;
    @FXML
    private TextField LastNameTextField;
    @FXML
    private Label LastNameLabel;
    @FXML
    private TextField EmailTextField;
    @FXML
    private Label DateOfBrithLabel;
    @FXML
    private Label BiographyLabel;
    @FXML
    private Label CountryLabel;
    @FXML
    private TextField BiogrohyTextField;
    @FXML
    private Label GenderLabel;
    @FXML
    private Label OfflineUsersLebel;
    @FXML
    private Label OnlineUsersLebel;
    @FXML
    private Label OnlineUsersCounterLabel;
    @FXML
    private Label OffileUsersCounterLabel;
    @FXML
    private Label MaleUsersLabel;
    @FXML
    private Label FemaleUsersLabel;
    @FXML
    private Label MaleUsersCounterLabel;
    @FXML
    private Label FemaleUsersCounterLabel;
    @FXML
    private Button GetStatisticsButton;
    @FXML
    private ListView GetNumberOfUsersByCountryListView;
    @FXML
    private DatePicker DateOfBirthDatePicker;
    @FXML
    private ComboBox GenderComboBox;
    @FXML
    private ComboBox CountryComboBox;

    private boolean serviceStarted = false;
    private UserDAOImpl userDaoImpl;

    public ServerGUIController() {
        new ServerService(); 
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        StartStopButton.setOnAction((event) -> {

            if (!serviceStarted) {
                System.out.println("Service started.");
                //Start Service code.
                serviceStarted = true;
                StartStopButton.setText("Stop");
            } else {
                System.out.println("Service stoped.");
                //Stop Service code.
                serviceStarted = false;
                StartStopButton.setText("Start");
            }
            System.out.println("Service Started/Stoped.");

        });

        UserRegistrationButton.setOnAction((event) -> {

            System.out.println("User Registered.");

        });

        AnnouncementButton.setOnAction((event) -> {

            System.out.println("Announcement sent.");

        });

        GetStatisticsButton.setOnAction((event) -> {

            try {

                Map<String, Integer> countryStatistics = userDaoImpl.getCountryStatistics();
                Map<String, Integer> genderStatistics = userDaoImpl.getGenderStatistics();
                Integer onlineUsers = userDaoImpl.getOnlineUsers();
                Integer offlineUsers = userDaoImpl.getOfflineUsers();
                
                Platform.runLater(() -> {

                    OnlineUsersCounterLabel.setText(onlineUsers.toString());
                    OffileUsersCounterLabel.setText(offlineUsers.toString());
                    if(genderStatistics.get("MALE")==null){
                        MaleUsersCounterLabel.setText("0");
                    }
                    else{
                        MaleUsersCounterLabel.setText(genderStatistics.get("MALE").toString());
                    }
                    if(genderStatistics.get("FEMALE")==null){
                        FemaleUsersCounterLabel.setText("0");
                    }
                    else{
                        FemaleUsersCounterLabel.setText(genderStatistics.get("FEMALE").toString());
                    }
                    

                });

                ArrayList<String> countryStat = new ArrayList<>();

                Thread getCountriesThread = new Thread(() -> {
                    for (String key : countryStatistics.keySet()) {
                        countryStat.add(key + " : " + countryStatistics.get(key).toString());
                    }
                });
                getCountriesThread.start();
                try {
                    getCountriesThread.join();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                

                ObservableList<String> countryObservableList = FXCollections.observableArrayList(countryStat);
                Platform.runLater(() -> {
                    GetNumberOfUsersByCountryListView.setItems(countryObservableList);
                    GetNumberOfUsersByCountryListView.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
                        @Override
                        public ListCell<String> call(ListView<String> param) {
                            return new ListCell<String>() {
                                @Override
                                protected void updateItem(String country, boolean empty) {
                                    super.updateItem(country, empty);
                                    if (!empty) {
                                        
                                        setGraphic(new Label(country));
                                        
                                    }
                                }
                            };
                        }
                    });
                });

            } catch (RemoteException ex) {
                ex.printStackTrace();
            }

        });

    }

}
