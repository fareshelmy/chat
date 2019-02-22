package view.view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class MyHBox extends HBox {

    public MyHBox() {

        Circle imageCircle = new Circle();
        Label messageLabel = new Label();

        Image image = new Image(getClass().getResource("/images/person.jpg").toString());
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        
        imageCircle.setFill(new ImagePattern(image));
        imageCircle.setRadius(15.0);
        imageCircle.setStroke(javafx.scene.paint.Color.TRANSPARENT);
        imageCircle.setStrokeType(javafx.scene.shape.StrokeType.INSIDE);
        HBox.setMargin(imageCircle, new Insets(10.0, 5.0, 0.0, 20.0));

        messageLabel.setAlignment(javafx.geometry.Pos.TOP_LEFT);
        messageLabel.setPrefHeight(30.0);
        messageLabel.setPrefWidth(85.0);
        messageLabel.setStyle("-fx-background-color: #fff; -fx-background-radius: 15;");
        messageLabel.setText("Hi Ahmed");
        HBox.setMargin(messageLabel, new Insets(10.0, 0.0, 0.0, 0.0));
        messageLabel.setPadding(new Insets(3.0));

        getChildren().add(imageCircle);
        getChildren().add(messageLabel);

    }
}
