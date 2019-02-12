package view.view;

import com.chat.common.GenderEnum;
import com.chat.common.RegisteredByEnum;
import com.chat.common.StatusEnum;
import com.chat.common.User;
import controller.implementations.Controller;
import java.lang.*;
import java.util.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class MainView extends AnchorPane {

    protected final Button button;
    protected final Label label;

    public MainView(Controller controller) {

        button = new Button();
        label = new Label();

        setId("AnchorPane");
        setPrefHeight(200);
        setPrefWidth(320);

        button.setLayoutX(126);
        button.setLayoutY(90);
        button.setText("Click Me!");

        label.setLayoutX(126);
        label.setLayoutY(120);
        label.setMinHeight(16);
        label.setMinWidth(69);

        getChildren().add(button);
        getChildren().add(label);
        
        button.setOnAction((event) -> {
            controller.signInUser(new User("2194984", "hamada", "medhat", "wiuuh", "wiudeh@UWddh", null, GenderEnum.MALE, "egyp", "1993-03-11", "qwiuhiquwhqwd", StatusEnum.AVAILABLE, RegisteredByEnum.USER));
        });

    }
}
