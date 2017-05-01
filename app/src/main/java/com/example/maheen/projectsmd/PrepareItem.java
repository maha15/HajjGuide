package com.example.maheen.projectsmd;

import java.io.Serializable;

/**
 * Created by mahaamjad on 01/05/2017.
 */

public class PrepareItem implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;



    int imageResourceId;

    private String title;

    private String description;

    private boolean isSelected;

    public PrepareItem() {

    }

    public PrepareItem(String title, String description) {

        this.title = title;
        this.description = description;

    }



    public PrepareItem(String title, String description, boolean isSelected) {

        this.title = title;
        this.description = description;
        this.isSelected = isSelected;
    }


    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

}