/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package touristattractiondbgui;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.*;
/**
 *
 * @author yadav
 */
public class Delete {
    
    static Stage removeWindow, confirmWindow;
    static TextField placeNameField, priceField, openingTimeField, numberOfActivitiesField;
   
    public static void delete() {
       
        Stage deleteStage = new Stage();
        deleteStage.setMaximized(true);

        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setVgap(25);

        Label title = new Label();
        title.setText("Delete an existing place");
        
        TextField deleteField = new TextField();
        deleteField.setPromptText("Input place name");
        deleteField.setFocusTraversable(false);
        
        CheckBox checkBox = new CheckBox("Agree to delete");
        checkBox.setIndeterminate(true);
        checkBox.setFocusTraversable(false);
        
        Button deleteBtn = new Button("Delete");
        deleteBtn.setMinWidth(300);
        deleteBtn.setOnAction(e -> {
            if (deleteField.getText().matches("")) {
                DialogBox.dialogBox("Delete Field cannot be empty!");
            } else if (!deleteField.getText().matches("^[a-zA-Z ]+$")) {
                DialogBox.dialogBox("Delete field can only contain alphabets!");
            } else if (!checkBox.isSelected()) {
                DialogBox.dialogBox("Agree to delete!");
            } else if (checkExisting(deleteField.getText()) == 1) {
                DialogBox.dialogBox("Place name does not exist");
            } else {
                delete(deleteField.getText());
                deleteStage.close();
                DialogBox.dialogBox("Delete successful!");
            }
        });
        
        Button backBtn = new Button("Back");
        backBtn.setMinWidth(300);
        backBtn.setOnAction(e -> {
            deleteStage.close();
        });
       
       pane.add(title, 0, 0);
       pane.add(deleteField, 0, 1);
       pane.add(checkBox, 0, 2);
       pane.add(deleteBtn, 0, 3);
       pane.add(backBtn, 0, 4);
       
       Scene scene = new Scene(pane);
       
       deleteStage.setScene(scene);
       deleteStage.show();

        
        
    }
//It will check if the placename is existed or haveduplicate or even if it's not existed 
public static int checkExisting(String checkCriteria) {
    
    Places[] places = DataImport.dataImport();
    
    for (int i = 0; i < places.length; i++) {
        if (places[i].getPlaceName().equalsIgnoreCase(checkCriteria)) {
            return 0;
        }
    }
    
    return 1;
}

public static void delete(String deleteCriteria) {
    
    Places[] places = DataImport.dataImport();
    Places[] updatedPlaces = new Places[places.length - 1];
    
    for (int x = 0, k = 0; x < places.length; x++) {
        
        if (places[x].getPlaceName().equalsIgnoreCase(deleteCriteria)) {
            continue;
        }
        
        updatedPlaces[k++] = places[x];
        
    }
    
    DataExport.updateFile(updatedPlaces);
    
}
    
}
