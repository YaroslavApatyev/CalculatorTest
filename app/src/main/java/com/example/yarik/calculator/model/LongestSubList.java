package com.example.yarik.calculator.model;

import android.graphics.Point;

import java.util.ArrayList;

public class LongestSubList {

    private ArrayList<Point> innerArrayList = new ArrayList();
    private ArrayList<Point> outArrayList = new ArrayList();
    private char[] charArray;

    public LongestSubList() {

    }

    private void sortSubList ( ArrayList<Point> innerArrayList){
        int k = 0;
        int xSort = innerArrayList.get(k).x ;
        int ySort = innerArrayList.get(k).y ;

       for (int i=1; i<=innerArrayList.size()-1;i++){
           if (xSort < innerArrayList.get(i).x & ySort > innerArrayList.get(i).y){
               outArrayList.add(new Point(xSort ,ySort ));
               outArrayList.add(new Point(innerArrayList.get(i).x ,innerArrayList.get(i).y ));
           }
           k++;
           xSort = innerArrayList.get(k).x ;
           ySort = innerArrayList.get(k).y ;
        }
    }

    public ArrayList<Point> getOutArrayList() {
        return outArrayList;
    }

    public void setInnerArrayList(ArrayList<Point> innerArrayList) {
        this.innerArrayList = innerArrayList;
    }

    public void sortedSubList(char [] CharArray){
        this.charArray = CharArray;
        for (int i = 0; i<this.charArray.length; i = i+2){
            if (i!=1){
            innerArrayList.add(new Point(Character.getNumericValue(charArray[i]),Character.getNumericValue(charArray[i+1])));
            }
        }
        sortSubList(innerArrayList);

    }

    public String convertOutArray(){
        String outString ="";
        for (int i = 0; i < this.outArrayList.size(); i++){
            outString=outString + this.outArrayList.get(i).x;
            outString=outString + this.outArrayList.get(i).y;
            outString=outString + "";

        }
        return outString;
    }
}
