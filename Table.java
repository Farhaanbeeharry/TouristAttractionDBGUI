/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package touristattractiondbgui;

/**
 *
 * @author yadav
 */
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.*;

/**
 *
 * @author yadav
 */
public class Table {
    
    static TableView tablePlaces;
    static Stage tableWindow;
    
    public static void showTable(String sortStatus) {
        
        tableWindow = new Stage();
        tableWindow.setResizable(false);
        tableWindow.setTitle("Table");
        
        double screenWidth = Screen.getPrimary().getBounds().getWidth();
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
            
            tablePlaces = new TableView<>();
            tablePlaces.setFocusTraversable(false);
            tablePlaces.setEditable(false);
            tablePlaces.prefHeightProperty().bind(tableWindow.heightProperty());
            tablePlaces.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            
            Places[] places = DataImport.dataImport();
            
            if (sortStatus.equalsIgnoreCase("sorted")) {
                places = sortData(places);
            }

            //take array places by name and sort here
            for (Places place : places) {
                tablePlaces.getItems().add(new Places(place.getPlaceName(), place.getPrice(), place.getOpeningTime(), place.getLunchIncluded(), place.getNumberOfActivities()));
            }
            
            tablePlaces.getColumns().addAll(nameColumns, priceColumns, openingTimeColumns, lunchIncludedColumns, numberOfActivitiesColumns);
        } catch (Exception e) {
        }
        
        Button btnBack = new Button("Back");
        btnBack.setPadding(new Insets(10, 10, 10, 10));
        btnBack.setMinWidth(250);
        btnBack.setFocusTraversable(false);
        btnBack.setOnAction(e -> tableWindow.close());
        
        VBox vbox = new VBox();
        vbox.getChildren().addAll(tablePlaces, btnBack);
        vbox.setAlignment(Pos.BOTTOM_CENTER);
        
        BorderPane bp = new BorderPane();
        bp.setCenter(vbox);
        
        Scene scene = new Scene(bp);
        
        tableWindow.setScene(scene);
        tableWindow.setMaximized(true);
        tableWindow.show();
        
    }
    
    public static Places[] sortData(Places[] places) {
        
        String tempPlaceName, tempLunchIncluded, tempOpeningTime;
        int tempNumberOfActivities;
        double tempPrice;
        
        for (int i = 1; i < places.length; i++) { 
            for (int j = i; j > 0; j--) {
                if (places[j].getPlaceName().compareToIgnoreCase(places[j - 1].getPlaceName()) < 0) {
                    
                    tempPlaceName = places[j].getPlaceName();
                    tempLunchIncluded = places[j].getLunchIncluded();
                    tempOpeningTime = places[j].getOpeningTime();
                    tempNumberOfActivities = places[j].getNumberOfActivities();
                    tempPrice = places[j].getPrice();
                    
                    places[j].setPlaceName(places[j - 1].getPlaceName());
                    places[j].setLunchIncluded(places[j - 1].getLunchIncluded());
                    places[j].setOpeningTime(places[j - 1].getOpeningTime());
                    places[j].setNumberOfActivities(places[j - 1].getNumberOfActivities());
                    places[j].setPrice(places[j - 1].getPrice());
                    
                    places[j - 1].setPlaceName(tempPlaceName);
                    places[j - 1].setLunchIncluded(tempLunchIncluded);
                    places[j - 1].setOpeningTime(tempOpeningTime);
                    places[j - 1].setNumberOfActivities(tempNumberOfActivities);
                    places[j - 1].setPrice(tempPrice);
                    
                }
            }
        }
        
        return places;
    }
}
