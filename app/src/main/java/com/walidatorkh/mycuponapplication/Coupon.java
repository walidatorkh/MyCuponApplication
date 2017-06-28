package com.walidatorkh.mycuponapplication;

/**
 * Created by jbt on 28/06/2017.
 */

public class Coupon {
    private String image;
    private String title;
    private String startDate;
    private String endDate;

    public Coupon(String image, String title, String startDate, String endDate) {
        this.image = image;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getImage() {

        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
