package com.example.maheen.projectsmd;

/**
 * Created by maheen on 5/1/2017.
 */

public class Umrahclass {

    private int id;
    private String methodname;
    private String description;
    private int imagename;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getMethodname() {
        return methodname;
    }

    public void setMethodname(String methodname) {
        this.methodname = methodname;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public int getImagename() {
        return imagename;
    }

    public void setImagename(int imagename) {
        this.imagename = imagename;
    }



    public Umrahclass(){}

    public Umrahclass(String methodname, String description,int imagename) {
        super();
        this.methodname = methodname;
        this.description = description;
        this.imagename = imagename;

    }

    //getters & setters

    @Override
    public String toString() {
        return "Umrah [id=" + id + ",methodname=" + methodname + ", description=" + description+ ",imagename=" + imagename
                + "]";
    }
}
