package com.company;

/**
 * Created by serjoga on 05.04.17.
 */
public class Wood extends Woods {

    private int diameter;

    private double length;

    private double volume;

    private DbSQLite db;

    public Wood(int d, double l)throws Throwable{
        diameter = d;
        length = l;

        setVolume(diameter, length);

    }

    public int getDiameter(){
        return diameter;
    }
    public double getLength(){
        return length;
    }
    public double getVolume(){
        return volume;
    }
    public void setDiameter(int d){
        diameter = d;
    }
    public void setLength(double l){
        length = l;
    }
    private void setVolume(Integer d, Double l) throws Throwable{

        db = new DbSQLite();

        String diam = d.toString();

        String length = l.toString();

        String[] str;


        if (length.substring(length.length()-1).equals("0")){
            str = new String[]{diam, length.substring(0,1)};
        }
        else
        {
            str = new String[]{diam, length};
        }

        volume = db.getValue(str);

        //TODO

        System.out.println("V " + volume);

    }
}
