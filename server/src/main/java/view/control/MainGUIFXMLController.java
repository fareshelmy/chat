/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.control;

import com.chat.common.GenderEnum;
import com.chat.common.RegisteredByEnum;
import com.chat.common.StatusEnum;
import com.chat.common.User;
import com.chat.utils.FieldValidationUtil;
import controller.implementations.Controller;
import java.net.URL;
import java.time.LocalDate;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Border;
import javafx.stage.Stage;

/**
 *
 * @author FARES-LAP
 */
public class MainGUIFXMLController implements Initializable {

    @FXML
    private PieChart statusPieChart;
    @FXML
    private PieChart genderPieChart;
    @FXML
    private ImageView worldImageView;
    @FXML
    private Label egyptLabel, KSALabel, UAELabel, USALabel, errorLabel, egyptNumberLabel, KSANumberLabel, UAENumberLabel, USANumberLabel, otherNumberLabel, messageLabel;
    @FXML
    private Tab dashboardTab;
    @FXML
    private Tab addUserTab;
    @FXML
    private Tab broadcastTab;
    @FXML
    private TabPane tabPane;
    @FXML
    private Button saveButton, broadcastButton, serviceButton, refreshButton;
    @FXML
    private TextField phoneNumberTextField, firstNameTextField, lastNameTextField, emailTextField;
    @FXML
    private TextArea broadcastTextArea;
    @FXML
    private ComboBox<String> genderComboBox, countryComboBox;
    @FXML
    private DatePicker birthDatePicker;

    private final Controller controller;

    MainGUIFXMLController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refreshStatistics();

        countryComboBox.getItems().removeAll(countryComboBox.getItems());
        countryComboBox.getItems().addAll("Egypt", "KSA", "UAE", "USA", "Other");
        countryComboBox.getSelectionModel().select("Egypt");

        genderComboBox.getItems().removeAll(genderComboBox.getItems());
        genderComboBox.getItems().addAll("Male", "Female");
        genderComboBox.getSelectionModel().select("Male");

        saveButton.setOnAction((event) -> {
            saveUser();
        });

