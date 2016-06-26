package com.csapp.csapp;

/**
 * Created by Shubhi on 5/6/2016.
 */
public class StudentInfo {

    String rollno = null;
    boolean selected = false;

    public StudentInfo(String code, boolean selected) {
        super();
        this.rollno = code;
      //  this.name = name;
        this.selected = selected;
    }


    public String getRollno() {
        return rollno;
    }

    public void setRollno(String rollno) {
        this.rollno = rollno;
    }

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

}
