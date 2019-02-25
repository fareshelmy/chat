package view.control.signInStagesControllers;

import com.chat.common.User;
import java.io.ByteArrayInputStream;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class AccountHBox extends HBox {

    private final Label phoneLabel;

    public AccountHBox(User user) {

        FlowPane flowPane = new FlowPane();
        ImageView personImageView = new ImageView();
        FlowPane flowPane0 = new FlowPane();
        Label nameLabel = new Label();
        phoneLabel = new Label();

        setMaxHeight(100.0);
        setMaxWidth(220.0);
        setMinHeight(60.0);
        setMinWidth(220.0);
        setPrefHeight(67.0);
        setPrefWidth(220.0);
        setStyle("-fx-background-color: #fff; -fx-background-radius: 5;");

        flowPane.setMinWidth(60.0);
        flowPane.setPrefHeight(67.0);
        flowPane.setPrefWidth(60.0);

        personImageView.setFitHeight(40.0);
        personImageView.setFitWidth(40.0);
        personImageView.setPickOnBounds(true);
        personImageView.setPreserveRatio(true);
        FlowPane.setMargin(personImageView, new Insets(10.0, 10.0, 0.0, 10.0));

        flowPane0.setPrefHeight(200.0);
        flowPane0.setPrefWidth(200.0);

        nameLabel.setPrefHeight(21.0);
        nameLabel.setPrefWidth(151.0);
        nameLabel.setFont(new Font("System Bold", 15.0));

        phoneLabel.setFont(new Font(13.0));
        HBox.setMargin(flowPane0, new Insets(10.0, 0.0, 0.0, 0.0));
        setCursor(Cursor.HAND);

        nameLabel.setText(user != null ? (user.getFirstName() + " " + user.getLastName()) : "Use Another Account");
        phoneLabel.setText(user != null ? user.getPhone() : "");
        personImageView.setImage(user != null ? new Image(new ByteArrayInputStream(user.getPic())) : new Image(getClass().getResource("/images/plus.png").toString()));
        flowPane.getChildren().add(personImageView);
        getChildren().add(flowPane);
        flowPane0.getChildren().add(nameLabel);
        flowPane0.getChildren().add(phoneLabel);
        getChildren().add(flowPane0);
    }

    public Label getPhoneLabel() {
        return phoneLabel;
    }
}
