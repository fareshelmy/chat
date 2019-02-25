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
import controller.implementations.Controller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

/**
 *
 * @author yasmin
 */
public class EditProfileController implements Initializable {

    @FXML
    private ImageView profileImgView;

    @FXML
    private Circle userCircle;

    @FXML
    private Circle statuseCircle;

    @FXML
    private Text nameText;

    @FXML
    private ImageView phoneImgView;

    @FXML
    private TextField phoneTextField;

    @FXML
    private ImageView firstNameImgView;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private ImageView lastNameImgView;

    @FXML
    private ImageView emailImgView;

    @FXML
    private TextField emailTextField;

    @FXML
    private ImageView countryImgView;

    @FXML
    private TextField countryTextField;

    @FXML
    private ImageView BirthdayImgView;

    @FXML
    private TextField BirthdayTextField;

    @FXML
    private ImageView genderImgView;

    @FXML
    private TextField genderTextField;

    @FXML
    private ImageView passwordImgView;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private ImageView bioImgView;

    @FXML
    private TextArea bioTextArea;

    @FXML
    private Button updateButton;
///fxml/mainStageFXMLs/ChatWindowFXML.fxml
    private User user;

    private Controller controller;

    EditProfileController(User user) {
        this.user = user;
        controller = new Controller();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Image bioImage = new Image(new FileInputStream("/images/bio.png"));
            Image birthdayImage = new Image(new FileInputStream("/images/birthday.png"));
            Image countryImage = new Image(new FileInputStream("/images/country.png"));
            Image emailImage = new Image(new FileInputStream("/images/email.png"));
            Image genderImage = new Image(new FileInputStream("/images/gender.png"));
            Image nameImage = new Image(new FileInputStream("/images/name.png"));
            Image passwordImage = new Image(new FileInputStream("/images/password.png"));
            Image phoneImage = new Image(new FileInputStream("/images/phone.png"));

            bioImgView.setImage(bioImage);
            BirthdayImgView.setImage(birthdayImage);
            countryImgView.setImage(countryImage);
            emailImgView.setImage(emailImage);
            genderImgView.setImage(genderImage);
            firstNameImgView.setImage(nameImage);
            lastNameImgView.setImage(nameImage);
            passwordImgView.setImage(passwordImage);
            phoneImgView.setImage(phoneImage);

            userCircle.setFill(new ImagePattern(bioImage));
            bioTextArea.setText(user.getBio());
            BirthdayTextField.setText(user.getDateOfBirth());
            countryTextField.setText(user.getCountry());
            emailTextField.setText(user.getEmail());
            genderTextField.setText(user.getGenderEnum());
            firstNameTextField.setText(user.getFirstName());
            lastNameTextField.setText(user.getLastName());
            passwordTextField.setText(user.getPassword());
            phoneTextField.setText(user.getPhone());

            BirthdayTextField.setEditable(false);
            BirthdayTextField.setMouseTransparent(true);
            BirthdayTextField.setFocusTraversable(false);

            countryTextField.setEditable(false);
            countryTextField.setMouseTransparent(true);
            countryTextField.setFocusTraversable(false);

            genderTextField.setEditable(false);
            genderTextField.setMouseTransparent(true);
            genderTextField.setFocusTraversable(false);

            phoneTextField.setEditable(false);
            phoneTextField.setMouseTransparent(true);
            phoneTextField.setFocusTraversable(false);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(EditProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }

        updateButton.setOnAction((event) -> {
            user.setBio(bioTextArea.getText());
            user.setEmail(emailTextField.getText());
            user.setFirstName(firstNameTextField.getText());
            user.setLastName(lastNameTextField.getText());
            user.setPassword(passwordTextField.getText());

            // photo
            //Image newPicture = bioImgView.getImage();
            //user.setPic();
            controller.updateUser(user);
        });

    }

}