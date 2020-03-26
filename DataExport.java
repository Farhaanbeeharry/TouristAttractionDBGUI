/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package touristattractiondbgui;

import java.io.*;

/**
 *
 * @author yadav
 */
public class DataExport {

    public static void dataExport(String placeName, double price, String openingTime, String lunchIncluded, int numberOfActivities) {

        try {
            File outputFile = new File("places.txt");

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, true))) {
                writer.newLine();
                writer.write(placeName + "--" + openingTime + "--" + price + "--" + numberOfActivities + "--" + lunchIncluded);
            }
        } catch (IOException e) {
            DialogBox.dialogBox("File not found");
        }
    }
    
    public static void updateFile(Places[] places) {
        try {
            File outputFile = new File("places.txt");
            try (PrintStream writer = new PrintStream(outputFile)) {
                for (int i = 0; i < places.length; i++) {
                    if (!(places[i].getPlaceName()== null)) {
                        writer.print(places[i].getPlaceName());
                        writer.print("--");
                        writer.print(places[i].getOpeningTime());
                        writer.print("--");
                        writer.print(places[i].getPrice());
                        writer.print("--");
                        writer.print(places[i].getNumberOfActivities());
                        writer.print("--");
                        if (i == places.length - 1) {
                            writer.print(places[i].getLunchIncluded());
                        } else {
                            writer.println(places[i].getLunchIncluded());
                        }
                    }

                }
            }
        } catch (IOException e) {
            DialogBox.dialogBox("File not found!");
        }
    }

}
