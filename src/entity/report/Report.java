package entity.report;

import java.util.ArrayList;

import entity.interfaces.ISerializable;

public abstract class Report implements ISerializable {
    private static String[] fields;

    public abstract ArrayList<ArrayList<String>> serialize();
}
