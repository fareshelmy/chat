package view.view;

import controller.implementations.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author Abdelrhman
 */

public class ChatWindow extends AnchorPane implements ChatWindowInterface {

    protected final Pane pane;
    protected final TextArea textArea;
    protected final TextField textField;
    protected final Button button;
    private Controller controler;

    public ChatWindow(Controller controler) {
        this.controler = controler;

        pane = new Pane();
        textArea = new TextArea();
        textField = new TextField();
        button = new Button();

        setId("AnchorPane");
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        pane.setPrefHeight(413.0);
        pane.setPrefWidth(600.0);

        textArea.setLayoutX(-2.0);
        textArea.setPrefHeight(361.0);
        textArea.setPrefWidth(600.0);
        textArea.setEditable(false);

        textField.setLayoutX(14.0);
        textField.setLayoutY(361.0);
        textField.setPrefHeight(32.0);
        textField.setPrefWidth(420.0);
        
        textField.setOnKeyPressed((event)
                -> {
//            if (event.getCode().equals(KeyCode.ENTER)) {
//                controler.sendMessageToServer(textField.getText());
//            }

        });

        button.setLayoutX(463.0);
        button.setLayoutY(361.0);
        button.setMnemonicParsing(false);
        button.setPrefHeight(32.0);
        button.setPrefWidth(135.0);
        button.setText("Send");

        pane.getChildren().add(textArea);
        pane.getChildren().add(textField);
        pane.getChildren().add(button);
        getChildren().add(pane);

        button.setOnAction((event) -> {

//            controler.sendMessageToServer(textField.getText());
//            this.textField.clear();

        });

    }

    @Override
    public void display(String receivedMessage) {
        textArea.appendText(receivedMessage + "\n");

    }
}
