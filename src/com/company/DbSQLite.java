package com.company;

import java.sql.*;
import java.util.ArrayList;
//import org.sqlite.*;

/**
 * Created by serjoga on 04.04.17.
 */
public class DbSQLite {

    public static Connection conn;
    public static Statement statmt;
    public static ResultSet resSet;

    public DbSQLite () throws ClassNotFoundException, SQLException
    //
    {
        //System.out.println("База ne Подключена!");
        conn = null;
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:kubdb");

       // System.out.println("База Подключена!");
//TODO
    }

    public static void connect(){


    }

    public static String[] getLong() throws ClassNotFoundException,SQLException{

        ArrayList<String> longs = new ArrayList<>();

        statmt = conn.createStatement();

        resSet = statmt.executeQuery("SELECT l FROM kub GROUP BY l");

        while (resSet.next()){
            longs.add(resSet.getString("l"));
        }

        String [] out = new String[longs.size()];

        for (int i = 0; i < longs.size(); i++){
            out[i] = longs.get(i);
        }

        return out;
    }

    public static String[] getDiameter() throws ClassNotFoundException, SQLException{

        ArrayList<String> tmp = new ArrayList<>();

        String query = "SELECT d FROM kub GROUP BY d";

        statmt = conn.createStatement();

        resSet = statmt.executeQuery(query);

        while (resSet.next()){
            tmp.add(resSet.getString("d"));
        }

        String[] diam = new String[tmp.size()];

        for (int i = 0; i < tmp.size(); i++){
            diam[i] = tmp.get(i);
        }


        return diam;

    }

    public static double getValue (String[] args) throws ClassNotFoundException,SQLException{

        String query = "SELECT v FROM kub WHERE d=" + args[0] + " AND l=" + args[1];

        System.out.println(query);

        statmt = conn.createStatement();

        resSet = statmt.executeQuery(query);

        double value = 0;

        resSet.next();

        String out = resSet.getString("v");

        return Double.parseDouble(out);
    }

    public static void closeDB() throws ClassNotFoundException, SQLException{
        conn.close();
        statmt.close();
        resSet.close();
    }

}
