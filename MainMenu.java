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
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.*;
import javafx.stage.Stage;

/**
 *
 * @author yadav Halkhari
 */
public class MainMenu {

    public static void mainMenu() {
        //MAIN MENU HERE !

        Stage window = new Stage();
        window.setTitle("Main Menu");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(10));

        Text welcome = new Text("Main Menu");
        welcome.setFont(Font.font("Tahoma", FontWeight.LIGHT, 25));
        grid.add(welcome, 0, 0);

        Button showAllBtn = new Button("Show all places");
        showAllBtn.setMinWidth(300);
        showAllBtn.setOnAction(e -> {
            Table.showTable("unsorted");
        });
        grid.add(showAllBtn, 0, 1);
        
        Button showAllSortedBtn = new Button("Show all places in alphabetical order");
        showAllSortedBtn.setMinWidth(300);
        showAllSortedBtn.setOnAction(e -> {
            Table.showTable("sorted");
        });
        grid.add(showAllSortedBtn, 0, 2);

        Button newBtn = new Button("Add new place");
        newBtn.setMinWidth(300);
        newBtn.setOnAction(e -> {
            window.close();
            AddPlace.addPlace();
        });
        grid.add(newBtn, 0, 3);

        Button deleteBtn = new Button("Delete an existing place");
        deleteBtn.setMinWidth(300);
        grid.add(deleteBtn, 0, 4);
        deleteBtn.setOnAction(e -> {
            Delete.delete();
        });
        
        Button searchBtn = new Button("Search");
        searchBtn.setMinWidth(300);
        searchBtn.setOnAction(e -> {
            Search.searchPlaceName();
        });
        grid.add(searchBtn, 0, 5);
        
        Button exitBtn = new Button("Exit");
        exitBtn.setMinWidth(300);
        exitBtn.setOnAction(e -> {
            System.exit(0);
        });
        grid.add(exitBtn, 0, 7);

        Scene scene = new Scene(grid, 800, 500);
        window.setScene(scene);
        window.setMaximized(true);
        window.show();
    }
}
