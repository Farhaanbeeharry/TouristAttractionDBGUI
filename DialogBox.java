/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package touristattractiondbgui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author yadav
 */
public class DialogBox {
    
    
    public static void dialogBox(String text) {
        Stage messageWindow = new Stage();
        messageWindow.setTitle("Dialog Box");
        messageWindow.setResizable(false);

        GridPane messagePane = new GridPane();
        messagePane.setAlignment(Pos.CENTER);
        messagePane.setVgap(30);

        Label displayMessage = new Label();
        displayMessage.setText(text);
        displayMessage.setAlignment(Pos.CENTER);

        Button btnOK = new Button("OK");
        btnOK.setMinWidth(250);
        btnOK.setFocusTraversable(false);
        btnOK.setOnAction(e -> messageWindow.close());

        messagePane.add(displayMessage, 0, 0);
        messagePane.add(btnOK, 0, 1);

        Scene scene = new Scene(messagePane, 400, 100);

        messageWindow.setScene(scene);
        messageWindow.showAndWait();
    }
}