        broadcastTextArea.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                broadcastMessage(broadcastTextArea.getText());
            }
        });
        broadcastButton.setOnAction((event) -> {
            broadcastMessage(broadcastTextArea.getText());
        });

        serviceButton.setOnAction((event) -> {
            if (serviceButton.getText().equals("Start Service")) {
                controller.startService();
                refreshStatistics();
                serviceButton.setText("Stop Service");
            } else {
                controller.stopService();
                serviceButton.setText("Start Service");
            }
        });

        refreshButton.setOnAction((event) -> {
            refreshStatistics();
        });
    }

    private void createPieChart(String firstPieName,
            int firstPieValue,
            String secondPieName,
            int secondPieValue,
            PieChart pieChart,
            String firstSliceColor,
            String secondSliceColor,
            int dataSum) {
        pieChart.getData().clear();
        PieChart.Data firstPie = new PieChart.Data(firstPieName, firstPieValue);
        PieChart.Data secondPie = new PieChart.Data(secondPieName, secondPieValue);
        pieChart.getData().add(firstPie);
        pieChart.getData().add(secondPie);
        pieChart.setLegendVisible(false);
        firstPie.getNode().setStyle("-fx-pie-color : " + firstSliceColor);
        secondPie.getNode().setStyle("-fx-pie-color : " + secondSliceColor);
        pieChart.setBorder(Border.EMPTY);
        pieChart.setStartAngle(90);

        for (PieChart.Data data : pieChart.getData()) {
            Node slice = data.getNode();
            double percent = (data.getPieValue() / dataSum) * 100;
            String tip = data.getName() + " = " + (int) data.getPieValue() + " (" + String.format("%.0f", percent) + "%)";
            Tooltip.install(slice, new Tooltip(tip));
        }
    }

    private void refreshStatistics() {
        int onlineUsersCount = controller.getOnlineUsersCount();
        int offlineUsersCount = controller.getOfflineUsersCount();
        if (onlineUsersCount > 0 || offlineUsersCount > 0) {
            createPieChart("Online Users", onlineUsersCount, "Offline Users", offlineUsersCount, statusPieChart, "#c3c3c3", "#2f4f4f", onlineUsersCount + offlineUsersCount);
        }

        Map<String, Integer> genderStatistics = controller.getGenderStatistics();
        if (genderStatistics != null) {
            Integer maleCount = genderStatistics.get(GenderEnum.MALE.getGender(GenderEnum.MALE));
            Integer femaleCount = genderStatistics.get(GenderEnum.FEMALE.getGender(GenderEnum.FEMALE));
            createPieChart("Male Users", maleCount, "Female Users", femaleCount, genderPieChart, "#2f4f4f", "#800000", maleCount + femaleCount);
        }
        worldImageView.setImage(new Image(getClass().getResource("/images/world.png").toString()));

        Map<String, Integer> countryStatistics = controller.getCountryStatistics();
        if (countryStatistics != null) {
            Integer egyptCount = countryStatistics.get("Egypt");
            egyptNumberLabel.setText(egyptCount == null ? String.valueOf(0) : egyptCount.toString());
            ImageView egyptImageView = new ImageView(new Image(getClass().getResource("/images/egypt.png").toString()));
            egyptImageView.setFitWidth(13);
            egyptImageView.setFitHeight(10);
            egyptLabel.setGraphic(egyptImageView);

            Integer KSACount = countryStatistics.get("KSA");
            KSANumberLabel.setText(KSACount == null ? String.valueOf(0) : KSACount.toString());

            ImageView KSAImageView = new ImageView(new Image(getClass().getResource("/images/ksa.png").toString()));
            KSAImageView.setFitWidth(13);
            KSAImageView.setFitHeight(10);
            KSALabel.setGraphic(KSAImageView);

            Integer UAECount = countryStatistics.get("UAE");
            UAENumberLabel.setText(UAECount == null ? String.valueOf(0) : UAECount.toString());

            ImageView UAEImageView = new ImageView(new Image(getClass().getResource("/images/uae.jpg").toString()));
            UAEImageView.setFitWidth(13);
            UAEImageView.setFitHeight(10);
            UAELabel.setGraphic(UAEImageView);

            Integer USACount = countryStatistics.get("USA");
            USANumberLabel.setText(USACount == null ? String.valueOf(0) : USACount.toString());

            ImageView USAImageView = new ImageView(new Image(getClass().getResource("/images/usa.png").toString()));
            USAImageView.setFitWidth(13);
            USAImageView.setFitHeight(10);
            USALabel.setGraphic(USAImageView);

            Integer otherCountriesCount = countryStatistics.get("Other");
            otherNumberLabel.setText(otherCountriesCount == null ? String.valueOf(0) : otherCountriesCount.toString());
        }
    }

    private void saveUser() {
        String phoneNumber = phoneNumberTextField.getText();
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String email = emailTextField.getText();
        String country = countryComboBox.getValue();
        String gender = genderComboBox.getValue();
        LocalDate birthDate = birthDatePicker.getValue();
        if (FieldValidationUtil.validatePhone(phoneNumber) && firstName != null && lastName != null && FieldValidationUtil.validateEmail(email) && birthDate != null) {
            if (controller.validateUser(phoneNumber) == null) {
                User user = new User();
                user.setPhone(phoneNumber);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setEmail(email);
                user.setCountry(country);
                user.setGenderEnum(GenderEnum.valueOf(gender.toUpperCase()));
                user.setDateOfBirth(birthDate.toString());
                user.setStatusEnum(StatusEnum.OFFLINE);
                user.setRegisteredBy(RegisteredByEnum.ADMIN);
                controller.persistUser(user);
                clearFields();
                errorLabel.setVisible(false);
            } else {
                errorLabel.setVisible(true);
                errorLabel.setText("User already registered!");
            }
        } else {
            errorLabel.setVisible(true);
            errorLabel.setText("Data missing or invalid");
        }
    }

    private void broadcastMessage(String message) {
        if (!message.equals("")) {
            broadcastTextArea.setText("");
            controller.broadcast(message);
        }
    }

    private void clearFields() {
        phoneNumberTextField.clear();
        firstNameTextField.clear();
        lastNameTextField.clear();
        emailTextField.clear();
        errorLabel.setVisible(false);
    }

}
