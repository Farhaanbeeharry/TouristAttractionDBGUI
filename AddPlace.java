package touristattractiondbgui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddPlace {

    static TextField placeNameField, priceField, openingTimeField, numberOfActivitiesField;
    static RadioButton yesBtn, noBtn;
    static ToggleGroup lunchIncluded;

    public static void addPlace() {

        Stage addPlaceStage = new Stage();

        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setVgap(25);

        Label title = new Label();
        title.setText("Add new place");

        placeNameField = new TextField();
        placeNameField.setPromptText("Place name here");
        placeNameField.setFocusTraversable(false);
        placeNameField.setMinWidth(300);
        
        priceField = new TextField();
        priceField.setPromptText("Price here");
        priceField.setFocusTraversable(false);
        priceField.setMinWidth(300);
        
        openingTimeField = new TextField();
        openingTimeField.setPromptText("Opening time here (hh:mm)");
        openingTimeField.setFocusTraversable(false);
        openingTimeField.setMinWidth(300);
        
        numberOfActivitiesField = new TextField();
        numberOfActivitiesField.setPromptText("Number of activities here");
        numberOfActivitiesField.setFocusTraversable(false);
        numberOfActivitiesField.setMinWidth(300);
        
        Text lunchTitle = new Text();
        lunchTitle.setText("Lunch included?");
        lunchIncluded = new ToggleGroup();
        yesBtn = new RadioButton("Yes");
        yesBtn.setFocusTraversable(false);
        yesBtn.setToggleGroup(lunchIncluded);
        noBtn = new RadioButton("No");
        noBtn.setFocusTraversable(false);
        noBtn.setToggleGroup(lunchIncluded);
        
        HBox radioBtns = new HBox(50);
        radioBtns.getChildren().addAll(lunchTitle, yesBtn, noBtn);
        radioBtns.setAlignment(Pos.CENTER);
        
        Button addBtn = new Button("Add place");
        addBtn.setFocusTraversable(false);
        addBtn.setMinWidth(300);
        addBtn.setOnAction(e -> {
            boolean valid = false;
            valid = checkValidData(); //check if the input data is valid
            String lunchIncluded = "";
            
            //if yes button is selected, string lunchIncluded becomes Yes
            if (yesBtn.isSelected()) {
                lunchIncluded = "Yes";
            } else if (noBtn.isSelected()) { //if no button is selected, string lunchIncluded becomes No
                lunchIncluded = "No";
            }
            
            //if data is valid
            if (valid == true) {
                //sends user input to the method dataExport to write to file
                DataExport.dataExport(placeNameField.getText(), Double.parseDouble(priceField.getText()), openingTimeField.getText(), lunchIncluded, Integer.parseInt(numberOfActivitiesField.getText()));
                addPlaceStage.close(); //close the window
                DialogBox.dialogBox("Place added successfully!"); //display success message
                MainMenu.mainMenu(); //opens main menu
            }
        });

        Button cancelBtn = new Button("Cancel");
        cancelBtn.setFocusTraversable(false);
        cancelBtn.setMinWidth(300);
        cancelBtn.setOnAction(e -> {
            addPlaceStage.close();
            MainMenu.mainMenu();
        });
        
        pane.add(title, 0, 0);
        pane.add(placeNameField, 0, 2);
        pane.add(priceField, 0, 3);
        pane.add(openingTimeField, 0, 4);
        pane.add(numberOfActivitiesField, 0, 5);
        pane.add(radioBtns, 0, 6);
        pane.add(addBtn, 0, 8);
        pane.add(cancelBtn, 0, 9);
        
        Scene scene = new Scene(pane);
        
        addPlaceStage.setTitle("Add new place");
        addPlaceStage.setMaximized(true);
        addPlaceStage.setScene(scene);
        addPlaceStage.show();

    }
    
    public static boolean checkValidData() {
        
        boolean valid = false;
        
        String placeName = placeNameField.getText();
        String price = priceField.getText();
        String openingTime = openingTimeField.getText();
        String numberOfActivities = numberOfActivitiesField.getText();
        
        if (placeName.equals("")) {
            DialogBox.dialogBox("Place name cannot be empty!");
        } else if (!placeName.matches("^[a-zA-Z ]+$")) {
            DialogBox.dialogBox("Place name cannot have numbers!");
        } else if (price.equals("")) {
            DialogBox.dialogBox("Price cannot be empty!");
        } else if (!price.matches("^[0-9]+$")) {
            DialogBox.dialogBox("Price should be numbers only!");
        } else if (openingTime.equals("")) {
           DialogBox.dialogBox("Opening time cannot be empty!");
        } else if (numberOfActivities.equals("")) {
            DialogBox.dialogBox("Number of activities cannot be empty!");
        } else if (!numberOfActivities.matches("^[0-9]+$")) {
           DialogBox.dialogBox("Number of activities should be numbers only!");
        } else if (Integer.parseInt(numberOfActivities) == 0) {
            DialogBox.dialogBox("There should be at least one activity!");
        } else if (!yesBtn.isSelected() && !noBtn.isSelected()) {
            DialogBox.dialogBox("Select Yes or No for lunch included!");
        } else {
            valid = true;
            return valid;
        }
        
        return valid; 
    }
}
