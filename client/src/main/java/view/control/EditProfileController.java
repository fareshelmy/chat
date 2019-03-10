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
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javax.imageio.ImageIO;

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

    private FileInputStream fileInputStream;
    private BufferedImage bufferedImage;
    private Image image;

    EditProfileController(User user, Controller controller) {
        this.user = user;
        this.controller = controller;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Image birthdayImage = new Image(getClass().getResource("/images/birthday.png").toString());
        Image countryImage = new Image(getClass().getResource("/images/country.png").toString());
        Image emailImage = new Image(getClass().getResource("/images/email.png").toString());
        Image genderImage = new Image(getClass().getResource("/images/gender.png").toString());
        Image nameImage = new Image(getClass().getResource("/images/name.png").toString());
        Image passwordImage = new Image(getClass().getResource("/images/password.png").toString());
        Image phoneImage = new Image(getClass().getResource("/images/phone.png").toString());

        Image bioImage = new Image(getClass().getResource("/images/bio.png").toString());

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
        BirthdayTextField.setText(user.getDateOfBirth().toString());
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

    public byte[] convertImageToByteArray(String imagePath) {
        byte[] bytes = null;
        try {
            File file = new File("C:\\Users\\islam\\Desktop\\li.jpg");
            fileInputStream = new FileInputStream(file);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] mybuffer = new byte[1024];

            for (int readNum; (readNum = fileInputStream.read(mybuffer)) != -1;) {
                // Writes to this byte array output stream
                byteArrayOutputStream.write(mybuffer, 0, readNum);
                System.out.println("read " + readNum + " bytes,");
            }
            bytes = byteArrayOutputStream.toByteArray();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return bytes;
    }

    public Image convertByteArrayToImage(byte[] rawImage) {
        try {
            bufferedImage = ImageIO.read(new ByteArrayInputStream(rawImage));
            image = SwingFXUtils.toFXImage(bufferedImage, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;

    }

}
