package com.company;

import java.util.ArrayDeque;

/**
 * Created by serjoga on 05.04.17.
 */
public class Supply {

    private ArrayDeque<Wood> woods = new ArrayDeque<>();

    public void setWoods(Wood wood){
        woods.add(wood);
    }

    public ArrayDeque<Wood> getWoods() {
        return woods;
    }
    public void removeWood(Wood wood){
        woods.remove(wood);
    }
}
