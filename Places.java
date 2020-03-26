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
public class Places {

    public String placeName;
    public double price;
    public String openingTime;
    public String lunchIncluded;
    public int numberOfActivities;

    public Places() {
        this.placeName = "";
        this.price = 0.0;
        this.openingTime = "";
        this.lunchIncluded = "";
        this.numberOfActivities = 0;
    }

    /**
     *
     * @param name
     * @param price
     * @param time
     * @param numOfAct
     * @param lunch
     */
    public Places(String placeName, double price, String time, String lunch, int numOfAct) {
        this.placeName = placeName;
        this.price = price;
        this.openingTime = time;
        this.lunchIncluded = lunch;
        this.numberOfActivities = numOfAct;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(String openingTime) {
        this.openingTime = openingTime;
    }

    public String getLunchIncluded() {
        return lunchIncluded;
    }

    public void setLunchIncluded(String lunchIncluded) {
        this.lunchIncluded = lunchIncluded;
    }

    public int getNumberOfActivities() {
        return numberOfActivities;
    }

    public void setNumberOfActivities(int numberOfActivities) {
        this.numberOfActivities = numberOfActivities;
    }
}
