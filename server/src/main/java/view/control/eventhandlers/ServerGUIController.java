/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view.control.eventhandlers;

import com.chat.common.UserDAO;
import com.chat.common.GenderEnum;
import com.chat.common.StatusEnum;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

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
    private ListView<?> GetNumberOfUsersByCountryListView;
    @FXML
    private DatePicker DateOfBirthDatePicker;
    @FXML
    private ComboBox<?> GenderComboBox;
    @FXML
    private ComboBox<?> CountryComboBox;

    private boolean serviceStarted = false;
    private UserDAO userDAO;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        StartStopButton.setOnAction((event)->{
            
            if(!serviceStarted){
                //Start Service code.
                serviceStarted = true;
                StartStopButton.setText("Stop");
            }
            else{
                //Stop Service code.
                serviceStarted = false;
                StartStopButton.setText("Start");
            }
            System.out.println("Service Started/Stoped.");
        
        });
        
        UserRegistrationButton.setOnAction((event)->{
            
            System.out.println("User Registered.");
        
        });
        
        AnnouncementButton.setOnAction((event)->{
            
            System.out.println("Announcement sent.");
            
        });
        
        GetStatisticsButton.setOnAction((event)->{
            
            try {
                
                System.out.println("Get Statistics.");
                Map<String ,Integer> countryStatistics = userDAO.getCountryStatistics();
                Map<String ,Integer> genderStatistics = userDAO.getGenderStatistics();
                Integer onlineUsers = userDAO.getOnlineUsers();
                Integer offlineUsers = userDAO.getOfflineUsers();
                OnlineUsersCounterLabel.setText(onlineUsers.toString());
                OffileUsersCounterLabel.setText(offlineUsers.toString());
                MaleUsersCounterLabel.setText(genderStatistics.get(GenderEnum.MALE).toString());
                FemaleUsersCounterLabel.setText(genderStatistics.get(GenderEnum.FEMALE).toString());
                
                
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
            
        });
        
    }    
    
}
