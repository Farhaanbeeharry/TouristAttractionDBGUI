/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package touristattractiondbgui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author yadav
 */
public class Search {
    
    static Stage searchStage, resultStage;
    static TextField inputPlaceName;
    static TableView resultTable;
    
    public static void searchPlaceName() {  //
        
        searchStage = new Stage();

        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setVgap(25);

        Label title = new Label();
        title.setText("Search for place");
        
        //input place name to search
        inputPlaceName = new TextField();
        inputPlaceName.setPromptText("Input place name");
        inputPlaceName.setFocusTraversable(false);

        //button to start search
        Button searchBtn = new Button("Search");
        searchBtn.setMinWidth(300);
        searchBtn.setOnAction(e -> {
            checkData(inputPlaceName.getText());
        });
        
        //button to cancel search
        Button cancelBtn = new Button("Cancel");
        cancelBtn.setMinWidth(300);
        cancelBtn.setOnAction(e -> {
            searchStage.close();
        });
        
        pane.add(title, 0, 0);
        pane.add(inputPlaceName, 0, 1);
        pane.add(searchBtn, 0, 2);
        pane.add(cancelBtn, 0, 3);
        
        Scene scene = new Scene(pane);
        
        searchStage.setScene(scene);
        searchStage.setMaximized(true);
        searchStage.show();
    }
    
    
    public static void checkData(String criteria) {
        
        if (criteria.matches("")) {
            DialogBox.dialogBox("Place name cannot be empty!");
        } else if (!criteria.matches("^[a-zA-Z ]+$")) {
            DialogBox.dialogBox("Place name cannot contain numbers!");
        } else {
            searchStage.close();
            showResult(criteria);
        }
        
    }
    
    public static void showResult(String criteria) {
        
        resultStage = new Stage();

        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setVgap(25);

        Label title = new Label();
        title.setText("Search Result");
        
        //get my screen width
        double screenWidth = Screen.getPrimary().getBounds().getWidth();
        //divide the screen width by total columns in the table to get the column width
        double columnWidth = (screenWidth - 4) / 5;
        
        try {
            TableColumn<Places, String> nameColumns = new TableColumn<>("Place Name");
            nameColumns.setCellValueFactory(new PropertyValueFactory<>("placeName"));
            nameColumns.setMinWidth(columnWidth);
            nameColumns.setResizable(false);
            nameColumns.setSortable(false);
            
            TableColumn<Places, Double> priceColumns = new TableColumn<>("Price (Rs)");
            priceColumns.setCellValueFactory(new PropertyValueFactory<>("price"));
            priceColumns.setMinWidth(columnWidth);
            priceColumns.setSortable(false);
            priceColumns.setResizable(false);
            
            TableColumn<Places, String> openingTimeColumns = new TableColumn<>("Opening Time");
            openingTimeColumns.setCellValueFactory(new PropertyValueFactory<>("openingTime"));
            openingTimeColumns.setMinWidth(columnWidth);
            openingTimeColumns.setSortable(false);
            openingTimeColumns.setResizable(false);
            
            TableColumn<Places, String> lunchIncludedColumns = new TableColumn<>("Lunch Included");
            lunchIncludedColumns.setCellValueFactory(new PropertyValueFactory<>("lunchIncluded"));
            lunchIncludedColumns.setMinWidth(columnWidth);
            lunchIncludedColumns.setSortable(false);
            lunchIncludedColumns.setResizable(false);
            
            TableColumn<Places, Integer> numberOfActivitiesColumns = new TableColumn<>("Number of Activities");
            numberOfActivitiesColumns.setCellValueFactory(new PropertyValueFactory<>("numberOfActivities"));
            numberOfActivitiesColumns.setMinWidth(columnWidth);
            numberOfActivitiesColumns.setSortable(false);
            numberOfActivitiesColumns.setResizable(false);
            
            resultTable = new TableView<>();
            resultTable.setFocusTraversable(false);
            resultTable.setEditable(false);
            resultTable.prefHeightProperty().bind(resultStage.heightProperty());
            resultTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            
            Places[] places = DataImport.dataImport();
         
            for (int i = 0; i < places.length; i++) {
                //open the methoc checkContains to check is the search matches the value in the file 
                if (checkContains(places[i].getPlaceName(), criteria) == true) {
                    //if the return value is true, it adds the place and its data to the table
                resultTable.getItems().add(new Places(places[i].getPlaceName(), places[i].getPrice(), places[i].getOpeningTime(), places[i].getLunchIncluded(), places[i].getNumberOfActivities()));
                }
            }
            
            resultTable.getColumns().addAll(nameColumns, priceColumns, openingTimeColumns, lunchIncludedColumns, numberOfActivitiesColumns);
        } catch (Exception e) {
        }
        
        Button btnBack = new Button("Back");
        btnBack.setPadding(new Insets(10, 10, 10, 10));
        btnBack.setMinWidth(250);
        btnBack.setFocusTraversable(false);
        btnBack.setOnAction(e -> resultStage.close());
        
        VBox vbox = new VBox();
        vbox.getChildren().addAll(resultTable, btnBack);
        vbox.setAlignment(Pos.BOTTOM_CENTER);
        
        BorderPane bp = new BorderPane();
        bp.setCenter(vbox);
        
        Scene scene = new Scene(bp);
        
       
        
        resultStage.setScene(scene);
        resultStage.setMaximized(true);
        resultStage.show();
    }
    
    public static boolean checkContains(String arrayData, String criteria) {
        
        //convert the place name in the array and the search criteria to lower case
        //for easier comparison
        arrayData = arrayData.toLowerCase();
        criteria = criteria.toLowerCase();
        
        //if the place name in the array contains the search criteria, returns true
        if (arrayData.contains(criteria)) {
            return true;
        }
    
        //else returns false
        return false;
    }
}
