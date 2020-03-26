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
import java.io.*;

/**
 *
 * @author yadav
 */
public class DataImport {

    public static int count() {
        int count = 0;

        try {
            FileReader inputFile = new FileReader("places.txt");

            try (BufferedReader inputBuffer = new BufferedReader(inputFile)) {
                String readItem = inputBuffer.readLine();

                while (readItem != null) {
                    count++;
                    readItem = inputBuffer.readLine();
                }
            }
        } catch (IOException e) {
           DialogBox.dialogBox("File not found");
        }

        return count;
    }
// Here is the algorithm for the bubblesort which is going to sort out all of the places names
    public static Places[] dataImport() {

        Places[] places = new Places[count()];

        try {
            FileReader inputFile = new FileReader("places.txt");

            try (BufferedReader inputBuffer = new BufferedReader(inputFile)) {
                String data;
                data = inputBuffer.readLine();

                while (data != null) {
                    for (int i = 0; i < count(); i++) {
                        String[] userData = data.split("--");
                        places[i] = new Places();
                        places[i].setPlaceName(userData[0]);
                        places[i].setOpeningTime(userData[1]);
                        places[i].setPrice(Double.parseDouble(userData[2]));
                        places[i].setNumberOfActivities(Integer.parseInt(userData[3]));
                        places[i].setLunchIncluded(userData[4]);
                        data = inputBuffer.readLine();
                    }

                }
            }
        } catch (IOException e) {
           DialogBox.dialogBox("File not found");
        }

        return places;

    }
}
