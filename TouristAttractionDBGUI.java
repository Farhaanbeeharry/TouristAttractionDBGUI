/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package touristattractiondbgui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

/**
 *
 * @author Yadav Halkhari This is the login menu for the application where the
 * user needs to insert a username and password to have access to it
 */
public class TouristAttractionDBGUI extends Application {

    Stage window;

    public static void main(String[] args) {
        launch(args);
    }

    @Override // override the start method in th apllication class 
    public void start(Stage primaryStage) {
        // 
        String username = "Yadav";
        String password = "admin";

        window = primaryStage;
        window.setTitle("TouristAttraction");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(20);

        Text welcome = new Text(" Welcome To Mauritius Destination");
        welcome.setFont(Font.font("Tahoma", FontWeight.LIGHT, 20));
        //grid.add(welcome, 0, 0);

        Label lblUser = new Label("USER ID");
        lblUser.setMinWidth(200);
        TextField txtUser = new TextField();
        txtUser.setAlignment(Pos.CENTER_LEFT);
        txtUser.setMinWidth(200);

        HBox userIDHBox = new HBox(-60);
        userIDHBox.getChildren().addAll(lblUser, txtUser);
        userIDHBox.setAlignment(Pos.CENTER_RIGHT);
        //grid.add(userIDHBox, 0, 1);

        Label lblPassword = new Label("PASSWORD");
        lblPassword.setAlignment(Pos.CENTER_LEFT);
        lblPassword.setMinWidth(200);
        PasswordField pwBox = new PasswordField();
        pwBox.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyPressed) {
                if (keyPressed.getCode().equals(KeyCode.ENTER)) {
                    if ((txtUser.getText().matches(username)) && (pwBox.getText().matches(password))) {
                        window.close();
                        MainMenu.mainMenu();
                    } else {
                        DialogBox.dialogBox("Incorrect !");
                    }
                }
            }
        });
        pwBox.setMinWidth(200);

        HBox passwordHBox = new HBox(-60);
        passwordHBox.getChildren().addAll(lblPassword, pwBox);
        passwordHBox.setAlignment(Pos.CENTER_RIGHT);
        //grid.add(passwordHBox, 0, 2);

        Button loginBtn = new Button("Login");
        loginBtn.setMinWidth(200);
        loginBtn.setAlignment(Pos.CENTER);
        loginBtn.setOnAction(e -> {
            if ((txtUser.getText().matches(username)) && (pwBox.getText().matches(password))) {
                window.close();
                MainMenu.mainMenu();
            } else {
                DialogBox.dialogBox("Incorrect !");
            }

        });

        Button exitBtn = new Button("Exit");
        exitBtn.setAlignment(Pos.CENTER);
        exitBtn.setMinWidth(200);
        exitBtn.setOnAction(e -> {
            System.exit(0);
        });

        VBox vbox = new VBox(20);
        vbox.getChildren().addAll(welcome, userIDHBox, passwordHBox, loginBtn, exitBtn);
        vbox.setAlignment(Pos.CENTER);
        grid.add(vbox, 0, 2);
        grid.setAlignment(Pos.CENTER);

        Scene scene = new Scene(grid, 1000, 500);
        window.setScene(scene);
        window.setMaximized(true);
        window.show();
    }

}
